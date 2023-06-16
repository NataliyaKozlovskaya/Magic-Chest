package com.magicchestcore.servicies;

import com.magicchestcore.models.BagModel;
import com.magicchestcore.repositories.BagModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BagModelService {
    private final BagModelRepository bagModelRepository;
    @Autowired
    public BagModelService(BagModelRepository bagModelRepository) {
        this.bagModelRepository = bagModelRepository;
         }

    public List<BagModel> findAll(){
        return bagModelRepository.findAll();
    }

    public Optional<BagModel> findById(Integer id){
        return bagModelRepository.findById(id);
    }

    @Transactional
    public void save(BagModel bagModel){
        bagModelRepository.save(bagModel);
    }

    @Transactional
    public void update(BagModel upDatedBagModel){
        bagModelRepository.save(upDatedBagModel);
    }

    @Transactional
    public void delete(Integer id){
        bagModelRepository.deleteById(id);
    }
}
