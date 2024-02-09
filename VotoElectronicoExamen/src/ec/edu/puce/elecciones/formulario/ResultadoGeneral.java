package ec.edu.puce.elecciones.formulario;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultadoGeneral extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	
	public ResultadoGeneral() {
		setBounds(100, 100, 450, 272);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 418, 203);
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
