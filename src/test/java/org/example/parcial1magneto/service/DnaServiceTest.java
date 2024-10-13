package org.example.parcial1magneto.service;

import org.example.parcial1magneto.repository.DnaRepository;
import org.example.parcial1magneto.services.DnaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
public class DnaServiceTest {
    private DnaService dnaService;
    private DnaRepository dnaRepository;


    @BeforeEach
    void setUp() {
        // Creamos un mock de DnaRepository
        dnaRepository = Mockito.mock(DnaRepository.class);
        // Pasamos el mock al constructor de DnaService
        dnaService = new DnaService(dnaRepository);
    }

    @Test
    void testIsMutant_RowsandColumns() {
        // Caso de secuencias horizontales y verticales de ADN mutante (más de una secuencia de 4 letras iguales)
        String[] dnaMutant = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        // Llamamos al método isMutant con el ADN mutante
        boolean isMutant = dnaService.isMutant(dnaMutant);

        // Verificamos que el resultado sea true
        assertTrue(isMutant);

        // Verificamos que se haya guardado en la base de datos
        verify(dnaRepository).save(Mockito.any()); // Se asegura de que algo se guardó en la BD
    }

    @Test
    void testIsMutant_Diagonals(){
        //Caso Diagonales de ADN mutante
        String[] dnaMutantDiagonal = {
                "ATTCGA",
                "CACTAC",
                "TACTTT",
                "CTGATT",
                "CCTTCA",
                "TCTATG"
        };
        boolean isMutant = dnaService.isMutant(dnaMutantDiagonal);
        assertTrue(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_NoMutant(){
        //Caso no mutante
        String[] dnaNoMutant = {
                "ATGCGA",
                "CAGTGC",
                "TTATCT",
                "AGAAGG",
                "CCTCTA",
                "TCACTG"
        };
        boolean isMutant = dnaService.isMutant(dnaNoMutant);
        assertFalse(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_AllDirections(){
        //Caso Diagonales de ADN mutante
        String[] dnaMutantDiagonal = {
                "ATCCGA",
                "CACTAC",
                "CACTTT",
                "CCCCTT",
                "CCTTCA",
                "TCTATG"
        };
        boolean isMutant = dnaService.isMutant(dnaMutantDiagonal);
        assertTrue(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testNullInput() {
        //Prueba de dna nulo
        assertThrows(NullPointerException.class, () -> dnaService.isMutant(null));
    }


    //PRUEBAS UNITARIAS PROPUESTAS

    @Test
    void testIsMutant_Mutant1(){
        //Caso mutante
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTG"
        };
        boolean isMutant = dnaService.isMutant(dna);
        assertTrue(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_NoMutant1(){
        //Caso no mutante
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        boolean isMutant = dnaService.isMutant(dna);
        assertFalse(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_Mutant2(){
        //Caso mutante
        String[] dna = {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        };
        boolean isMutant = dnaService.isMutant(dna);
        assertTrue(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_Mutant3(){
        //Caso  mutante
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        boolean isMutant = dnaService.isMutant(dna);
        assertTrue(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_NoMutant2(){
        //Caso no mutante
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        };
        boolean isMutant = dnaService.isMutant(dna);
        assertFalse(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }

    @Test
    void testIsMutant_Mutant4(){
        //Caso mutante
        String[] dna = {
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "CCGACCAGT",
                "GGCACTCCA",
                "AGGACACTA",
                "CAAAGGCAT",
                "GCAGTCCCC"
        };
        boolean isMutant = dnaService.isMutant(dna);
        assertTrue(isMutant);
        verify(dnaRepository).save(Mockito.any());
    }



}
