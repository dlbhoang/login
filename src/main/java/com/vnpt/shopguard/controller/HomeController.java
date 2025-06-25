package com.vnpt.shopguard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vnpt.shopguard.entity.Product;



@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
    	model.addAttribute("title", "Chào mừng đến với ShopGuard");
    	
    	List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop ABC", "Laptop mạnh mẽ cho dân IT", 1500.0, 5));
        products.add(new Product(2, "Điện thoại XYZ", "Smartphone pin trâu, chụp ảnh đẹp", 800.0, 10));
        products.add(new Product(3, "Tai nghe Bluetooth", "Tai nghe không dây, chống ồn", 120.0, 20));

        model.addAttribute("title", "Danh sách sản phẩm");
        model.addAttribute("products", products);

        return "site/home"; 
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Giới thiệu về ShopGuard");
        return "site/about";
    }
}
