package com.magicchestcore.servicies;

import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.dto.OrderItemDTO;
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
    public void save(Integer personId, OrderDTO orderDTO) {

        Person person = personRepository.getReferenceById(personId);

        List<OrderItemDTO> orderItemList = orderDTO.getOrderItemList();
        Order order = new Order();
        Integer endPrice = 0;
        for (OrderItemDTO el : orderItemList) {
            Integer id = el.getProduct().getId();
            Product product = productRepository.getReferenceById(id);
            Integer productPrice = product.getPrice();
            Integer quantity = el.getQuantity();

            Integer priceOrderItem = productPrice * quantity;
            endPrice = endPrice + priceOrderItem;

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(priceOrderItem);

            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);

            product.getOrderItemList().add(orderItem);

        }
        order.setPrice(endPrice);
        order.setPerson(person);
        order.setDate(new Date());
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
