package clase0603;

import controlador.Cejemplotabla;

public class principal {
    // static Crelogdol x;
    static Cejemplotabla y;

    public static void main(String[] args) {
        try {
            // x = new Crelogdol();
            y = new Cejemplotabla();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}