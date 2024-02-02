package ec.edu.puce.elecciones.formulario;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ec.edu.puce.elecciones.dominio.Curso;
import ec.edu.puce.elecciones.dominio.Estudiante;
import ec.edu.puce.elecciones.dominio.Mesa;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReportePadronElectoral extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Mesa> mesas;
	private List<Curso> cursos;
	private List<Estudiante> estudiantes;
	private String[] mesasTexto;
	private String[] cursosTexto;
	private JButton btnCancelar;
	private JLabel lblNombres;
	private JComboBox comboBox;
	private JLabel lblCiudad;
	private JComboBox comboBox_1;

	public ReportePadronElectoral(List<Mesa> mesas, List<Curso> cursos, List<Estudiante> estudiantes) {
		this.mesas = mesas;
		this.cursos = cursos;
		this.estudiantes = estudiantes;
		this.mesasTexto = new String[this.mesas.size()];
		int i = 0;
		for (Mesa mesa : this.mesas) {
			this.mesasTexto[i] = mesa.getNombre();
			i++;
		}
		
		setTitle("REPORTE DEL PADRÓN ELECTORAL");
		setBounds(100, 100, 600, 309);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 566, 167);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Estudiante", "Mesa Designada", "Voto"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(235, 244, 117, 25);
		getContentPane().add(btnCancelar);
		
		lblNombres = new JLabel("Mesa");
		lblNombres.setBounds(12, 21, 70, 15);
		getContentPane().add(lblNombres);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarMesa();
				llenarTabla();
				
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(this.mesasTexto));
		comboBox.setBounds(79, 12, 231, 24);
		getContentPane().add(comboBox);
		
		lblCiudad = new JLabel("Curso");
		lblCiudad.setBounds(12, 47, 70, 15);
		getContentPane().add(lblCiudad);
		this.cursosTexto = new String[this.mesas.get(comboBox.getSelectedIndex()).getCursos().size()];
		int j = 0;
		for (Curso curso : this.mesas.get(comboBox.getSelectedIndex()).getCursos()) {
			this.cursosTexto[j] = curso.getNombreCurso();
			j++;
		}
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla();
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(this.cursosTexto));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(79, 43, 231, 24);
		getContentPane().add(comboBox_1);
		
		
		llenarTabla();
	}

	
	private void llenarTabla() {
		model.setRowCount(0);
		for (Estudiante estudiante : estudiantes) {
			if(estudiante.getCurso().getNombreCurso() == this.mesas.get(this.comboBox.getSelectedIndex()).getCursos().get(this.comboBox_1.getSelectedIndex()).getNombreCurso()) {
				Object[] fila = new Object[3];
				fila[0] = estudiante.getNombre();
				fila[1] = estudiante.getCurso().getMesa().getNombre();
				if(estudiante.isHaVotado()) {
					fila[2] = "El estudiante ya ha votado";
				} else {
					fila[2] = "El estudiante no ha votado";
				}
				
				model.addRow(fila);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
	
	public void seleccionarMesa() {
		this.cursosTexto = new String[this.mesas.get(comboBox.getSelectedIndex()).getCursos().size()];
		int j = 0;
		for (Curso curso : this.mesas.get(comboBox.getSelectedIndex()).getCursos()) {
			this.cursosTexto[j] = curso.getNombreCurso();
			j++;
		}
		comboBox_1.setModel(new DefaultComboBoxModel(cursosTexto));
	}
	
	
}
