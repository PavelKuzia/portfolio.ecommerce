package ecommerce_app.controller;

import ecommerce_app.dao.OrderRepository;
import ecommerce_app.dao.ProductRepository;
import ecommerce_app.entity.Address;
import ecommerce_app.entity.Order;
import ecommerce_app.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomController {
    private final OrderRepository repo;
    public CustomController(OrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = "/api/get/address", produces="application/json")
    private Address getAddress(@RequestParam("email") String email, Principal principal) {
        String principalEmail = principal.getName();
        if (!principalEmail.equals(email)) throw new AuthorizationDeniedException("Wrong credentials");
        Page<Order> orders = repo.findByCustomerEmail(email, Pageable.unpaged());
        try {
            Order order = orders.toList().getLast();
            return order.getBillingAddress();
        } catch (Exception NoSuchElementException) {
            return null;
        }
    }
}
