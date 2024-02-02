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

public class CrearCurso extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Curso curso;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private String[] nombreMesas;
	private List<Curso> cursos;
	private List<Mesa> mesas;
	private JComboBox comboBox;
	
	public CrearCurso(List<Mesa> mesas, List<Curso> cursos) {
		this.cursos=cursos;
		this.mesas = mesas;
		setTitle("CREAR CURSOS Y ASIGNAR MESAS");
		setBounds(100, 100, 443, 385);
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

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCurso();
			}
		});
		txtNombre.setBounds(130, 42, 200, 19);
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
		
		JLabel lblNombres_1 = new JLabel("Nombre Curso");
		lblNombres_1.setBounds(30, 46, 90, 15);
		getContentPane().add(lblNombres_1);
		
		comboBox = new JComboBox(new DefaultComboBoxModel(this.nombreMesas));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarFilas();
			}
		});
		comboBox.setBounds(130, 11, 200, 24);
		getContentPane().add(comboBox);

		model = (DefaultTableModel) table.getModel();
		cambiarFilas();
	}

	private void nuevo() {
		curso = new Curso("", null);
		txtNombre.setText(curso.getNombreCurso());
	}
	
	private void cambiarFilas() {
		model.setRowCount(0);
		for (Curso curso : cursos) {
			if(curso.getMesa().getNombre() == mesas.get(this.comboBox.getSelectedIndex()).getNombre()) {
				Object[] fila = new Object[1];
				fila[0] = curso.getNombreCurso();
				model.addRow(fila);
			}
		}
	}

	private void agregarCurso() {
		curso = new Curso(txtNombre.getText(), mesas.get(this.comboBox.getSelectedIndex()));
		cursos.add(curso);
		mesas.get(this.comboBox.getSelectedIndex()).getCursos().add(curso);
		agregarFila();
		cambiarFilas();
		txtNombre.setText("");
		
	}

	private void agregarFila() {
		model.setRowCount(0);
		for (Curso curso : cursos) {
			Object[] fila = new Object[1];
			fila[0] = curso.getNombreCurso();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarCurso();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource() == txtNombre) {
			agregarCurso();
		}
	}	
}
