package cm.ls.orderservice.controllers;

import cm.ls.orderservice.dto.OrderRequest;
import cm.ls.orderservice.dto.OrderResponse;
import cm.ls.orderservice.model.Order;
import cm.ls.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(orderRequest);
        return "OrderNum: " + order.getOrderNumber();
    }

    @GetMapping("/{orderNum}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderResponse findOrder(@PathVariable String orderNum) {
        return orderService.findOrder(orderNum);
    }

}
