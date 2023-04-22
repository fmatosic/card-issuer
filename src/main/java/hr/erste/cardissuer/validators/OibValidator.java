package hr.erste.cardissuer.validators;

import hr.erste.cardissuer.annotations.ValidOib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OibValidator implements ConstraintValidator<ValidOib, String> {
    @Override
    public void initialize(ValidOib constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return validateOib(s);
    }
    private boolean validateOib(String oib){
        return OibValidation.checkOIB(oib);
    }
}
