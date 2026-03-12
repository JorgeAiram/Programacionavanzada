package Udemy;
import java.util.List;
import java.util.Scanner;

public class VistaVentas {
    private Scanner scanner;

    public VistaVentas() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n--- SISTEMA DE VENTAS ---");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Mostrar Productos");
        System.out.println("3. Realizar Venta");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Producto solicitarDatosProducto() {
        System.out.print("ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock inicial: ");
        int stock = Integer.parseInt(scanner.nextLine());
        return new Producto(id, nombre, precio, stock);
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d\n", 
                                  p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
            }
        }
    }

    public int solicitarIdVenta() {
        System.out.print("Ingrese el ID del producto a vender: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int solicitarCantidadVenta() {
        System.out.print("Ingrese la cantidad a vender: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}