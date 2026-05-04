package com.escuela;

import com.escuela.controller.AsignaturaController;

public class Main {
    public static void main(String[] args) {
        AsignaturaController controller = new AsignaturaController();
        controller.listar().forEach(a -> {
            System.out.println(a.getIdMateria() + " - " + a.getMateria());
        });
    }
}