package com.magicchestcore.servicies;

import com.magicchestcore.models.ShoesModel;
import com.magicchestcore.repositories.ShoesModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ShoesModelService {

    private final ShoesModelRepository shoesModelRepository;
    @Autowired
    public ShoesModelService(ShoesModelRepository shoesModelRepository) {
        this.shoesModelRepository = shoesModelRepository;
    }

    public List<ShoesModel> findAll(){
        return shoesModelRepository.findAll();
    }


    public Optional<ShoesModel> findById(Integer id){
        return shoesModelRepository.findById(id);
    }

    @Transactional
    public void save(ShoesModel shoesModel){
        shoesModelRepository.save(shoesModel);
    }

    @Transactional
    public void update(Integer id, ShoesModel upDateShoesModel){
        upDateShoesModel.setId(id);
        shoesModelRepository.save(upDateShoesModel);
    }

    @Transactional
    public void delete(Integer id){
        shoesModelRepository.deleteById(id);
    }
}
