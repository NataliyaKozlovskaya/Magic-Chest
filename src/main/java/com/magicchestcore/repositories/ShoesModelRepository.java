package com.magicchestcore.repositories;

import com.magicchestcore.models.ShoesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesModelRepository extends JpaRepository <ShoesModel, Integer>{

}
