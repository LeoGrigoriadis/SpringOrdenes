package Dia27.EjerciciosManana27.repositories;

import Dia27.EjerciciosManana27.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}
