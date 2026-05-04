package com.escuela.model;

import com.escuela.util.BaseDatos;
import java.util.*;

public class AsignaturaDAO {
    public List<Asignatura> obtenerTodas(){
        String sql="SELECT CARRERA, PLAN, IDMATERIA, MATERIA FROM ASIGNATURA";
        return BaseDatos.consultarAObjeto(sql, Asignatura.class);
    }
}