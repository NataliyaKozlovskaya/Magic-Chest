package com.magicchestcore.repositories;

import com.magicchestcore.models.BagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagModelRepository extends JpaRepository <BagModel, Integer>{


}
