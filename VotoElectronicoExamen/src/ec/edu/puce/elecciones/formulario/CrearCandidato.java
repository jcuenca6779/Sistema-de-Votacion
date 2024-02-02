package ec.edu.puce.elecciones.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Candidato;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CrearCandidato extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Candidato candidato;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private List<Candidato> candidatos;
	private int idCandidato;
	private JLabel lblLista;
	private JTextField textField;
	
	public CrearCandidato(List<Candidato> candidatos, int idCandidato) {
		this.idCandidato=idCandidato;
		this.candidatos=candidatos;
		setTitle("CREAR CANDIDATOS");
		setBounds(100, 100, 443, 385);
		getContentPane().setLayout(null);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(30, 25, 70, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCandidato();
			}
		});
		txtNombre.setBounds(99, 22, 231, 19);
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lista", "Nombre"
			}
		));
		scrollPane.setViewportView(table);
		
		lblLista = new JLabel("Lista:");
		lblLista.setBounds(30, 47, 70, 15);
		getContentPane().add(lblLista);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(99, 43, 231, 19);
		getContentPane().add(textField);

		model = (DefaultTableModel) table.getModel();
		agregarFila();
	}

	private void nuevo() {
		candidato = new Candidato();
		txtNombre.setText(candidato.getNombre());
	}
	
	

	private void agregarCandidato() {
		candidato = new Candidato();
		candidato.setId(idCandidato);
		candidato.setNombre(txtNombre.getText());
		candidato.setLista(textField.getText());
		candidatos.add(candidato);
		agregarFila();
		txtNombre.setText("");
		idCandidato++;
		
	}

	private void agregarFila() {
		model.setRowCount(0);
		for (Candidato candidato : candidatos) {
			Object[] fila = new Object[2];
			fila[0] = candidato.getNombre();
			fila[1] = candidato.getLista();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarCandidato();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource() == txtNombre) {
			agregarCandidato();
		}
	}	
}
