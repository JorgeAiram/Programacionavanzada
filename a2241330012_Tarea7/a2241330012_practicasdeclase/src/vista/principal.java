package clase0603;

import controlador.Cejemplotabla;
import controlador.Crelogdol;

public class principal {
    static Crelogdol x;
    static Cejemplotabla y;

    public static void main(String[] args) {
        try {
            x = new Crelogdol(); // Ya puedes usar el reloj
            y = new Cejemplotabla();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}