package persistencia;

import modelo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class ReporteExcelEvaluacion {

    /**
     * Genera (o sobreescribe) el Excel de la evaluacion en la ruta indicada.
     * Nombre sugerido: Asignatura_Profesor_Grupo.xlsx
     */
    public void generarExcel(Evaluacion e, String rutaCompleta) {

        try (Workbook wb = new XSSFWorkbook()) {

            // ==================
            // HOJA 1: PRODUCTO INTEGRADOR
            // ==================
            Sheet producto = wb.createSheet("Producto Integrador");

            Row r0 = producto.createRow(0);
            r0.createCell(0).setCellValue("Asignatura");
            r0.createCell(1).setCellValue(e.getAsignatura());

            Row r1 = producto.createRow(1);
            r1.createCell(0).setCellValue("Profesor");
            r1.createCell(1).setCellValue(e.getProfesor());

            Row r2 = producto.createRow(2);
            r2.createCell(0).setCellValue("Grupo");
            r2.createCell(1).setCellValue(e.getGrupo());

            Row r3 = producto.createRow(3);
            r3.createCell(0).setCellValue("Fecha");
            r3.createCell(1).setCellValue(e.getProductoIntegrador().getFecha());

            // Encabezado criterios
            Row rh = producto.createRow(5);
            rh.createCell(0).setCellValue("Criterio");
            rh.createCell(1).setCellValue("Calificacion");

            int fila = 6;
            for (int i = 1; i <= 6; i++) {
                Row rf = producto.createRow(fila++);
                rf.createCell(0).setCellValue("Criterio " + i);
                int val = switch (i) {
                    case 1 -> e.getProductoIntegrador().getCriterio1();
                    case 2 -> e.getProductoIntegrador().getCriterio2();
                    case 3 -> e.getProductoIntegrador().getCriterio3();
                    case 4 -> e.getProductoIntegrador().getCriterio4();
                    case 5 -> e.getProductoIntegrador().getCriterio5();
                    default -> e.getProductoIntegrador().getCriterio6();
                };
                rf.createCell(1).setCellValue(val);
            }

            Row robs = producto.createRow(fila + 1);
            robs.createCell(0).setCellValue("Observaciones");
            robs.createCell(1).setCellValue(e.getProductoIntegrador().getObservaciones());

            producto.autoSizeColumn(0);
            producto.autoSizeColumn(1);

            // ==================
            // HOJA 2: RUBRICA
            // ==================
            Sheet rubrica = wb.createSheet("Rubrica");

            Row hr = rubrica.createRow(0);
            hr.createCell(0).setCellValue("Alumno");
            hr.createCell(1).setCellValue("Calificacion Rubrica");
            hr.createCell(2).setCellValue("Estado");

            int fr = 1;
            for (Equipo eq : e.getEquipos()) {
                String nombre = eq.getAlumnos().get(0).getNombre();
                int cal = eq.getCalificacionRubrica();
                Row row = rubrica.createRow(fr++);
                row.createCell(0).setCellValue(nombre);
                row.createCell(1).setCellValue(cal);
                row.createCell(2).setCellValue(cal >= 6 ? "Aprobado" : "Reprobado");
            }

            rubrica.autoSizeColumn(0);
            rubrica.autoSizeColumn(1);
            rubrica.autoSizeColumn(2);

            // ==================
            // HOJA 3: LISTA DE COTEJO
            // ==================
            Sheet cotejo = wb.createSheet("Lista de Cotejo");

            Row hc = cotejo.createRow(0);
            hc.createCell(0).setCellValue("Indicadores Cumplidos");

            int fc = 1;
            if (e.getListaCotejo().getIndicadoresSeleccionados() != null) {
                for (String s : e.getListaCotejo().getIndicadoresSeleccionados()) {
                    cotejo.createRow(fc++).createCell(0).setCellValue(s);
                }
            }

            cotejo.autoSizeColumn(0);

            // ==================
            // GUARDAR (sobreescribe si ya existe)
            // ==================
            File archivo = new File(rutaCompleta);
            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                wb.write(fos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error generando Excel: " + ex.getMessage(), ex);
        }
    }

    /** Sobrecarga de compatibilidad: guarda en el directorio actual */
    public void generarExcel(Evaluacion e) {
        generarExcel(e, e.getId() + ".xlsx");
    }
}
