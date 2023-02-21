package com.magicchestcore.repositories;

import com.magicchestcore.models.DressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressModelRepository extends JpaRepository <DressModel, Integer> {
}
