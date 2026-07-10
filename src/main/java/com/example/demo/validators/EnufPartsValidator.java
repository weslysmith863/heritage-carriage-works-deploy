package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context == null) return true;
        if(context.getBean(ProductService.class)!=null){
            ProductService repo = context.getBean(ProductService.class);
            if (product.getId() != 0) {
                Product myProduct = repo.findById((int) product.getId());
                for (Part p : myProduct.getParts()) {

                    // Check if taking parts drops stock below 0
                    if (p.getInv() < (product.getInv() - myProduct.getInv())) {
                        return false;
                    }

                    // NEW LOGIC: Check if taking parts drops stock below the part's Minimum Inventory
                    if ((p.getInv() - (product.getInv() - myProduct.getInv())) < p.getMinInv()) {

                        constraintValidatorContext.disableDefaultConstraintViolation();
                        constraintValidatorContext.buildConstraintViolationWithTemplate(
                            "Adding this product would cause the inventory of part '" + p.getName() + "' to fall below its minimum threshold."
                        ).addConstraintViolation();

                        return false;
                    }
                }
                return true;
            }
            else{
                return true;
            }
        }
        return true;
    }
}
