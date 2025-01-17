package org.example.parcial1magneto.repository;

import org.example.parcial1magneto.domain.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRepository extends JpaRepository<Dna,Long> {
    Optional<Dna> findByDna(String dna);
    long countByIsMutant(boolean isMutant);
}
