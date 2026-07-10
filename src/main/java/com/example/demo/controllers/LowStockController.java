package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class LowStockController {

    private final PartRepository partRepository;

    public LowStockController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @GetMapping("/lowstock")
    public String lowStockReport(Model model) {
        List<Part> allParts = StreamSupport
                .stream(partRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<Part> lowStockParts = allParts.stream()
                .filter(p -> p.getInv() <= p.getMinInv())
                .collect(Collectors.toList());

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a"));

        model.addAttribute("lowStockParts", lowStockParts);
        model.addAttribute("timestamp", timestamp);
        return "lowstock";
    }
}
