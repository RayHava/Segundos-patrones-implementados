/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Producto;

/**
 *
 * @author hecto
 */
public interface ProductoDAO {
    boolean guardar(Producto objeto);
    boolean existeProducto(String producto);
    boolean actualizar(Producto objeto, int idProducto);
    boolean eliminar(int idProducto);
    boolean actualizarStock(Producto objeto, int idProducto);
}