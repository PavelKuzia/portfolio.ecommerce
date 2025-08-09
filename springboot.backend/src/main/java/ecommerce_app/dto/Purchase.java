package ecommerce_app.dto;

import ecommerce_app.entity.Address;
import ecommerce_app.entity.Customer;
import ecommerce_app.entity.Order;
import ecommerce_app.entity.OrderItem;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
