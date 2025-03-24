package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Producto;


public class Ctrl_Producto {

    private ProductoDAO productoDAO;

    public Ctrl_Producto() {
        this.productoDAO = new ProductoDAOImpl(); // Inyectamos la implementación concreta
    }

    public boolean guardar(Producto objeto) {
        return productoDAO.guardar(objeto);
    }

    public boolean existeProducto(String producto) {
        return productoDAO.existeProducto(producto);
    }

    public boolean actualizar(Producto objeto, int idProducto) {
        return productoDAO.actualizar(objeto, idProducto);
    }

    public boolean eliminar(int idProducto) {
        return productoDAO.eliminar(idProducto);
    }

    public boolean actualizarStock(Producto objeto, int idProducto) {
        return productoDAO.actualizarStock(objeto, idProducto);
    }
}