package com.gdtcore.ordersservice.controller;

//import com.gdtcore.ordersservice.model.dto.OrderRequest;
//import com.gdtcore.ordersservice.model.dto.OrderResponse;
import com.gdtcore.ordersservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String createOrder(@RequestBody OrderRequest orderRequest){
//        this.orderService.createOrder(orderRequest);
//        return "Order placed successfully";
//    }

//    @GetMapping("/read-all")
//    @ResponseStatus(HttpStatus.OK)
//    public List<OrderResponse> getAllOrders(){
//        return this.orderService.getAllOrders();
//    }

//    @GetMapping("/read-one/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public OrderResponse getOrderById(@PathVariable Long id) {
//        return this.orderService.getOrderById(id);
//    }

//    @PatchMapping("/update/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest){
//        this.orderService.updateOrder(id, orderRequest);
//        return;
//    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        // Todo
    }
}
