package main;
import controlador.Cejercicioclase02;
import vista.Vejercicioclase02;

public class Main02 {

    public static void main(String[] args) {
        Vejercicioclase02 v = new Vejercicioclase02();
        new Cejercicioclase02(v);
        v.setVisible(true);
    }
}