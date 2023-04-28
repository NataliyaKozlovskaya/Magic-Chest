package com.magicchestcore.servicies;

import com.magicchestcore.dto.OrderDTO;
import com.magicchestcore.dto.OrderItemDTO;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.dto.ProductDTO;
import com.magicchestcore.models.OrderItem;
import com.magicchestcore.models.Person;
import com.magicchestcore.models.Product;
import com.magicchestcore.repositories.OrderRepository;
import com.magicchestcore.repositories.PersonRepository;
import com.magicchestcore.repositories.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final Integer PERSON_ID=1;
    private static final Integer PRICE=10;
    private static final Integer PRODUCT_ID=3;

    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        this.orderService = new OrderService(orderRepository, productRepository, personRepository);
    }


    private OrderDTO createOrderDTO(){
        ProductDTO product= new ProductDTO();
        product.setId(PRODUCT_ID);
        OrderItemDTO orderItem=new OrderItemDTO(product,  PRICE, 1);
        List<OrderItemDTO> orderItemList= new ArrayList<>();
        orderItemList.add(orderItem);

        OrderDTO orderDTO= new OrderDTO(null, PRICE, PERSON_ID, orderItemList);
        return orderDTO;
    }

    @Test
    void save_orderWithOneProduct_orderPriceEqualsProductPrice() {
      OrderDTO orderDTO = createOrderDTO();

      Person person=new Person();
      Product product=new Product();
        product.setPrice(PRICE);
        Mockito.when(personRepository.getReferenceById(PERSON_ID)).thenReturn(person);
        Mockito.when(productRepository.getReferenceById(PRODUCT_ID)).thenReturn(product);

      orderService.save(PERSON_ID, orderDTO);
    }

    @Test
    void save_orderWithTwoProducts_orderPriceEqualsSumPrices() {

    }
}