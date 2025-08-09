package ecommerce_app.dao;

import ecommerce_app.entity.Address;
import ecommerce_app.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCustomerEmail(String email, Pageable pageable);

}
