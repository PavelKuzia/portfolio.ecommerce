package ecommerce_app.controller;

import ecommerce_app.dao.ProductRepository;
import ecommerce_app.entity.Product;
import ecommerce_app.entity.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = "/api/products", produces="application/json")
    private List<Product> getProducts() {
        return repo.findAll();
    }
}
