package com.magicchestcore.servicies;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.models.Order;
import com.magicchestcore.repositories.OrderRepository;
import com.magicchestcore.repositories.PersonRepository;
import com.magicchestcore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final Converter converter;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, PersonRepository personRepository, Converter converter) {
        this.orderRepository = orderRepository;
        this.converter = converter;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findOrdersByPersonId(Integer personId){
        return orderRepository.findAllByPersonId(personId);
    }

    public Optional<Order> findById(Integer id){
        return orderRepository.findById(id);
    }


    @Transactional
    public void save(OrderDTO orderDTO) {
        Order order = converter.convertToOrder(orderDTO);
        orderRepository.save(order);
}

    @Transactional
    public void delete(Integer id){
        orderRepository.deleteById(id);
    }
}
