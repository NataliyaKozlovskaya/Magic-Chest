package com.magicchestcore.servicies;

import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.models.Order;
import com.magicchestcore.models.OrderItem;
import com.magicchestcore.models.Person;
import com.magicchestcore.models.Product;
import com.magicchestcore.repositories.OrderRepository;
import com.magicchestcore.repositories.PersonRepository;
import com.magicchestcore.repositories.ProductRepository;
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
    private final ProductRepository productRepository;
    private final PersonRepository personRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.personRepository = personRepository;
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
    public void save(Integer personId, OrderDTO orderDTO){

        Person person = personRepository.getReferenceById(personId);

        Integer productId = orderDTO.getOrderItemList().get(0).getProduct().getId();
        Integer quantity = orderDTO.getOrderItemList().get(0).getQuantity();

        Product product = productRepository.getReferenceById(productId);
        Integer productPrice = product.getPrice();

        Integer priceOrderItem = productPrice*quantity;

        Order order = new Order();

        order.setDate(new Date());
        order.setPerson(person);
        order.setPrice(priceOrderItem);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(priceOrderItem);

        orderItem.setOrder(order);
        order.getOrderItemList().add(orderItem);

        product.getOrderItemList().add(orderItem);

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
