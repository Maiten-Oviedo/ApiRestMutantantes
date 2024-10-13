package org.example.parcial1magneto.controller;

import org.example.parcial1magneto.domain.dto.StatsResponseDto;
import org.example.parcial1magneto.services.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/stats")
public class StatsController {

    //Iyeccion de dependencias
    private final StatsService statsService;
    public StatsController(StatsService statsService){this.statsService = statsService;};


    @GetMapping
    public ResponseEntity<?> getStats(){
        try {
            return ResponseEntity.ok().body(statsService.getStats());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al traer las estadísticas. Por favor, vuelva a intentarlo");
        }
    }
}
