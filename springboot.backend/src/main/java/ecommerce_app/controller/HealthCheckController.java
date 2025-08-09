package ecommerce_app.controller;

import ecommerce_app.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    private String healthCheck() {
        return "Works";
    }
}
