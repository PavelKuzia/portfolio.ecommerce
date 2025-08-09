package ecommerce_app.service;

import ecommerce_app.dao.CustomerRepository;
import ecommerce_app.dto.Purchase;
import ecommerce_app.dto.PurchaseResponse;
import ecommerce_app.entity.Customer;
import ecommerce_app.entity.Order;
import ecommerce_app.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository cr) {
        this.customerRepository = cr;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String trackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(trackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();

        // if customer exists
        String email = customer.getEmail();
        Customer existingCustomer = this.customerRepository.findByEmail(email);
        if (existingCustomer != null) customer = existingCustomer;

        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponse(trackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
