package com.magicchestcore.repositories;

import com.magicchestcore.models.ShoesSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesSizeRepository extends JpaRepository <ShoesSize, Integer>{

}
