package com.magicchestcore.repositories;

import com.magicchestcore.models.Order;
import com.magicchestcore.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);


//   Optional<Order> findByPerson(Integer id);
}
