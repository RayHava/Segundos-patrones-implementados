/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author hecto
 */

import modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.Conexion;

public class VentaFacade {

    private Venta venta;
    private VentaPDF ventaPDF;
    private Reportes reportes;

    public VentaFacade() {
        this.venta = new Venta();
        this.ventaPDF = new VentaPDF();
        this.reportes = new Reportes();
    }

    // Método para generar la factura de venta
    public void generarFacturaPDF(int idCliente) {
        ventaPDF.DatosCliente(idCliente);
        ventaPDF.generarFacturaPDF();
    }

    // Método para generar el reporte de ventas
    public void generarReporteVentas() {
        reportes.ReportesVentas();
    }

    // Método para generar el reporte de ventas del día
    public void generarReporteVentasDia() {
        reportes.ReportesVentasDia();
    }

    // Método para obtener y actualizar el folio de venta
    public int obtenerYActualizarFolio() {
        return venta.obtenerYActualizarFolio();
    }
}