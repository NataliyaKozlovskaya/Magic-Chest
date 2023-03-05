package com.magicchestcore.servicies;

import com.magicchestcore.models.Bag;
import com.magicchestcore.repositories.BagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BagService {
    private final BagRepository bagRepository;

    @Autowired
    public BagService(BagRepository bagRepository) {
        this.bagRepository = bagRepository;
    }

    public List<Bag> findAll(){
        return bagRepository.findAll();
    }


    public Optional<Bag> findById(Integer id){
        return bagRepository.findById(id);
    }

    @Transactional
    public void save(Bag bag){
        bagRepository.save(bag);
    }

    @Transactional
    public void update(Integer id, Bag upDateBag){
        upDateBag.setId(id);
        bagRepository.save(upDateBag);
    }

    @Transactional
    public void delete(Integer id){
        bagRepository.deleteById(id);
    }

}
