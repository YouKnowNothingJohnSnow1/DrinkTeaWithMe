package com.Herbaciarnia.DrinkTeaWithMe.controller;

import com.Herbaciarnia.DrinkTeaWithMe.exception.ValidationException;
import com.Herbaciarnia.DrinkTeaWithMe.model.Tea;
import com.Herbaciarnia.DrinkTeaWithMe.dto.TeaDto;
import com.Herbaciarnia.DrinkTeaWithMe.service.TeaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/teas")
public class TeaController {

    private final ModelMapper modelMapper;
    private final TeaService teaService;


    @Autowired
    public TeaController(ModelMapper modelMapper, TeaService teaService) {
        this.modelMapper = modelMapper;
        this.teaService = teaService;
    }

    @GetMapping("/all")
    public List<TeaDto> getAllTeas(){
        return teaService.getAllTeas().stream().map(tea -> modelMapper.map(tea, TeaDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/tea/{id}")
    public ResponseEntity<TeaDto> getTeaById(@PathVariable("id") Long id){
       Tea tea = teaService.getTeaById(id);

        //convert entity do Dto
        TeaDto teaResponse = modelMapper.map(tea, TeaDto.class);

        return ResponseEntity.ok().body(teaResponse);
    }

    @PostMapping("/")
    public ResponseEntity<TeaDto> createTea(@RequestBody TeaDto teaDto){

        //convert dto to entity
        Tea teaRequest = modelMapper.map(teaDto, Tea.class);
        Tea tea = teaService.createTea(teaRequest);

        //convert entity do Dto
        TeaDto teaResponse = modelMapper.map(tea, TeaDto.class);

        return new ResponseEntity<TeaDto>(teaResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeaDto> updateTea(@PathVariable long id, @RequestBody TeaDto teaDto){

        validate(teaDto);
        //convert dto to entity
        Tea teaRequest = modelMapper.map(teaDto, Tea.class);
        Tea tea = teaService.updateTea(id, teaRequest);

        //convert entity do Dto
        TeaDto teaResponse = modelMapper.map(tea, TeaDto.class);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeaDto> deleteTea(@PathVariable("id") Long id){
        teaService.deleteTea(id);
        return ResponseEntity.noContent().build();
    }

    public void validate(TeaDto teaDto){
        if(isBlank(teaDto.getTeaName())){
            throw new ValidationException("teaName");
        }
        if(isBlank(teaDto.getTeaDescription())){
            throw new ValidationException("teaDescription");
        }

        if(isBlank(teaDto.getAvailableQuantity())){
            throw new ValidationException("available quantity");
        }
    }

    public boolean isBlank(String value){
        return ofNullable(value).map(String::trim).map(v-> v.length() == 0).orElse(true);
    }

}
