package com.Herbaciarnia.DrinkTeaWithMe.service;

import com.Herbaciarnia.DrinkTeaWithMe.exception.ResourceNotFoundException;
import com.Herbaciarnia.DrinkTeaWithMe.model.Tea;
import com.Herbaciarnia.DrinkTeaWithMe.repository.TeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// W SERWISIE NIE UZYWAMY DTO !

@Service
public class TeaService {


    private final TeaRepository teaRepository;

    @Autowired
    public TeaService(TeaRepository teaCrudRepository) {
        this.teaRepository = teaCrudRepository;
    }

    public List<Tea> getAllTeas(){
        return teaRepository.findAll();
    }


    public Tea getTeaById(Long id) {
        Optional<Tea> result = teaRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }else{
            throw new ResourceNotFoundException("Tea", "id", id);
        }
    }

    public Tea createTea(Tea tea) {
        return teaRepository.save(tea);
    }

    public Tea updateTea(long id, Tea teaRequest) {
        Tea tea = teaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tea", "id", id));

        tea.setTeaName(teaRequest.getTeaName());
        tea.setTeaDescription(teaRequest.getTeaDescription());
        tea.setPriceOfSelling(teaRequest.getPriceOfSelling());
        tea.setPriceOfDelivery(teaRequest.getPriceOfDelivery());
        tea.setAvailableQuantity(teaRequest.getAvailableQuantity());
        return teaRepository.save(tea);
    }

    public void deleteTea(Long id) {
        Tea tea = teaRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Tea", "id", id));
        teaRepository.delete(tea);
    }

}

