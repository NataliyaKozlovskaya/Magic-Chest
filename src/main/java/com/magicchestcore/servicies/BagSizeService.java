package com.magicchestcore.servicies;

import com.magicchestcore.models.BagSize;
import com.magicchestcore.repositories.BagSizeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BagSizeService {
    private final BagSizeRepository bagSizeRepository;

    public BagSizeService(BagSizeRepository bagSizeRepository) {
        this.bagSizeRepository = bagSizeRepository;
    }

    public List<BagSize> findAll(){
        return bagSizeRepository.findAll();
    }


    public Optional<BagSize> findById(Integer id){
        return bagSizeRepository.findById(id);
    }

    @Transactional
    public void save(BagSize bagSize){
        bagSizeRepository.save(bagSize);
    }

    @Transactional
    public void update(Integer id, BagSize upDateBagSize){
        upDateBagSize.setId(id);
        bagSizeRepository.save(upDateBagSize);
    }

    @Transactional
    public void delete(Integer id){
        bagSizeRepository.deleteById(id);
    }
}
