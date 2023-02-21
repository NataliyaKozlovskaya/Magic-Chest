package com.magicchestcore.servicies;

import com.magicchestcore.models.Dress;
import com.magicchestcore.models.DressModel;
import com.magicchestcore.repositories.DressModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DressModelService {

    private final DressModelRepository dressModelRepository;

    public DressModelService(DressModelRepository dressModelRepository) {
        this.dressModelRepository = dressModelRepository;
    }

    public List<DressModel> findAll(){
        return dressModelRepository.findAll();
    }


    public Optional<DressModel> findById(Integer id){
        return dressModelRepository.findById(id);
    }

    @Transactional
    public void save(DressModel dressModel){
        dressModelRepository.save(dressModel);
    }

    @Transactional
    public void update(Integer id, DressModel upDateDressModel){
        upDateDressModel.setId(id);
        dressModelRepository.save(upDateDressModel);
    }

    @Transactional
    public void delete(Integer id){
        dressModelRepository.deleteById(id);
    }
}
