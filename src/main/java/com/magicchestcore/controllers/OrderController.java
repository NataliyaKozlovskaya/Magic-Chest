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
//@RequestMapping("/person/{personId}/order") -- ?
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @Autowired
    public OrderController(OrderService orderService, Converter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }

    // admin
    @GetMapping("/order")
    public List<OrderDTO> findAll() {
        return orderService.findAll().stream().map(converter::convertToOrderDTO).collect(Collectors.toList());
    }


    //admin, user
    @GetMapping("/person/{personId}/order")
    public List<OrderDTO> findOrdersByPersonId(@PathVariable("personId") Integer personId) {
        List<Order> ordersByPersonId = orderService.findOrdersByPersonId(personId);
        List<OrderDTO> collect = ordersByPersonId.stream().map(converter::convertToOrderDTO).collect(Collectors.toList());
        return collect;

    }

// admin, USER
    @GetMapping("/order/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Order> order = orderService.findById(id);
        if(order.isPresent()){
            OrderDTO orderDTO = converter.convertToOrderDTO(order.get());
            return ResponseEntity.ok(orderDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    //user
    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO){
       orderService.save(converter.convertToOrder(orderDTO));
    }


    //  user
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
    }

}
