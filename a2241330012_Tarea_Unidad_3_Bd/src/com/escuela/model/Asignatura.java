package com.escuela.model;

public class Asignatura {
    private String carrera;
    private String plan;
    private String idMateria;
    private String materia;

    public String getCarrera(){ return carrera; }
    public void setCarrera(String carrera){ this.carrera=carrera; }
    public String getPlan(){ return plan; }
    public void setPlan(String plan){ this.plan=plan; }
    public String getIdMateria(){ return idMateria; }
    public void setIdMateria(String idMateria){ this.idMateria=idMateria; }
    public String getMateria(){ return materia; }
    public void setMateria(String materia){ this.materia=materia; }
}