package cm.ls.orderservice.service;

import cm.ls.orderservice.dto.InventoryResponse;
import cm.ls.orderservice.dto.OrderLineItemDto;
import cm.ls.orderservice.dto.OrderRequest;
import cm.ls.orderservice.model.Order;
import cm.ls.orderservice.model.OrderLineItems;
import cm.ls.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToLineItem).collect(Collectors.toList());

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItemsList)
                .build();

        // liste des sku code des produits
        List<String> skuCode_list = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        // on contacte le service d'inventaire pour verifier si le produit est dans le stock
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8083/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCode_list).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        if (inventoryResponses == null) {
            throw new NullPointerException("Impossible de contacter le service d'Inventaire");
        }

        // on verifie que tous les produits sont dans le stock
        boolean result = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::getIsInStock);

        if (!result) {
            throw new IllegalArgumentException("Le produit n'est pas dans le stock !! ");
        }

        orderRepository.save(order);
    }

    private OrderLineItems mapToLineItem(OrderLineItemDto orderLineItem) {
        return OrderLineItems.builder()
                .prix(orderLineItem.getPrix())
                .qte(orderLineItem.getQte())
                .skuCode(orderLineItem.getSkuCode())
                .build();
    }

}
