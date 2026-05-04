package com.escuela.controller;

import com.escuela.model.*;
import java.util.*;

public class AsignaturaController {
    private AsignaturaDAO dao = new AsignaturaDAO();
    public List<Asignatura> listar(){ return dao.obtenerTodas(); }
}