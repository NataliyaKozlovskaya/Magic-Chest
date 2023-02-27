package com.magicchestcore.servicies;

import com.magicchestcore.models.Dress;
import com.magicchestcore.repositories.DressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DressService {

    private final DressRepository dressRepository;

    @Autowired
    public DressService(DressRepository dressRepository) {
        this.dressRepository = dressRepository;
    }

    public List<Dress> findAll(){
        return dressRepository.findAll();
    }


    public Optional<Dress> findById(Integer id){
        return dressRepository.findById(id);
    }

    @Transactional
    public void save(Dress dress){
        dressRepository.save(dress);
    }

    @Transactional
    public void update(Integer id, Dress upDateDress){
        upDateDress.setId(id);
        dressRepository.save(upDateDress);
    }

    @Transactional
    public void delete(Integer id){
        dressRepository.deleteById(id);
    }
}
