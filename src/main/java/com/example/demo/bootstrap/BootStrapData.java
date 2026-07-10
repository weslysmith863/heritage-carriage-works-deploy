package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        // Seed 5 Parts and 5 Products if the database is empty
        if (partRepository.count() == 0 && productRepository.count() == 0) {

            // --- 5 CARRIAGE PARTS ---
            OutsourcedPart part1 = new OutsourcedPart();
            part1.setName("Spoke Wheel Assembly");
            part1.setPrice(425.00);
            part1.setInv(8);
            part1.setMinInv(2);
            part1.setMaxInv(40);
            part1.setCompanyName("Bluegrass Wheel Co.");
            partRepository.save(part1);

            OutsourcedPart part2 = new OutsourcedPart();
            part2.setName("Forged Steel Axle");
            part2.setPrice(310.00);
            part2.setInv(10);
            part2.setMinInv(2);
            part2.setMaxInv(40);
            part2.setCompanyName("Cumberland Ironworks");
            partRepository.save(part2);

            OutsourcedPart part3 = new OutsourcedPart();
            part3.setName("Leather Bench Seat");
            part3.setPrice(780.00);
            part3.setInv(6);
            part3.setMinInv(1);
            part3.setMaxInv(20);
            part3.setCompanyName("Lexington Saddlery");
            partRepository.save(part3);

            OutsourcedPart part4 = new OutsourcedPart();
            part4.setName("Draft Horse Harness Set");
            part4.setPrice(1250.00);
            part4.setInv(4);
            part4.setMinInv(1);
            part4.setMaxInv(15);
            part4.setCompanyName("Lexington Saddlery");
            partRepository.save(part4);

            OutsourcedPart part5 = new OutsourcedPart();
            part5.setName("Hickory Carriage Body Panel");
            part5.setPrice(560.00);
            part5.setInv(12);
            part5.setMinInv(2);
            part5.setMaxInv(50);
            part5.setCompanyName("Appalachian Timber Mills");
            partRepository.save(part5);

            // --- 5 CARRIAGE PRODUCTS ---
            Product prod1 = new Product("Classic Vis-a-Vis Carriage", 18500.00, 2);
            Product prod2 = new Product("Surrey with Fringe Top", 12800.00, 3);
            Product prod3 = new Product("Meadowbrook Cart", 4200.00, 5);
            Product prod4 = new Product("Formal Dress Landau", 22000.00, 1);
            Product prod5 = new Product("Exercise Breaking Cart", 3100.00, 4);

            productRepository.save(prod1);
            productRepository.save(prod2);
            productRepository.save(prod3);
            productRepository.save(prod4);
            productRepository.save(prod5);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
