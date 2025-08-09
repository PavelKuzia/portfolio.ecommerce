package ecommerce_app.controller;

import ecommerce_app.dto.Purchase;
import ecommerce_app.dto.PurchaseResponse;
import ecommerce_app.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService cs) {
        this.checkoutService = cs;
    }

    @PostMapping("/purchase")
    @CrossOrigin(origins = "http://localhost:4200")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }
}
