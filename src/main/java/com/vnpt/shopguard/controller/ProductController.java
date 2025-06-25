package com.vnpt.shopguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vnpt.shopguard.entity.Product;
import com.vnpt.shopguard.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("title", "Danh sách sản phẩm");
        return "site/products";  // đổi tên file template cho rõ ràng
    }
}
