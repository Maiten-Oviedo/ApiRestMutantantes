package org.example.parcial1magneto.service;

import org.example.parcial1magneto.repository.DnaRepository;
import org.example.parcial1magneto.services.DnaService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DnaServiceTest {
    @Mock
    private DnaRepository dnaRepository;  // Creamos un mock de DnaRepository

    @InjectMocks
    private DnaService dnaService;  // Inyectamos el mock de DnaRepository en DnaService

}
