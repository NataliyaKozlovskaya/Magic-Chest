package com.magicchestcore.servicies;

import com.magicchestcore.models.DressSize;
import com.magicchestcore.repositories.DressSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DressSizeService {
    private final DressSizeRepository dressSizeRepository;
    @Autowired
    public DressSizeService(DressSizeRepository dressSizeRepository) {
        this.dressSizeRepository = dressSizeRepository;
    }

    public List<DressSize> findAll(){
        return dressSizeRepository.findAll();
    }

    public Optional<DressSize> findById(Integer id){
        return dressSizeRepository.findById(id);
    }

    @Transactional
    public void save(DressSize dressSize){
        dressSizeRepository.save(dressSize);
    }

    @Transactional
    public void update(DressSize upDatedDressSize){
        dressSizeRepository.save(upDatedDressSize);
    }

    @Transactional
    public void delete(Integer id){
        dressSizeRepository.deleteById(id);
    }
}
