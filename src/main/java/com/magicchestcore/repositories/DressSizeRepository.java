package com.magicchestcore.repositories;

import com.magicchestcore.models.DressSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressSizeRepository extends JpaRepository<DressSize, Integer > {
}
