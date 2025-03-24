/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.DetalleVenta;

/**
 *
 * @author hecto
 */
public interface FacturaGenerator {
    void generarFactura(String nombreCliente, String cedulaCliente, String telefonoCliente, String direccionCliente, 
            String fechaActual, String folioStr, List<DetalleVenta> listaProductos);
}