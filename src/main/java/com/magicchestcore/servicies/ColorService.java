package com.magicchestcore.servicies;

import com.magicchestcore.models.Color;
import com.magicchestcore.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ColorService {
    private final ColorRepository colorRepository;
    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> findAll(){
        return colorRepository.findAll();
    }

    public Optional<Color> findById(Integer id){
        return colorRepository.findById(id);
    }


    @Transactional
    public void save(Color color){
        colorRepository.save(color);
    }


    @Transactional
    public void update(Color upDatedColor){
        colorRepository.save(upDatedColor);
    }

    @Transactional
    public void delete(Integer id){
        colorRepository.deleteById(id);
    }
}
