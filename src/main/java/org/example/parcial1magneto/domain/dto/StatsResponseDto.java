package org.example.parcial1magneto.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsResponseDto {
    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;
}