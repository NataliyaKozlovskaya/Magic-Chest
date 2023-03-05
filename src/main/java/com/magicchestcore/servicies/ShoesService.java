package com.magicchestcore.servicies;

import com.magicchestcore.models.Shoes;
import com.magicchestcore.repositories.ShoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ShoesService {

    private final ShoesRepository shoesRepository;

    @Autowired
    public ShoesService(ShoesRepository shoesRepository) {
        this.shoesRepository = shoesRepository;
    }

    public List<Shoes> findAll(){
        return shoesRepository.findAll();
    }


    public Optional<Shoes> findById(Integer id){
        return shoesRepository.findById(id);
    }

    @Transactional
    public void save(Shoes shoes){
        shoesRepository.save(shoes);
    }

    @Transactional
    public void update(Integer id, Shoes upDateShoes){
        upDateShoes.setId(id);
        shoesRepository.save(upDateShoes);
    }

    @Transactional
    public void delete(Integer id){
        shoesRepository.deleteById(id);
    }


}
