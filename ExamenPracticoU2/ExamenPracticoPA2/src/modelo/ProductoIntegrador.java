package modelo;

public class ProductoIntegrador {

    private String fecha;

    private int criterio1;
    private int criterio2;
    private int criterio3;
    private int criterio4;
    private int criterio5;
    private int criterio6;

    private String observaciones;

    public ProductoIntegrador() {
    }

    public ProductoIntegrador(String fecha, int criterio1, int criterio2,
                              int criterio3, int criterio4,
                              int criterio5, int criterio6,
                              String observaciones) {
        this.fecha = fecha;
        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
        this.criterio3 = criterio3;
        this.criterio4 = criterio4;
        this.criterio5 = criterio5;
        this.criterio6 = criterio6;
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCriterio1() {
        return criterio1;
    }

    public void setCriterio1(int criterio1) {
        this.criterio1 = criterio1;
    }

    public int getCriterio2() {
        return criterio2;
    }

    public void setCriterio2(int criterio2) {
        this.criterio2 = criterio2;
    }

    public int getCriterio3() {
        return criterio3;
    }

    public void setCriterio3(int criterio3) {
        this.criterio3 = criterio3;
    }

    public int getCriterio4() {
        return criterio4;
    }

    public void setCriterio4(int criterio4) {
        this.criterio4 = criterio4;
    }

    public int getCriterio5() {
        return criterio5;
    }

    public void setCriterio5(int criterio5) {
        this.criterio5 = criterio5;
    }

    public int getCriterio6() {
        return criterio6;
    }

    public void setCriterio6(int criterio6) {
        this.criterio6 = criterio6;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}