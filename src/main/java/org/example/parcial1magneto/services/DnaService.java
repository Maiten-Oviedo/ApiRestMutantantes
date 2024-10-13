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


    public boolean isMutant(String[] dna){
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

    public boolean analyzeDna(String[] dna){
        validateDna(dna); //Validación adicional de caracteres
        n = dna.length;
        int secuenceFound = 0;

        //Dado que la matriz es nxn, si es menos que 4 no podemos tener secuencias
        if (n < 4 ) return true;
        //Verificamos Horizontales y Verticales

        for (int i = 0; i < n; i++) {
            //Le restamos 4 al límite del for para que las subcadenas no excedan los límites
            for (int j = 0; j < n-4; j++) {
                //Verticales
                //De cada fila sacamos las subcadenas de 4 caracteres
                if (hasSecuence(dna[i].substring(j,j+4))){
                    secuenceFound ++;
                }

                //Horizontales
                //Extraemos la primera letra de las 4 filas y formamos la nueva cadena
                if (hasSecuence(new String(new char[]{dna[j].charAt(i), dna[j + 1].charAt(i), dna[j + 2].charAt(i), dna[j + 3].charAt(i)}))){
                    secuenceFound++;
                }

                //Si al final de las comprobaciones horizontales y verticales tenemos más de una coincidencia retornamos
                //true
                if(secuenceFound > 1) return true;
            }

        }

        //Verificar Diagonales
        for (int i = 0; i <= n - 4; i++) {
            for (int j = 0; j <= n - 4; j++) {
                if (hasSecuence(new String(new char[]{dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3)}))) {
                    secuenceFound++;
                }
                if (hasSecuence(new String(new char[]{dna[i].charAt(j + 3), dna[i + 1].charAt(j + 2), dna[i + 2].charAt(j + 1), dna[i + 3].charAt(j)}))) {
                    secuenceFound++;
                }
                if (secuenceFound > 1) return true;
            }
        }

        return false;

    }

    public boolean hasSecuence(String secuence){
        //Este metodo sirve para corroborar que una cadena de 4 caracteres tenga las mismas letras
        //ya que .chars().distinct().count() nos dice si encontro al menos una diferencia
        //y esto equivaldria a decir que en una secuencia de 4 caracteres al menos uno es distinto.
        return secuence.chars().distinct().count() == 1;
    }

    private void validateDna(String[] dna) {
        for (String row : dna) {
            if (!row.matches("[ATCG]+")) {
                throw new IllegalArgumentException("Invalid DNA sequence: " + row);
            }
        }
    }

}
