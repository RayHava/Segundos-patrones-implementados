package modelo;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Venta {

    public int obtenerFolioVenta() {
        int folio = 1; // Valor inicial por defecto
        Connection cn = Conexion.conectar();
        String sql = "SELECT MAX(folio) AS max_folio FROM ventas WHERE DATE(fecha) = CURDATE()";

        try (PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                folio = rs.getInt("max_folio") + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el folio de venta: " + e);
        }
        return folio;
    }
    
    public int obtenerYActualizarFolio() {
    Connection cn = Conexion.conectar();
    int folio = 0;
    String sqlSelect = "SELECT folio_actual FROM folios WHERE id = 1";
    String sqlUpdate = "UPDATE folios SET folio_actual = folio_actual + 1 WHERE id = 1";
    
    try (Statement st = cn.createStatement()) {
        ResultSet rs = st.executeQuery(sqlSelect);
        if (rs.next()) {
            folio = rs.getInt("folio_actual");
        }
        // Incrementar el folio
        st.executeUpdate(sqlUpdate);
    } catch (SQLException e) {
        System.out.println("Error al obtener o actualizar el folio: " + e);
    } finally {
        try {
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e);
        }
    }
    return folio;
}

}
