package ecommerce_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "State")
@Getter
@Setter
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public State() { }

    public State(Integer id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public State(String name, Country c) {
        this.name = name;
        this.country = c;
    }
}
