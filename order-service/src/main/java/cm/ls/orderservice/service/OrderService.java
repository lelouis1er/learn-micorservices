package cm.ls.orderservice.service;

import cm.ls.orderservice.dto.OrderLineItemDto;
import cm.ls.orderservice.dto.OrderRequest;
import cm.ls.orderservice.model.Order;
import cm.ls.orderservice.model.OrderLineItems;
import cm.ls.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToLineItem).collect(Collectors.toList());

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItemsList)
                .build();

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
