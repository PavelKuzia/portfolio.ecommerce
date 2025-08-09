package ecommerce_app.service;

import ecommerce_app.dto.Purchase;
import ecommerce_app.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
