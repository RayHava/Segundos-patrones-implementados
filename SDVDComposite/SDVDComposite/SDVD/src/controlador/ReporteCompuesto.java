/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;



import java.util.ArrayList;
import java.util.List;

public class ReporteCompuesto implements ReporteComponente {
    private List<ReporteComponente> reportes = new ArrayList<>();

    public void agregarReporte(ReporteComponente reporte) {
        reportes.add(reporte);
    }

    public void eliminarReporte(ReporteComponente reporte) {
        reportes.remove(reporte);
    }

    @Override
    public void generarReporte() {
        for (ReporteComponente reporte : reportes) {
            reporte.generarReporte();
        }
    }
}
