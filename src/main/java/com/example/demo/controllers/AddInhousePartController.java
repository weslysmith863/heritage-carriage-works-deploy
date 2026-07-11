package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AddInhousePartController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        theModel.addAttribute("categories", categoryRepository.findAll());
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part,
                             BindingResult theBindingResult, Model theModel,
                             @RequestParam(value = "categoryId", required = false) Long categoryId) {
        theModel.addAttribute("inhousepart", part);
        theModel.addAttribute("categories", categoryRepository.findAll());
        if (theBindingResult.hasErrors()) {
            return "InhousePartForm";
        }
        if (categoryId != null) {
            categoryRepository.findById(categoryId).ifPresent(part::setCategory);
        }
        InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
        InhousePart ip = repo.findById((int) part.getId());
        if (ip != null) part.setProducts(ip.getProducts());
        repo.save(part);
        return "confirmationaddpart";
    }
}
