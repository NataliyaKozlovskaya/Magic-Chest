package com.magicchestcore.controllers;

import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.Order;
import com.magicchestcore.models.Person;
import com.magicchestcore.servicies.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/person/{personId}/order") -- ?
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/order")
    public List<OrderDTO> findAll() {
        return orderService.findAll().stream().map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }



    //??????
    @GetMapping("/person/{personId}/order")//подумать нужен ли ?
    public List<OrderDTO> findOrdersByPersonId(@PathVariable("personId") Integer personId) {
        return orderService.findOrdersByPersonId(personId).stream().map(this::convertToOrderDTO).collect(Collectors.toList());
    }



    // что делать с Optional ???
    @GetMapping("/order/{id}")//подумать нужен ли ?
    public Optional<OrderDTO> findById(@PathVariable("id") Integer id) {
        return convertToOrderDTO(orderService.findById(id));
    }


    // что делать с незаполненными полями ??
    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO){
       orderService.save(convertToOrder(orderDTO));
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody OrderDTO updateOrderDTO) {
        orderService.update(id, convertToOrder(updateOrderDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
    }


    public Order convertToOrder(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }

    public OrderDTO convertToOrderDTO(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, OrderDTO.class);
    }
}
