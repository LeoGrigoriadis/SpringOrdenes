package Dia27.EjerciciosManana27.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    private String dni;
    private String fName;
    private String lName;

    public Client(String dni)
    {
        this.dni = dni;
    }
}