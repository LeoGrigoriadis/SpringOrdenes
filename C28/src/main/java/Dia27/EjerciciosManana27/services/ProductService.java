package Dia27.EjerciciosManana27.services;

import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository cR;

    public List<Product> getAll()
    {
        return  cR.findAll();
    }

    public Product save(Product c)
    {
        return  cR.save(c);
    }
    @Transactional
    public Product update(Product c, long code)
    {
        if(cR.getById(code) != null)
        {
            c.setCode(code);
            return  cR.save(c);
        }
        else {
            return  null;
        }
    }

    public boolean delete(long code)
    {
        if (cR.getById(code)!=null){
            cR.deleteById(code);
            return  true;
        }
        return false;
    }
}