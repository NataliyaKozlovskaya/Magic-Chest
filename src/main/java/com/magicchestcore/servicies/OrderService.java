package com.magicchestcore.servicies;

import com.magicchestcore.models.Order;
import com.magicchestcore.models.Person;
import com.magicchestcore.repositories.OrderRepository;
import com.magicchestcore.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

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
    public void save(Order order){
        Date date = new Date();
        order.setDate(date);
        orderRepository.save(order);
    }

    @Transactional
    public void update(Integer id, Order upDateOrder){
        upDateOrder.setId(id);
        orderRepository.save(upDateOrder);
    }

    @Transactional
    public void delete(Integer id){
        orderRepository.deleteById(id);
    }
}
