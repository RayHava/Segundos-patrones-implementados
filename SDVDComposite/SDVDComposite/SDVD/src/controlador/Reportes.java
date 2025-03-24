package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Reportes {

    /* ********************************************************************
    * metodo para crear reportes de los clientes registrados en el sistema
    *********************************************************************** */
    public void generarReporteCompuesto() {
        ReporteCompuesto reporteCompuesto = new ReporteCompuesto();

        // Agregar reportes individuales al compuesto
        reporteCompuesto.agregarReporte(new ReporteCliente());
        reporteCompuesto.agregarReporte(new ReporteProducto());
        reporteCompuesto.agregarReporte(new ReporteCategoria());
        reporteCompuesto.agregarReporte(new ReporteVentas());

        // Generar todos los reportes
        reporteCompuesto.generarReporte();
    }

    public void generarReporteIndividual(ReporteComponente reporte) {
        reporte.generarReporte();
    }

}
