package com.magicchestcore.repositories;

import com.magicchestcore.models.BagModel;
import com.magicchestcore.models.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends JpaRepository <PromoCode, Integer>{


}
