package Dia27.EjerciciosManana27.repositories;

import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "Select * from ticket t where t.dni = :dni", nativeQuery = true)
    public List<Ticket> getByDni(@Param("dni") String dni);
    @Query(value = "Select name from product p inner join details_ticket t" +
            " on t.n_ticket = p.:n_ticket", nativeQuery = true)
    List<Product> findByNTicket(@Param("n_ticket") long nTicket);
}
