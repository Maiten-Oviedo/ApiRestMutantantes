package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna,String[]> {
    @Override
    public void initialize(ValidDna constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext constraintValidatorContext) {
        //Reglas de validacion para la seciencia de ADN

        //1. Ni nullo ni vacio

        if (dna == null || dna.length == 0){
            return false;
        }

        int n = dna.length;

        //2. Verificar tamaño de cadenas y que contengan solo A,T,C o G

        for (String row : dna){
            if (row == null || row.length() != n || row.matches("[ATCG]+")){
                return false;
            }
        }

        return true;
    }
}
