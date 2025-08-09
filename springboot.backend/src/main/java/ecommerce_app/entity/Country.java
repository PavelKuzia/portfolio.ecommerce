package ecommerce_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Country")
@Getter
@Setter
public class Country {
    public Country() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<State> states;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
