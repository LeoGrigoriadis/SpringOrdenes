package Dia27.EjerciciosManana27.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser {
    @Id
    private String username;
    private String password;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public String toString() {
        return "MyUser [password=" + password + ", rol=" + role + ", username=" + username + "]";
    }

    
}
