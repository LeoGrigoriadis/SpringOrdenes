package Dia27.EjerciciosManana27.controller;

import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.services.ClientService;
import Dia27.EjerciciosManana27.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService cS;

    @GetMapping
    public ResponseEntity getAll()
    {
        try
        {
            List<Product> prods = cS.getAll();
            return ResponseEntity.status(200).body(prods);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product prod)
    {
        try
        {
            Product c = cS.save(prod);
            return ResponseEntity.status(200).body(c);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity delete(@PathVariable("code") long code)
    {
        boolean f = cS.delete(code);
        if(f)
        {
            return ResponseEntity.status(200).body("Success.");
        }
        else
        {
            return ResponseEntity.status(204).body("DNI Not found.");
        }
    }

    @PutMapping("/update/{code}")
    public ResponseEntity update(@PathVariable("code") long code, @RequestBody Product prod)
    {
        Product c = cS.update(prod, code);
        if(c != null)
        {
            return ResponseEntity.status(200).body("Success");
        }
        else {
            return ResponseEntity.status(204).body("Client Not Found");
        }
    }
}
