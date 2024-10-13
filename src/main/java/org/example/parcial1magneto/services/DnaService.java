package org.example.parcial1magneto.services;

import org.example.parcial1magneto.domain.entities.Dna;
import org.springframework.stereotype.Service;
import org.example.parcial1magneto.repository.DnaRepository;

import java.util.Optional;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;

    //Inyeccion de dependencias de MutantRepository
    public DnaService(DnaRepository dnaRepository){
        this.dnaRepository = dnaRepository;
    }

    private static final int SEQUENCE_LENGTH = 4; // Cantidad de letras iguales necesarias para que sea una secuencia
    private static int n; // Número de filas y columnas del dna


    public  boolean isMutant(String[] dna){
        //Convertimos nuestro dna a String para verificar si se encuentra en un nuestra bdd
        String dnaString = String.join(",",dna);

        Optional<Dna> dnaExist = dnaRepository.findByDna(dnaString); // Utilizamos Optional para el manejo de null

        if (dnaExist.isPresent()){
            return dnaExist.get().isMutant(); //Devolvemos el valor que ya posee en la bdd
        }

        //Si no esta en nuestra bdd, verificamos si es o no mutante
        boolean isMutant = analyzeDna(dna);
        Dna entity = Dna.builder()
                .dna(dnaString)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(entity); //Guardamos la nueva prueba realizada

        return isMutant;//Devolvemos el resultado de la prueba

    };

    public boolean analyzeDna(String[] dna) {
        validateDna(dna); // Validación adicional de caracteres
        n = dna.length;
        int secuenceFound = 0;

        // Si la matriz es menor que 4, no puede tener secuencias
        if (n < SEQUENCE_LENGTH) return false;

        // Verificamos Horizontales y Verticales
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (hasSecuence(dna[i].substring(j, j + 4))) {
                    secuenceFound++;
                }
                if (hasSecuence(new String(new char[]{dna[j].charAt(i), dna[j + 1].charAt(i), dna[j + 2].charAt(i), dna[j + 3].charAt(i)}))) {
                    secuenceFound++;
                }
                if (secuenceFound > 1) return true; // Al encontrar más de 1 secuencia, es mutante
            }
        }

        // Verificar Diagonales
        for (int row = 0; row <= n - SEQUENCE_LENGTH; row++) {
            for (int col = 0; col <= n - SEQUENCE_LENGTH; col++) {
                if (checkSequenceInDiagonal(dna, row, col, 1, 1)) {
                    secuenceFound++;
                }
            }
        }

        // Verificación de diagonales ↙ (derecha a izquierda)
        for (int row = 0; row <= n - SEQUENCE_LENGTH; row++) {
            for (int col = SEQUENCE_LENGTH - 1; col < n; col++) {
                if (checkSequenceInDiagonal(dna, row, col, 1, -1)) {
                    secuenceFound++;
                }
            }
        }

        // Si encontramos más de una secuencia en diagonales, retornamos true
        return secuenceFound > 1;
    }


    private boolean checkSequenceInDiagonal(String[] dna, int startRow, int startCol, int rowDirection, int colDirection) {
        char initial = dna[startRow].charAt(startCol);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[startRow + i * rowDirection].charAt(startCol + i * colDirection) != initial) {
                return false;
            }
        }
        return true;
    }

    public boolean hasSecuence(String secuence){
        //Este metodo sirve para corroborar que una cadena de 4 caracteres tenga las mismas letras
        //ya que .chars().distinct().count() nos dice si encontro al menos una diferencia
        //y esto equivaldria a decir que en una secuencia de 4 caracteres al menos uno es distinto.
        return secuence.chars().distinct().count() == 1;
    }

    private void validateDna(String[] dna) {
        // Validar el input
        if (dna == null || dna.length == 0) {
            throw new IllegalArgumentException("El ADN no puede ser null o vacío.");
        }

        int n = dna.length;

        // Verificar si es un cuadrado NxN
        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new IllegalArgumentException("El ADN debe ser un cuadrado NxN.");
            }
        }

        // Verificar caracteres válidos
        for (String row : dna) {
            for (char base : row.toCharArray()) {
                if ("ATCG".indexOf(base) == -1) {
                    throw new IllegalArgumentException("El ADN contiene caracteres inválidos.");
                }
            }
        }
    }


}