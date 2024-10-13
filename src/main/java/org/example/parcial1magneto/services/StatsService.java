package org.example.parcial1magneto.services;

import org.example.parcial1magneto.domain.dto.StatsResponseDto;
import org.example.parcial1magneto.repository.DnaRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    //Inyección de dependencias
    private final DnaRepository dnaRepository;
    public StatsService(DnaRepository dnaRepository){this.dnaRepository = dnaRepository;}

    public StatsResponseDto getStats(){
        long humanCount = dnaRepository.countByIsMutant(false);
        long mutantCount = dnaRepository.countByIsMutant(true);
        double ratio = humanCount == 0 ? 0 : (double) mutantCount / humanCount;

        return new StatsResponseDto(humanCount, mutantCount, ratio);
    }
}
