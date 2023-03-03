package com.magicchestcore.servicies;

import com.magicchestcore.models.Color;
import com.magicchestcore.repositories.ColorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }
// admin, user
    public List<Color> findAll(){
        return colorRepository.findAll();
    }

    // admin, user
    public Optional<Color> findById(Integer id){
        return colorRepository.findById(id);
    }
//admin
    @Transactional
    public void save(Color color){
        colorRepository.save(color);
    }

//admin
    @Transactional
    public void update(Integer id, Color upDateColor){
        upDateColor.setId(id);
        colorRepository.save(upDateColor);
    }
// admin
    @Transactional
    public void delete(Integer id){
        colorRepository.deleteById(id);
    }
}
