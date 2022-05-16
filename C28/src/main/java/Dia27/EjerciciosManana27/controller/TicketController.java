package Dia27.EjerciciosManana27.controller;

import Dia27.EjerciciosManana27.Documentation.TicketExcelReport;
import Dia27.EjerciciosManana27.Documentation.TicketPdfReport;
import Dia27.EjerciciosManana27.Email.Email;
import Dia27.EjerciciosManana27.Email.EmailService;
import Dia27.EjerciciosManana27.models.Client;
import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Ticket;
import Dia27.EjerciciosManana27.repositories.ProductRepository;
import Dia27.EjerciciosManana27.services.ProductService;
import Dia27.EjerciciosManana27.services.TicketService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService cS;
    @Autowired
    ProductRepository pS;
    @Autowired
    EmailService eS;

    @GetMapping
    public ResponseEntity getAll()
    {
        try
        {
            List<Ticket> tickets = cS.getAll();
            return ResponseEntity.status(200).body(tickets);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @GetMapping("/byClient/{dni}")
    public ResponseEntity getTicketsByDni(@PathVariable("dni") String dni)
    {
        List<Ticket> tickets = cS.getByClient(dni);
        if(tickets != null)
        {
            return ResponseEntity.status(200).body(tickets);
        }
        else {
            return  ResponseEntity.status(204).body("Error");
        }
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity getTicketsByDni(@PathVariable("id") long id)
    {
        Ticket ticket = cS.getById(id);
        if(ticket != null)
        {
            return ResponseEntity.status(200).body(ticket);
        }
        else {
            return  ResponseEntity.status(204).body("Error");
        }
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Ticket tickets)
    {
        try
        {
            Ticket c = cS.save(tickets);
            return ResponseEntity.status(200).body(c);
        }
        catch (Exception e)
        {
            return   ResponseEntity.status(500).body("Error.");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") long id)
    {
        boolean f = cS.delete(id);
        if(f)
        {
            return ResponseEntity.status(200).body("Success.");
        }
        else
        {
            return ResponseEntity.status(204).body("DNI Not found.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Ticket ticket)
    {
        Ticket c = cS.update(ticket, id);
        if(c != null)
        {
            return ResponseEntity.status(200).body("Success");
        }
        else {
            return ResponseEntity.status(204).body("Client Not Found");
        }
    }

    @GetMapping("/pdf/all")
    public void getAllPDF(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=Ticket-ListPDF.pdf");

        List<Ticket> tickets = cS.getAll();
        TicketPdfReport pdfclass = new TicketPdfReport(tickets);
        pdfclass.export(response);
    }

    @GetMapping("/pdf/{dni}")
    public void getAllPDF(@PathVariable("dni") String dni, HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=Ticket-ListPDF.pdf");

        List<Ticket> tickets = cS.getByClient(dni);
        TicketPdfReport pdfclass = new TicketPdfReport(tickets);
        pdfclass.export(response);
    }

    @GetMapping("/excel/all")
    public void getAllExcel(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=TICKET-ListExcel.xlsx");
        List<Ticket> tickets = cS.getAll();
        TicketExcelReport sER = new TicketExcelReport(tickets);
        sER.export(response);
    }

    @GetMapping("/excel/{dni}")
    public void getAllExcelByClient(@PathVariable("dni") String dni, HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=TICKET-ListExcel.xlsx");
        List<Ticket> tickets = cS.getByClient(dni);
        TicketExcelReport sER = new TicketExcelReport(tickets);
        sER.export(response);
    }
}
