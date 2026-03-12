package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class EjemploTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	// NUEVO: Declaramos el cuadro de texto para buscar
	private JTextField txtBuscar; 

	public JTable getTable() {
		return table;
	}

	// NUEVO: Método para que el controlador pueda usar el cuadro de texto
	public JTextField getTxtBuscar() { 
		return txtBuscar;
	}

	public EjemploTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Cambiamos CardLayout por BorderLayout para acomodar el buscador arriba
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// --- NUEVO: Panel superior para el buscador ---
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSuperior.add(new JLabel("Buscar (Alumno, Matrícula, Materia): "));
		
		txtBuscar = new JTextField(30); // Cuadro de texto de 30 columnas de ancho
		panelSuperior.add(txtBuscar);
		
		contentPane.add(panelSuperior, BorderLayout.NORTH); // Lo ponemos arriba
		// ----------------------------------------------
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER); // La tabla va al centro
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}