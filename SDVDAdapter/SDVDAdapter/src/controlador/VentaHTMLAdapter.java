/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author hecto
 */

import modelo.DetalleVenta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VentaHTMLAdapter implements FacturaGenerator {

    @Override
    public void generarFactura(String nombreCliente, String cedulaCliente, String telefonoCliente, String direccionCliente, String fechaActual, String folioStr, List<DetalleVenta> listaProductos) {
        // Crear el contenido HTML
        StringBuilder contenidoHTML = new StringBuilder();
        contenidoHTML.append("<!DOCTYPE html>\n")
                     .append("<html lang=\"es\">\n")
                     .append("<head>\n")
                     .append("    <meta charset=\"UTF-8\">\n")
                     .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                     .append("    <title>Factura de Venta</title>\n")
                     .append("    <style>\n")
                     .append("        body { font-family: Arial, sans-serif; }\n")
                     .append("        h1 { color: #333; }\n")
                     .append("        table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n")
                     .append("        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n")
                     .append("        th { background-color: #f2f2f2; }\n")
                     .append("    </style>\n")
                     .append("</head>\n")
                     .append("<body>\n")
                     .append("    <h1>Factura de Venta</h1>\n")
                     .append("    <p><strong>Cliente:</strong> ").append(nombreCliente).append("</p>\n")
                     .append("    <p><strong>Cédula:</strong> ").append(cedulaCliente).append("</p>\n")
                     .append("    <p><strong>Teléfono:</strong> ").append(telefonoCliente).append("</p>\n")
                     .append("    <p><strong>Dirección:</strong> ").append(direccionCliente).append("</p>\n")
                     .append("    <p><strong>Fecha:</strong> ").append(fechaActual).append("</p>\n")
                     .append("    <p><strong>Folio:</strong> ").append(folioStr).append("</p>\n")
                     .append("    <table>\n")
                     .append("        <thead>\n")
                     .append("            <tr>\n")
                     .append("                <th>Producto</th>\n")
                     .append("                <th>Cantidad</th>\n")
                     .append("                <th>Precio Unitario</th>\n")
                     .append("                <th>Subtotal</th>\n")
                     .append("            </tr>\n")
                     .append("        </thead>\n")
                     .append("        <tbody>\n");

        // Agregar productos dinámicamente
        double totalPagar = 0.0;
        for (DetalleVenta producto : listaProductos) {
            contenidoHTML.append("            <tr>\n")
                         .append("                <td>").append(producto.getNombre()).append("</td>\n")
                         .append("                <td>").append(producto.getCantidad()).append("</td>\n")
                         .append("                <td>$").append(producto.getPrecioUnitario()).append("</td>\n")
                         .append("                <td>$").append(producto.getSubTotal()).append("</td>\n")
                         .append("            </tr>\n");
            totalPagar += producto.getSubTotal();
        }

        contenidoHTML.append("        </tbody>\n")
                     .append("    </table>\n")
                     .append("    <p><strong>Total a Pagar:</strong> $").append(totalPagar).append("</p>\n")
                     .append("</body>\n")
                     .append("</html>");

        // Guardar el contenido HTML en un archivo
        String nombreArchivo = "Factura_" + nombreCliente.replace(" ", "_") + "_" + fechaActual.replace("/", "_") + ".html";
        File archivo = new File("src/html/" + nombreArchivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(contenidoHTML.toString());
            System.out.println("Factura HTML generada: " + archivo.getAbsolutePath());

            // Abrir el archivo HTML en el navegador predeterminado
            java.awt.Desktop.getDesktop().open(archivo);
        } catch (IOException e) {
            System.out.println("Error al generar la factura HTML: " + e.getMessage());
        }
    }
}