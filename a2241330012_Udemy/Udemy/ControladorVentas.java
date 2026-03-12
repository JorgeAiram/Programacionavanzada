package Udemy;
import java.util.List;

public class ControladorVentas {
    private VistaVentas vista;
    private GestorArchivos modeloArchivos;
    private List<Producto> inventario;

    public ControladorVentas(VistaVentas vista, GestorArchivos modeloArchivos) {
        this.vista = vista;
        this.modeloArchivos = modeloArchivos;
        this.inventario = modeloArchivos.cargarProductos(); // Carga inicial
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            try {
                int opcion = vista.mostrarMenu();
                switch (opcion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        vista.mostrarProductos(inventario);
                        break;
                    case 3:
                        realizarVenta();
                        break;
                    case 4:
                        salir = true;
                        vista.mostrarMensaje("Saliendo del sistema...");
                        break;
                    default:
                        vista.mostrarMensaje("Opción no válida.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("Error en la entrada de datos. Intente de nuevo.");
            }
        }
    }

    private void agregarProducto() {
        Producto nuevo = vista.solicitarDatosProducto();
        inventario.add(nuevo);
        modeloArchivos.guardarProductos(inventario);
        vista.mostrarMensaje("Producto agregado y guardado en archivo con éxito.");
    }

    private void realizarVenta() {
        vista.mostrarProductos(inventario);
        int idVenta = vista.solicitarIdVenta();
        
        Producto productoSeleccionado = null;
        for (Producto p : inventario) {
            if (p.getId() == idVenta) {
                productoSeleccionado = p;
                break;
            }
        }

        if (productoSeleccionado == null) {
            vista.mostrarMensaje("Producto no encontrado.");
            return;
        }

        int cantidad = vista.solicitarCantidadVenta();
        if (cantidad > productoSeleccionado.getStock()) {
            vista.mostrarMensaje("Stock insuficiente. Stock actual: " + productoSeleccionado.getStock());
        } else {
            // Actualizar stock
            productoSeleccionado.setStock(productoSeleccionado.getStock() - cantidad);
            modeloArchivos.guardarProductos(inventario); // Guardar cambios en el TXT
            
            double total = cantidad * productoSeleccionado.getPrecio();
            vista.mostrarMensaje("Venta realizada con éxito. Total a cobrar: $" + total);
        }
    }
}