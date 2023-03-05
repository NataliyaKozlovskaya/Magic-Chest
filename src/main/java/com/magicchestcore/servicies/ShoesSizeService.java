package com.magicchestcore.servicies;

import com.magicchestcore.models.ShoesSize;
import com.magicchestcore.repositories.ShoesSizeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoesSizeService {
    private final ShoesSizeRepository shoesSizeRepository;

    public ShoesSizeService(ShoesSizeRepository shoesSizeRepository) {
        this.shoesSizeRepository = shoesSizeRepository;
    }

    public List<ShoesSize> findAll(){
        return shoesSizeRepository.findAll();
    }


    public Optional<ShoesSize> findById(Integer id){
        return shoesSizeRepository.findById(id);
    }

    @Transactional
    public void save(ShoesSize shoesSize){
        shoesSizeRepository.save(shoesSize);
    }

    @Transactional
    public void update(Integer id, ShoesSize upDateShoesSize){
        upDateShoesSize.setId(id);
        shoesSizeRepository.save(upDateShoesSize);
    }

    @Transactional
    public void delete(Integer id){
        shoesSizeRepository.deleteById(id);
    }
}
