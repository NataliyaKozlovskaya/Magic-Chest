package com.magicchestcore.servicies;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.dto.OrderItemDTO;
import com.magicchestcore.dto.ProductDTO;
import com.magicchestcore.models.Order;
import com.magicchestcore.models.OrderItem;
import com.magicchestcore.models.Person;
import com.magicchestcore.models.Product;
import com.magicchestcore.repositories.OrderRepository;
import com.magicchestcore.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.magicchestcore.config.util.ProductType.DRESS;
import static com.magicchestcore.config.util.ProductType.SHOES;
import static org.mockito.Mockito.times;
//@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private Converter converter;
    private static final Integer PERSON_ID = 1;
    private static final Integer ORDER_ID = 1;
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldFindAllOrders() {
        List<Order> listOrders = new ArrayList<>();
        Mockito.when(orderRepository.findAll()).thenReturn(listOrders);
        List<Order> list = orderService.findAll();
        Assertions.assertEquals(listOrders, list);
    }


    @Test
    public void shouldFindAllOrdersByPersonId() {
        List<Order> listOrders = new ArrayList<>();
        Mockito.when(orderRepository.findAllByPersonId(PERSON_ID)).thenReturn(listOrders);
        List<Order> ordersByPersonId = orderService.findOrdersByPersonId(PERSON_ID);
        Mockito.verify(orderRepository, times(1)).findAllByPersonId(PERSON_ID);
        Assertions.assertEquals(listOrders, ordersByPersonId);
    }


    @Test
    public void shouldFindOrderById() {
        Order order = new Order();
        Mockito.when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));
        Optional<Order> orderOptional = orderService.findById(ORDER_ID);
        Mockito.verify(orderRepository, times(1)).findById(ORDER_ID);
        Assertions.assertEquals(Optional.of(order), orderOptional);
    }

    @Test
    public void shouldDeleteOrderById(){
        orderService.delete(ORDER_ID);
        Mockito.verify(orderRepository, times(1)).deleteById(ORDER_ID);
    }


    @Test
    public void shouldSaveOrder() {
        OrderDTO orderDTO = createOrderDTO();

        List<OrderItemDTO> orderItemListDTO = orderDTO.getOrderItemList();

        OrderItemDTO orderItemDTO1 = new OrderItemDTO();
        OrderItemDTO orderItemDTO2 = new OrderItemDTO();


        ProductDTO productDTO1 = new ProductDTO();
        ProductDTO productDTO2 = new ProductDTO();

        orderItemDTO1.setProductDTO(productDTO1);
        orderItemDTO2.setProductDTO(productDTO2);

        orderItemListDTO.add(orderItemDTO1);
        orderItemListDTO.add(orderItemDTO2);

        Order order = new Order();

        Person person= new Person();


        Mockito.when(personRepository.getReferenceById(1)).thenReturn(person);

        Mockito.when(converter.convertToOrderItem(orderItemDTO1)).thenReturn(new OrderItem());
        Mockito.when(converter.convertToOrderItem(orderItemDTO2)).thenReturn(new OrderItem());

        Mockito.when(orderRepository.getReferenceById(1)).thenReturn(order);

        Mockito.when(converter.convertToProduct(productDTO1)).thenReturn(new Product());
        Mockito.when(converter.convertToProduct(productDTO2)).thenReturn(new Product());


        Mockito.when(orderRepository.save(order)).thenReturn(order);

        orderService.save(orderDTO);
        Mockito.verify(orderService, times(1)).save(orderDTO);
    }


    private OrderDTO createOrderDTO(){
        ProductDTO productDTO1 = new ProductDTO();
        ProductDTO productDTO2 = new ProductDTO();

        productDTO1.setProductType(DRESS);
        productDTO1.setName("Dress1");
        productDTO1.setPrice(10);

        productDTO2.setProductType(SHOES);
        productDTO2.setName("Shoes1");
        productDTO2.setPrice(11);


        OrderItemDTO orderItem1=new OrderItemDTO(1, productDTO1, 10, 2);
        OrderItemDTO orderItem2=new OrderItemDTO(1, productDTO2, 11, 2);


        Integer price1 = orderItem1.getPrice();
        Integer price2 = orderItem1.getPrice();
        Integer quantity1 = orderItem1.getQuantity();
        Integer quantity2 = orderItem2.getQuantity();
        Integer endPrice= price1*quantity1 + price2*quantity2;

        List<OrderItemDTO> orderItemList= new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);

        return new OrderDTO(1, endPrice, PERSON_ID, new Date(), orderItemList);
    }

}