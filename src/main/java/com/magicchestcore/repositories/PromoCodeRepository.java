package com.magicchestcore.repositories;

import com.magicchestcore.models.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoCodeRepository extends JpaRepository <PromoCode, Integer>{
    List<PromoCode> findAllByPersonId(Integer personId);

}
