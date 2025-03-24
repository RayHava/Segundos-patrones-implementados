package modelo;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetalleVenta {

    //Atributos
    private int idDetalleVenta;
    private int idCabeceraVenta;
    private int idProducto;
    //esta de mas
    private String nombre;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    private double descuento;
    private double iva;
    private double totalPagar;
    private int estado;

    //Contructor
    public DetalleVenta() {
        this.idDetalleVenta = 0;
        this.idCabeceraVenta = 0;
        this.idProducto = 0;
        this.nombre = "";
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.subTotal = 0.0;
        this.descuento = 0.0;
        this.iva = 0.0;
        this.totalPagar = 0.0;
        this.estado = 0;
    }
    
    //Contructor sobrebargado

    public DetalleVenta(int idDetalleVenta, int idCabeceraVenta, int idProducto, String nombre, int cantidad, double precioUnitario, double subTotal, double descuento, double iva, double totalPagar, int estado) {
        this.idDetalleVenta = idDetalleVenta;
        this.idCabeceraVenta = idCabeceraVenta;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.iva = iva;
        this.totalPagar = totalPagar;
        this.estado = estado;
    }
    
    public int obtenerFolioVenta() {
    int folio = 1; // Valor predeterminado
    String fechaHoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    Connection cn = Conexion.conectar();

    String sqlSelect = "SELECT ultimo_folio, fecha FROM folio_ventas WHERE fecha = ?";
    String sqlInsertOrUpdate = "INSERT INTO folio_ventas (fecha, ultimo_folio) VALUES (?, ?) " +
                               "ON DUPLICATE KEY UPDATE ultimo_folio = ?";

    try (PreparedStatement psSelect = cn.prepareStatement(sqlSelect);
         PreparedStatement psInsertOrUpdate = cn.prepareStatement(sqlInsertOrUpdate)) {
        // Verificar si ya existe un folio para la fecha actual
        psSelect.setString(1, fechaHoy);
        ResultSet rs = psSelect.executeQuery();

        if (rs.next()) {
            folio = rs.getInt("ultimo_folio") + 1; // Incrementar el folio
        }

        // Insertar o actualizar el folio en la tabla
        psInsertOrUpdate.setString(1, fechaHoy);
        psInsertOrUpdate.setInt(2, folio);
        psInsertOrUpdate.setInt(3, folio);
        psInsertOrUpdate.executeUpdate();

        rs.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener o actualizar el folio de venta: " + e);
    }

    return folio;
}

    
    //get and set

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    //toString

    @Override
    public String toString() {
        return "DetalleVenta{" + "idDetalleVenta=" + idDetalleVenta + ", idCabeceraVenta=" + idCabeceraVenta + ", idProducto=" + idProducto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subTotal=" + subTotal + ", descuento=" + descuento + ", iva=" + iva + ", totalPagar=" + totalPagar + ", estado=" + estado + '}';
    }
    

}
