package services;

import domain.entities.Dna;
import org.springframework.stereotype.Service;
import repository.DnaRepository;

import java.util.Optional;

@Service
public class DnaService {


    private final DnaRepository dnaRepository;

    //Inyeccion de dependencias de MutantRepository
    DnaService(DnaRepository dnaRepository){
        this.dnaRepository = dnaRepository;
    }

    private static final int SEQUENCE_LENGTH = 4; // Cantidad de letras iguales necesarias para que sea una secuencia
    private static int n; // Numero de filas y columnas del dna


    public boolean isMutant(String[] dna){
        //Convertimos nuestro dna a String para verificar si se encuentra en un nuestra bdd
        String dnaString = String.join(",",dna);

        Optional<Dna> dnaExist = dnaRepository.findByDna(dnaString); // Utlizamos Optional para el manejo de null

        if (dnaExist.isPresent()){
            return dnaExist.get().isMutant(); //Devolvemos el valor que ya posee en la bdd
        }

        //Si no esta en nuestra bdd, verificamos si es o no mutante
        boolean isMutant = analyzeDna(dnaString);
        Dna entity = Dna.builder()
                .dna(dnaString)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(entity); //Guardamos la nueva prueba realizada

        return isMutant;//Devolvemos el resultado de la prueba

    };

    public boolean analyzeDna(String dna){
        //LOGICA DE COMPROBACION
        return true;
    }

}
