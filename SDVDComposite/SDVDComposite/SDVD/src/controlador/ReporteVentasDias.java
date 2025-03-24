/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hecto
 */
public class ReporteVentasDias implements ReporteComponente {

    @Override
    public void generarReporte() {
Document documento = new Document();
    try {
        // Obtener la ruta del usuario
        String ruta = System.getProperty("user.home");
        
        // Crear el archivo PDF en el escritorio
        PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Ventas_Dia.pdf"));
        
        // Agregar la imagen de cabecera
        Image header = Image.getInstance("src/img/header1.jpg");
        header.scaleToFit(650, 1000);
        header.setAlignment(Chunk.ALIGN_CENTER);
        
        // Formato del texto del reporte
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.add("Reporte creado por \nMiscelanea Calicanto\n\n");
        parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
        parrafo.add("Reporte de Ventas del Día \n\n");

        documento.open();
        
        // Agregar la imagen y el texto al documento
        documento.add(header);
        documento.add(parrafo);
        
        // Crear la tabla con las ventas del día
        float[] columnsWidths = {3, 9, 4, 5, 3}; // Definir el tamaño de las columnas
        PdfPTable tabla = new PdfPTable(columnsWidths);
        tabla.addCell("Codigo");
        tabla.addCell("Cliente");
        tabla.addCell("Tot. Pagar");
        tabla.addCell("Fecha Venta");
        tabla.addCell("Estado");

        try {
            Connection cn = Conexion.conectar();
            
            // Obtener las ventas del día actual con la función CURRENT_DATE
            PreparedStatement pst = cn.prepareStatement(
                    "SELECT cv.idCabeceraVenta AS id, "
                    + "CONCAT(c.nombre, ' ', c.apellido) AS cliente, "
                    + "cv.valorPagar AS total, cv.fechaVenta AS fecha, cv.estado "
                    + "FROM tb_cabecera_venta AS cv "
                    + "JOIN tb_cliente AS c ON cv.idCliente = c.idCliente "
                    + "WHERE DATE(cv.fechaVenta) = CURRENT_DATE;");  // Filtra por la fecha de hoy (CURRENT_DATE)

            ResultSet rs = pst.executeQuery();
            double totalVentasDia = 0.0;  // Variable para almacenar el total de ventas del día
            if (rs.next()) {
                do {
                    tabla.addCell(rs.getString(1));
                    tabla.addCell(rs.getString(2));
                    tabla.addCell(rs.getString(3));
                    tabla.addCell(rs.getString(4));
                    tabla.addCell(rs.getString(5));
                    
                    // Sumar el total de ventas del día
                    totalVentasDia += rs.getDouble(3);
                } while (rs.next());
                
                // Agregar la tabla al documento
                documento.add(tabla);
                
                // Agregar el total de ventas del día al final del reporte
                documento.add(new Paragraph("\n\nTotal de Ventas del Día: " + totalVentasDia + " MXN"));
            } else {
                // Si no hay ventas del día, agregar mensaje
                documento.add(new Paragraph("\n\nNo se han realizado ventas hoy."));
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL: " + e);
        }
        
        // Cerrar el documento PDF
        documento.close();
        
        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(null, "Reporte de Ventas del Día creado");

    } catch (DocumentException e) {
        System.out.println("Error al generar el documento: " + e);
    } catch (FileNotFoundException ex) {
        System.out.println("Error al crear el archivo: " + ex);
    } catch (IOException ex) {
        System.out.println("Error al leer el archivo: " + ex);
    }    
    }
    
    
}
