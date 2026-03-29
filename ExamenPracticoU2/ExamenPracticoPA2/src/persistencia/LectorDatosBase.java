package persistencia;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

public class LectorDatosBase {

    private static final String RUTA = "Datosbase.xlsx";

    // ======================
    // ASIGNATURAS
    // ======================
    public Set<String> obtenerAsignaturas() {

        Set<String> asignaturas = new HashSet<>();

        try (FileInputStream fis = new FileInputStream(RUTA);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet("ListasAsistencia");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Cell cell = row.getCell(2); // MATERIA
                if (cell != null) {
                    asignaturas.add(cell.getStringCellValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return asignaturas;
    }

    // ======================
    // PROFESORES
    // ======================
    public Set<String> obtenerProfesores() {

        Set<String> profesores = new HashSet<>();

        try (FileInputStream fis = new FileInputStream(RUTA);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet("ListasAsistencia");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Cell cell = row.getCell(1); // PROFESOR
                if (cell != null) {
                    profesores.add(cell.getStringCellValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return profesores;
    }

    // ======================
    // GRUPOS
    // ======================
    public Set<String> obtenerGrupos() {

        Set<String> grupos = new HashSet<>();

        try (FileInputStream fis = new FileInputStream(RUTA);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet("ListasAsistencia");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Cell cell = row.getCell(0); // LETRA
                if (cell != null) {
                    grupos.add(cell.getStringCellValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return grupos;
    }

    // ======================
    // LISTA DE COTEJO
    // ======================
    public Set<String> obtenerAtributosPorAsignatura(String asignaturaBuscada) {

        Set<String> atributos = new HashSet<>();

        try (FileInputStream fis = new FileInputStream(RUTA);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet("AsignaturaAtributo");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cell cellAsignatura = row.getCell(1);
                Cell cellAtributo = row.getCell(2);

                if (cellAsignatura == null || cellAtributo == null) continue;

                if (cellAsignatura.getStringCellValue()
                        .equalsIgnoreCase(asignaturaBuscada)) {
                    atributos.add(cellAtributo.getStringCellValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return atributos;
    }
}