package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BuyProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long productID) {
        // 1. Fetch the product from the database using the ID passed from the button
        Optional<Product> optionalProduct = productRepository.findById(productID);

        // 2. Check if the product actually exists in the database
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // 3. Check if we have enough inventory to sell
            if (product.getInv() > 0) {
                // Decrement inventory by 1 and save back to the database
                product.setInv(product.getInv() - 1);
                productRepository.save(product);

                // Route to the success page
                return "purchasesuccess";
            } else {
                // Route to the error page if inventory is 0
                return "purchaseerror";
            }
        } else {
            // Route to the error page if the product wasn't found at all
            return "purchaseerror";
        }
    }
}