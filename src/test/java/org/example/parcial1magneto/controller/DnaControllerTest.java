package org.example.parcial1magneto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.parcial1magneto.domain.dto.DnaRequestDto;
import org.example.parcial1magneto.services.DnaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class DnaControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Mock
    private DnaService dnaService;

    @InjectMocks
    private DnaController dnaController;

    @BeforeEach
    void setUp() {
        // Inicializa el ObjectMapper antes de cada prueba
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(dnaController).build(); // Asegúrate de inicializar mockMvc
    }

    @Test
    void testIsMutant_whenMutantDna_thenReturnOk() {
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        DnaRequestDto dnaDto = new DnaRequestDto();
        dnaDto.setDna(dna);

        when(dnaService.isMutant(dna)).thenReturn(true);
        ResponseEntity<?> response = dnaController.isMutant(dnaDto);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debería retornar HTTP 200 OK");
    }

    @Test
    void testIsMutant_whenNonMutantDna_thenReturnForbidden() {
        String[] dna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
        DnaRequestDto dnaDto = new DnaRequestDto();
        dnaDto.setDna(dna);

        when(dnaService.isMutant(dna)).thenReturn(false);

        ResponseEntity<?> response = dnaController.isMutant(dnaDto);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode(), "Debería retornar HTTP 403 Forbidden");
    }

}
