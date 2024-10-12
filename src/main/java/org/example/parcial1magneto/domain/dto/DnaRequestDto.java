package org.example.parcial1magneto.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.parcial1magneto.validation.ValidDna;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaRequestDto {
    @Valid
    @NotNull(message = "El campo dna no puede ser nulo")
    @ValidDna
    private String[] dna;
}
