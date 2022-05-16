package Dia27.EjerciciosManana27.repositories;


import java.util.Optional;


import Dia27.EjerciciosManana27.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser,String>  {


    @Query(value = "SELECT name FROM my_user INNER JOIN role ON my_user.role_id=role.id WHERE username=:username", nativeQuery = true)
    public String getRole(@Param("username") String username);

    public Optional<MyUser> findByUsername(String username);
}
