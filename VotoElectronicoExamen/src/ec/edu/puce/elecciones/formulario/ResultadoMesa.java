package ec.edu.puce.elecciones.formulario;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import ec.edu.puce.elecciones.dominio.Mesa;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultadoMesa extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private List<Mesa> mesas;
	private String[] nombreMesas;
	private JTable table;
	
	public ResultadoMesa(List<Mesa> mesas) {
		this.mesas = mesas;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		int i = 0;
		this.nombreMesas = new String[mesas.size()];
		for (Mesa mesa : mesas) {
			nombreMesas[i] = mesa.getNombre();
			i++;
		}
		
		JLabel lblNombres = new JLabel("Mesa");
		lblNombres.setBounds(30, 16, 70, 15);
		getContentPane().add(lblNombres);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(77, 10, 298, 26);
		getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 418, 203);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Candidatos", "Cantidad de Votos"
			}
		));
		scrollPane.setViewportView(table);
	}
}
