package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
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
public class AddOutsourcedPartController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel) {
        Part part = new OutsourcedPart();
        theModel.addAttribute("outsourcedpart", part);
        theModel.addAttribute("categories", categoryRepository.findAll());
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part,
                             BindingResult bindingResult, Model theModel,
                             @RequestParam(value = "categoryId", required = false) Long categoryId) {
        theModel.addAttribute("outsourcedpart", part);
        theModel.addAttribute("categories", categoryRepository.findAll());
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";
        }
        if (categoryId != null) {
            categoryRepository.findById(categoryId).ifPresent(part::setCategory);
        }
        OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
        OutsourcedPart op = repo.findById((int) part.getId());
        if (op != null) part.setProducts(op.getProducts());
        repo.save(part);
        return "confirmationaddpart";
    }
}
