package Udemy;
public class Main {
    public static void main(String[] args) {
        // Inicializar el Modelo
        GestorArchivos gestor = new GestorArchivos();
        
        // Inicializar la Vista
        VistaVentas vista = new VistaVentas();
        
        // Inicializar el Controlador inyectando el Modelo y la Vista
        ControladorVentas controlador = new ControladorVentas(vista, gestor);
        
        // Iniciar la aplicación
        controlador.iniciar();
    }
}