package Controlador;

import Modelo.GestionProductos;
import Modelo.Producto;
import persistencia.GestorCSV;
import vista.VentanaPrincipal;
import vista.VistaProducto;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ControladorProducto implements ActionListener {
    private VentanaPrincipal mdi;
    private VistaProducto vistaProd;
    private GestionProductos gestionProd;
    private GestorCSV csv;

    public ControladorProducto(VentanaPrincipal mdi, VistaProducto vistaProd, GestionProductos gestionProd, GestorCSV csv) {
        this.mdi = mdi;
        this.vistaProd = vistaProd;
        this.gestionProd = gestionProd;
        this.csv = csv;

        this.vistaProd.btnGuardar.addActionListener(this);
        this.vistaProd.btnBuscar.addActionListener(this);
        this.vistaProd.btnModificar.addActionListener(this);
        this.vistaProd.btnEliminar.addActionListener(this);
        this.vistaProd.btnLimpiar.addActionListener(this);
        
        
        this.vistaProd.btnCargarFoto.addActionListener(this);

        // Evento para calcular el precio de venta automáticamente mientras escribes
        this.vistaProd.txtPrecioCompra.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { calcularPrecioVenta(); }
        });
        this.vistaProd.txtPorcentajeGanancia.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { calcularPrecioVenta(); }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaProd.btnGuardar) guardarProducto();
        else if (e.getSource() == vistaProd.btnBuscar) buscarProducto();
        else if (e.getSource() == vistaProd.btnModificar) modificarProducto();
        else if (e.getSource() == vistaProd.btnEliminar) eliminarProducto();
        else if (e.getSource() == vistaProd.btnLimpiar) limpiarFormulario();
        
      
        else if (e.getSource() == vistaProd.btnCargarFoto) cargarFotografia();
    }

    // ==========================================
    // NUEVO MÉTODO PARA SUBIR FOTOS
    // ==========================================
    private void cargarFotografia() {
        String sku = vistaProd.txtSku.getText().trim();
        
        // Exigimos que primero escriba el SKU para poder ponerle ese nombre a la foto
        if (sku.isEmpty()) {
            JOptionPane.showMessageDialog(vistaProd, "Primero escriba el SKU del producto para poder nombrar la fotografía.");
            return;
        }

        // Abrimos la ventana exploradora de archivos
        JFileChooser explorador = new JFileChooser();
        explorador.setDialogTitle("Seleccionar foto del producto");
        
        // Filtramos para que solo deje subir imágenes
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
        explorador.setFileFilter(filtro);

        int respuesta = explorador.showOpenDialog(vistaProd);
        
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            try {
                File archivoSeleccionado = explorador.getSelectedFile();
                
                // Extraemos la extensión original (.jpg o .png)
                String nombreOriginal = archivoSeleccionado.getName();
                String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
                
                // El nuevo nombre será: "SKU.jpg" (Ejemplo: 75010.jpg)
                String nuevoNombreFoto = sku + extension;
                File archivoDestino = new File(nuevoNombreFoto);

                // Copiamos la foto al lado de nuestro programa (proyecto)
                Files.copy(archivoSeleccionado.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                // Guardamos el nombre para la base de datos
                vistaProd.rutaFotoActual = nuevoNombreFoto;

                // Mostramos la foto en el cuadrito (lblFoto) ajustada a 150x150
                ImageIcon iconoOriginal = new ImageIcon(archivoDestino.getAbsolutePath());
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                vistaProd.lblFoto.setIcon(new ImageIcon(imagenEscalada));
                vistaProd.lblFoto.setText(""); // Quitamos el texto "Sin Imagen"

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vistaProd, "Error al cargar la imagen: " + ex.getMessage());
            }
        }
    }

    // ==========================================
    // RESTO DE LOS MÉTODOS DEL CONTROLADOR
    // ==========================================

    private void calcularPrecioVenta() {
        try {
            double compra = Double.parseDouble(vistaProd.txtPrecioCompra.getText());
            double ganancia = Double.parseDouble(vistaProd.txtPorcentajeGanancia.getText());
            double venta = compra + (compra * (ganancia / 100.0));
            vistaProd.txtPrecioVenta.setText(String.format("%.2f", venta));
        } catch (Exception ex) {
            vistaProd.txtPrecioVenta.setText("0.00");
        }
    }

    private void guardarProducto() {
        if (vistaProd.txtSku.getText().isEmpty() || vistaProd.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaProd, "SKU y Nombre son obligatorios.");
            return;
        }
        try {
            String sku = vistaProd.txtSku.getText();
            if (gestionProd.existe(sku)) {
                JOptionPane.showMessageDialog(vistaProd, "El SKU ya existe.");
                return;
            }
            Producto p = capturarDatos();
            gestionProd.insertar(p);
            guardarYRefrescar("Producto guardado con éxito.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaProd, "Revise que los números y precios sean correctos.");
        }
    }

    private void buscarProducto() {
        String skuBuscado = JOptionPane.showInputDialog(vistaProd, "Ingrese el SKU a buscar:");
        if (skuBuscado != null && !skuBuscado.isEmpty()) {
            Producto p = gestionProd.buscar(skuBuscado);
            if (p != null) {
                vistaProd.txtSku.setText(p.getSku());
                vistaProd.txtSku.setEditable(false);
                vistaProd.txtNombre.setText(p.getNombre());
                vistaProd.comboCategoriaPrincipal.setSelectedItem(p.getCategoriaPrincipal());
                vistaProd.comboSubCategoria.setSelectedItem(p.getSubCategoria());
                vistaProd.comboUnidad.setSelectedItem(p.getUnidadMedida());
                vistaProd.txtCantidad.setText(String.valueOf(p.getCantidadAlmacen()));
                vistaProd.txtPrecioCompra.setText(String.valueOf(p.getPrecioCompra()));
                vistaProd.txtPorcentajeGanancia.setText(String.valueOf(p.getPorcentajeGanancia()));
                calcularPrecioVenta();
                vistaProd.rutaFotoActual = p.getRutaFoto();

                // AQUÍ CARGAMOS LA FOTO SI EL PRODUCTO YA TENÍA UNA
                if (!p.getRutaFoto().equals("sin_foto.png")) {
                    File imgFile = new File(p.getRutaFoto());
                    if (imgFile.exists()) {
                        ImageIcon icono = new ImageIcon(imgFile.getAbsolutePath());
                        Image imgEscalada = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        vistaProd.lblFoto.setIcon(new ImageIcon(imgEscalada));
                        vistaProd.lblFoto.setText("");
                    } else {
                        vistaProd.lblFoto.setIcon(null);
                        vistaProd.lblFoto.setText("Foto no encontrada");
                    }
                } else {
                    vistaProd.lblFoto.setIcon(null);
                    vistaProd.lblFoto.setText("Sin Imagen");
                }

            } else {
                JOptionPane.showMessageDialog(vistaProd, "SKU no encontrado.");
            }
        }
    }

    private void modificarProducto() {
        if (vistaProd.txtSku.isEditable()) {
            JOptionPane.showMessageDialog(vistaProd, "Primero busque un producto.");
            return;
        }
        try {
            Producto p = capturarDatos();
            gestionProd.actualizar(p);
            vistaProd.txtSku.setEditable(true);
            guardarYRefrescar("Producto modificado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaProd, "Error en los datos numéricos.");
        }
    }

    private void eliminarProducto() {
        String sku = vistaProd.txtSku.getText();
        if (!sku.isEmpty()) {
            if (JOptionPane.showConfirmDialog(vistaProd, "¿Eliminar SKU " + sku + "?") == JOptionPane.YES_OPTION) {
                gestionProd.eliminar(sku);
                vistaProd.txtSku.setEditable(true);
                guardarYRefrescar("Producto eliminado.");
            }
        }
    }

    private Producto capturarDatos() {
        String sku = vistaProd.txtSku.getText();
        String nom = vistaProd.txtNombre.getText();
        String cat = vistaProd.comboCategoriaPrincipal.getSelectedItem().toString();
        String sub = vistaProd.comboSubCategoria.getSelectedItem().toString();
        String uni = vistaProd.comboUnidad.getSelectedItem().toString();
        int cant = Integer.parseInt(vistaProd.txtCantidad.getText());
        double pC = Double.parseDouble(vistaProd.txtPrecioCompra.getText());
        double gan = Double.parseDouble(vistaProd.txtPorcentajeGanancia.getText());
        String foto = vistaProd.rutaFotoActual;
        
        return new Producto(sku, nom, pC, gan, cant, foto, uni, cat, sub);
    }

    private void limpiarFormulario() {
        vistaProd.txtSku.setText("");
        vistaProd.txtSku.setEditable(true);
        vistaProd.txtNombre.setText("");
        vistaProd.txtCantidad.setText("");
        vistaProd.txtPrecioCompra.setText("");
        vistaProd.txtPorcentajeGanancia.setText("30");
        vistaProd.txtPrecioVenta.setText("");
        vistaProd.comboCategoriaPrincipal.setSelectedIndex(0);
        vistaProd.comboSubCategoria.setSelectedIndex(0);
        vistaProd.comboUnidad.setSelectedIndex(0);
        
        // Limpiamos la foto también
        vistaProd.rutaFotoActual = "sin_foto.png";
        vistaProd.lblFoto.setIcon(null);
        vistaProd.lblFoto.setText("Sin Imagen");
    }

    private void guardarYRefrescar(String msj) {
        csv.exportarCSV(gestionProd.getLista());
        actualizarTabla();
        limpiarFormulario();
        JOptionPane.showMessageDialog(vistaProd, msj);
    }

    public void actualizarTabla() {
        vistaProd.modeloTabla.setRowCount(0);
        for (Producto p : gestionProd.getLista()) {
            Object[] fila = {
                p.getSku(), p.getNombre(), p.getPrecioCompra(), p.getPorcentajeGanancia(), 
                String.format("%.2f", p.getPrecioVenta()), p.getCantidadAlmacen(), p.getUnidadMedida(), p.getCategoriaPrincipal()
            };
            vistaProd.modeloTabla.addRow(fila);
        }
    }
}