package com.magicchestcore.repositories;


import com.magicchestcore.models.Dress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressRepository extends JpaRepository<Dress, Integer> {

}
