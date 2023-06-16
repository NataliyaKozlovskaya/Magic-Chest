package com.magicchestcore.repositories;

import com.magicchestcore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer>{
    List<Order> findAllByPersonId(Integer personId);

}
