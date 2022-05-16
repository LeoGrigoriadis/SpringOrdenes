package Dia27.EjerciciosManana27.Documentation;

import Dia27.EjerciciosManana27.models.Product;
import Dia27.EjerciciosManana27.models.Ticket;

import Dia27.EjerciciosManana27.services.TicketService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TicketPdfReport {
    @Autowired
    TicketService tk;
    private List<Ticket> tickets;
    public TicketPdfReport(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public void HeaderTable(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.cyan);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("TICKET ID"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CLIENT DNI"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PRODUCT NAME"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PRODUCT AMMOUNT"));
        table.addCell(cell);


    }
    public void BodyTable(PdfPTable table)
    {
        for(Ticket ticket: tickets)
        {
            table.addCell(String.valueOf(ticket.getNTicket()));
            table.addCell(String.valueOf(ticket.getClient().getDni()));
            table.addCell(String.valueOf(tk.getProd(ticket).stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", "))));
            table.addCell(String.valueOf(tk.getAmmountProducts(ticket)));

        }
    }
    public void export(HttpServletResponse hsr) throws DocumentException, IOException
    {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, hsr.getOutputStream());
        doc.open();
        Paragraph paragraph = new Paragraph("Ticket List");
        paragraph.setSpacingAfter(5);
        paragraph.setAlignment(paragraph.ALIGN_CENTER);
        doc.add(paragraph);
        PdfPTable pdf = new PdfPTable(4);
        HeaderTable(pdf);
        BodyTable(pdf);
        doc.add(pdf);
        doc.close();
    }
}
