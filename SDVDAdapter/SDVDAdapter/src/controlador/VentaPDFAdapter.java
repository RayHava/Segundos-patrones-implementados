/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelo.DetalleVenta;

/**
 *
 * @author hecto
 */
public class VentaPDFAdapter implements FacturaGenerator {
    private VentaPDF ventaPDF;

    public VentaPDFAdapter(VentaPDF ventaPDF) {
        this.ventaPDF = ventaPDF;
    }


private int obtenerIdClientePorCedula(String cedulaCliente) {
    int idCliente = -1;
    Connection cn = Conexion.conectar();
    String sql = "select idCliente from tb_cliente where cedula = '" + cedulaCliente + "'";
    Statement st;
    try {
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            idCliente = rs.getInt("idCliente"); // Obtener el ID del cliente
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener ID del cliente por cédula: " + e);
    }
    return idCliente;
}


    @Override
    public void generarFactura(String nombreCliente, String cedulaCliente, String telefonoCliente, String direccionCliente, String fechaActual, String folioStr, List<DetalleVenta> listaProductos) {
 int idCliente = obtenerIdClientePorCedula(cedulaCliente);

    if (idCliente != -1) {
        ventaPDF.DatosCliente(idCliente); // Pasar el ID del cliente
        ventaPDF.generarFacturaPDF();
    } else {
        System.out.println("Error: No se encontró el cliente con cédula " + cedulaCliente);
    }    
    }
    
}
