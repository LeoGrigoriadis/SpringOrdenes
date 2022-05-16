package Dia27.EjerciciosManana27.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long nTicket;

    private int ammountProducts;

    @ManyToOne
    @JoinColumn(name = "code")
    private Product prod;

    @ManyToOne
    @JoinColumn(name = "dni")
    private Client client;

}
