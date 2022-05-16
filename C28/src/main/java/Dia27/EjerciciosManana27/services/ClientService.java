package Dia27.EjerciciosManana27.services;

import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository cR;

    public List<Client> getAll()
    {
        return  cR.findAll();
    }

    public Client save(Client c)
    {
        return  cR.save(c);
    }
    @Transactional
    public Client update(Client c, String dni)
    {
        if(cR.getById(dni) != null)
        {
            c.setDni(dni);
            return  cR.save(c);
        }
        else {
            return  null;
        }

    }

    public boolean delete(String dni)
    {
        if (cR.getById(dni)!=null){
            cR.deleteById(dni);
            return  true;
        }
        return false;
    }
}
