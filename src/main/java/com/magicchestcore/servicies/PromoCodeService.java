package com.magicchestcore.servicies;

import com.magicchestcore.models.PromoCode;
import com.magicchestcore.repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class PromoCodeService {
    private final PromoCodeRepository promoCodeRepository;
    @Autowired
    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public List<PromoCode> findAll(){
        return promoCodeRepository.findAll();
    }

    public Optional<PromoCode> findById(Integer id){
        return promoCodeRepository.findById(id);
    }

    public List<PromoCode> findAllByPersonId(Integer personId){
        return promoCodeRepository.findAllByPersonId(personId);
    }

    @Transactional
    public void save(PromoCode promoCode){
        promoCodeRepository.save(promoCode);
    }

    @Transactional
    public void update(PromoCode updatedPromoCode){
        promoCodeRepository.save(updatedPromoCode);
    }

    @Transactional
    public void delete(Integer id){
        promoCodeRepository.deleteById(id);
    }
}
