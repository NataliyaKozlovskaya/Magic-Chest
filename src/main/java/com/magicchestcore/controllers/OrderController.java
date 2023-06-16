package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.models.Order;
import com.magicchestcore.servicies.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order/")
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @Autowired
    public OrderController(OrderService orderService, Converter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }


    @GetMapping
    public List<OrderDTO> findAll() {
        return orderService.findAll().stream().map(converter::convertToOrderDTO).collect(Collectors.toList());
    }


    @GetMapping("/{personId}")
    public List<OrderDTO> findOrdersByPersonId(@PathVariable("personId") Integer personId) {
        List<Order> ordersByPersonId = orderService.findOrdersByPersonId(personId);
        return ordersByPersonId.stream().map(converter::convertToOrderDTO).collect(Collectors.toList());

    }

    @GetMapping("/orderId/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Integer id) {
        Optional<Order> order = orderService.findById(id);
        if(order.isPresent()){
            OrderDTO orderDTO = converter.convertToOrderDTO(order.get());
            return ResponseEntity.ok(orderDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/save")
    public void save(@RequestBody OrderDTO orderDTO){
       orderService.save(orderDTO);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
    }

}
