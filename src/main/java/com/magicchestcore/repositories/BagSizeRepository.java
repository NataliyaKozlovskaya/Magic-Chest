package com.magicchestcore.repositories;

import com.magicchestcore.models.BagSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;

@Repository
public interface BagSizeRepository extends JpaRepository <BagSize, Integer>{


}
