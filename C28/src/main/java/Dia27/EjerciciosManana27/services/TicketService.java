package Dia27.EjerciciosManana27.services;

import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Ticket;
import Dia27.EjerciciosManana27.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository tR;

    public List<Ticket> getAll()
    {
        return  tR.findAll();
    }

    public Ticket save(Ticket c)
    {
        return  tR.save(c);
    }

    public List<Ticket> getByClient(String dni)
    {
        return tR.getByDni(dni);
    }
    public Ticket getById(long id)
    {
        return tR.getById(id);
    }
    @Transactional
    public Ticket update(Ticket c, long id)
    {
        if(tR.getById(id) != null)
        {
            c.setNTicket(id);
            return  tR.save(c);
        }
        else {
            return  null;
        }

    }

    public boolean delete(long id)
    {
        try
        {
            tR.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public List<Product> getProd(Ticket s) {
        return tR.findByNTicket(s.getNTicket());
    }

    public Object getAmmountProducts(Ticket s) {
        return tR.findByNTicket(s.getNTicket());
    }
}
