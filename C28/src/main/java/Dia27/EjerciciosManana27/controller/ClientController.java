package Dia27.EjerciciosManana27.controller;

import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService cS;

    @GetMapping
    public ResponseEntity getAll()
    {
        try
        {
            List<Client> clients = cS.getAll();
            return ResponseEntity.status(200).body(clients);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Client client)
    {
        try
        {
            Client c = cS.save(client);
            return ResponseEntity.status(200).body(c);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity delete(@PathVariable("dni") String dni)
    {
        boolean f = cS.delete(dni);
        if(f)
        {
            return ResponseEntity.status(200).body("Success.");
        }
        else
        {
            return ResponseEntity.status(204).body("DNI Not found.");
        }
    }

    @PutMapping("/update/{dni}")
    public ResponseEntity update(@PathVariable("dni") String dni, @RequestBody Client client)
    {
        Client c = cS.update(client, dni);
        if(c != null)
        {
            return ResponseEntity.status(200).body("Success");
        }
        else {
            return ResponseEntity.status(204).body("Client Not Found");
        }
    }

}
