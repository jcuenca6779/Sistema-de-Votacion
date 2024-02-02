package ec.edu.puce.elecciones.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Candidato;
import ec.edu.puce.elecciones.dominio.Curso;
import ec.edu.puce.elecciones.dominio.Estudiante;
import ec.edu.puce.elecciones.dominio.Mesa;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

public class CrearEstudiante extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Estudiante estudiante;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private String[] nombreCursos;
	private List<Estudiante> estudiantes;
	private List<Curso> cursos;
	private int idEstudiante;
	private JComboBox comboBox;
	
	public CrearEstudiante(List<Curso> cursos, List<Estudiante> estudiantes, int idEstudiante) {
		this.idEstudiante=idEstudiante;
		this.estudiantes=estudiantes;
		this.cursos = cursos;
		setTitle("CREAR ESTUDIANTES POR PARALELO");
		setBounds(100, 100, 443, 385);
		getContentPane().setLayout(null);
		
		
		int i = 0;
		this.nombreCursos = new String[cursos.size()];
		for (Curso curso : cursos) {
			nombreCursos[i] = curso.getNombreCurso();
			i++;
		}
		
		JLabel lblNombres = new JLabel("Curso");
		lblNombres.setBounds(30, 16, 70, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarEstudiante();
			}
		});
		txtNombre.setBounds(99, 42, 231, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(30, 72, 117, 25);
		getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(157, 72, 117, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(286, 72, 117, 25);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 416, 224);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre" }));
		scrollPane.setViewportView(table);
		
		JLabel lblNombres_1 = new JLabel("Nombres");
		lblNombres_1.setBounds(30, 46, 70, 15);
		getContentPane().add(lblNombres_1);
		
		comboBox = new JComboBox(new DefaultComboBoxModel(this.nombreCursos));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarFilas();
			}
		});
		comboBox.setBounds(99, 11, 231, 24);
		getContentPane().add(comboBox);

		model = (DefaultTableModel) table.getModel();
		cambiarFilas();
	}

	private void nuevo() {
		estudiante = new Estudiante();
		txtNombre.setText(estudiante.getNombre());
	}
	
	private void cambiarFilas() {
		model.setRowCount(0);
		/*if(this.comboBox.getSelectedIndex()<0) {
			return;
		}*/
		for (Estudiante estudiante : estudiantes) {
			if(estudiante.getCurso().getNombreCurso() == cursos.get(this.comboBox.getSelectedIndex()).getNombreCurso()) {
				Object[] fila = new Object[1];
				fila[0] = estudiante.getNombre();
				model.addRow(fila);
			}
		}
	}

	private void agregarEstudiante() {
		estudiante = new Estudiante();
		estudiante.setId(idEstudiante);
		estudiante.setNombre(txtNombre.getText());
		estudiante.setCurso(cursos.get(this.comboBox.getSelectedIndex()));
		estudiante.setClavePersonal("123456");
		estudiantes.add(estudiante);
		agregarFila();
		cambiarFilas();
		txtNombre.setText("");
		idEstudiante++;
		
	}

	private void agregarFila() {
		model.setRowCount(0);
		for (Estudiante estudiante : estudiantes) {
			Object[] fila = new Object[1];
			fila[0] = estudiante.getNombre();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarEstudiante();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource() == txtNombre) {
			agregarEstudiante();
		}
	}	
}
