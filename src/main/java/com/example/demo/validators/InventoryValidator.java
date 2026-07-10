package com.example.demo.validators;

import com.example.demo.domain.Part;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InventoryValidator implements ConstraintValidator<ValidInventory, Part> {

    @Override
    public void initialize(ValidInventory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;
        }

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if (part.getMinInv() > part.getMaxInv()) {
            context.buildConstraintViolationWithTemplate("Minimum inventory cannot exceed Maximum inventory.")
                    .addConstraintViolation();
            isValid = false;
        }
        else if (part.getInv() < part.getMinInv()) {
            context.buildConstraintViolationWithTemplate("Inventory cannot be lower than the Minimum inventory.")
                    .addConstraintViolation();
            isValid = false;
        }
        else if (part.getInv() > part.getMaxInv()) {
            context.buildConstraintViolationWithTemplate("Inventory cannot be higher than the Maximum inventory.")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}