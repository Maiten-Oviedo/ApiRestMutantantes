package org.example.parcial1magneto.controller;

import org.example.parcial1magneto.domain.dto.DnaRequestDto;
import org.example.parcial1magneto.domain.dto.DnaResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.parcial1magneto.services.DnaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/mutant")
public class DnaController {
    private final DnaService dnaService;
    //Inyeccion de dependencias
    DnaController(DnaService dnaService){
        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<?> isMutant(@RequestBody @Valid DnaRequestDto dnaRequestDto){
        try{
            //Se comprueba que el dna ingresado sea o no mutante
            boolean isMutant = dnaService.isMutant(dnaRequestDto.getDna());
            DnaResponseDto dnaResponseDto = new DnaResponseDto(isMutant);
            if (isMutant){
                //De ser mutante se devuelve un status 200 con sus respectivos campos
                return ResponseEntity.ok(dnaResponseDto);
            }
            //De ser falso se devuelve un 403-FORBIDDEN
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponseDto);
        }catch (Exception e){
            //En caso de errores se pide volver a intentarlo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar la peticion. Por favor, vuelva a intentarlo.");
        }
    }
}
