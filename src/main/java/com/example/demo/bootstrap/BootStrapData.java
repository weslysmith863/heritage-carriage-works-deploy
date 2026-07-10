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

        // Part E: Add 5 Parts and 5 Products if the database is empty
        if (partRepository.count() == 0 && productRepository.count() == 0) {

            // --- 5 ROCKET PARTS ---
            OutsourcedPart part1 = new OutsourcedPart();
            part1.setName("Vacuum Merlin Engine");
            part1.setPrice(250000.00);
            part1.setInv(5);
            part1.setMinInv(1);
            part1.setMaxInv(50);
            part1.setCompanyName("SpaceX Systems");
            partRepository.save(part1);

            OutsourcedPart part2 = new OutsourcedPart();
            part2.setName("Carbon Composite Fairing");
            part2.setPrice(45000.00);
            part2.setInv(12);
            part2.setMinInv(1);
            part2.setMaxInv(50);
            part2.setCompanyName("Plastex Aerospace");
            partRepository.save(part2);

            OutsourcedPart part3 = new OutsourcedPart();
            part3.setName("Flight Computer Grid Fin");
            part3.setPrice(18500.00);
            part3.setInv(8);
            part3.setMinInv(1);
            part3.setMaxInv(50);
            part3.setCompanyName("Guidance Avionics");
            partRepository.save(part3);

            OutsourcedPart part4 = new OutsourcedPart();
            part4.setName("Liquid Oxygen (LOX) Tank");
            part4.setPrice(85000.00);
            part4.setInv(4);
            part4.setMinInv(1);
            part4.setMaxInv(50);
            part4.setCompanyName("CryoFuel Logistics");
            partRepository.save(part4);

            OutsourcedPart part5 = new OutsourcedPart();
            part5.setName("Cold Gas Thruster Assembly");
            part5.setPrice(12500.00);
            part5.setInv(15);
            part5.setMinInv(1);
            part5.setMaxInv(50);
            part5.setCompanyName("OrbitTech Propulsion");
            partRepository.save(part5);

            // --- 5 ROCKET PRODUCTS ---
            Product prod1 = new Product("Falcon Heavy Lift Kit", 1200000.00, 3);
            Product prod2 = new Product("Orbital Delivery Stage", 650000.00, 5);
            Product prod3 = new Product("LEO Satellite Base", 320000.00, 7);
            Product prod4 = new Product("Suborbital Test Vehicle", 150000.00, 2);
            Product prod5 = new Product("Deep Space Cargo Unit", 980000.00, 4);

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
