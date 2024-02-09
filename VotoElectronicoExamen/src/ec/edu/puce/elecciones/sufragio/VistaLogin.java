package ec.edu.puce.elecciones.sufragio;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import ec.edu.puce.elecciones.dominio.Candidato;
import ec.edu.puce.elecciones.dominio.Estudiante;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	private JTextField textCedula;
	
	public VistaLogin(List<Estudiante> estudiantes, List<Candidato> candidatos) {
		setBackground(new Color(240, 240, 240));
		setBounds(100, 100, 450, 205);
		getContentPane().setLayout(null);
		
		textCedula = new JTextField();
		textCedula.setBounds(87, 63, 251, 38);
		getContentPane().add(textCedula);
		textCedula.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("¡Bienvenido a las Elecciones PUCEM 2024!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(42, 10, 362, 31);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedulaIngresada = textCedula.getText();
				 boolean encontrado = false;
			        for (Estudiante estudiante : estudiantes) {
			            if (estudiante.getCedula().equals(cedulaIngresada)) {
			                encontrado = true;
			                estudiante.setHaVotado(true);
			            }
			        }
			        if (encontrado) {
			            Sufragar sufragar = new Sufragar(candidatos);
			    		sufragar.setVisible(true);
			    		dispose();
			        } else {
			        	
			            JOptionPane.showMessageDialog(null, "Cédula incorrecta", "Error de Login", JOptionPane.ERROR_MESSAGE);
			        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(157, 111, 111, 38);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Cedula del Estudiante:");
		lblNewLabel_1.setBounds(87, 50, 142, 13);
		getContentPane().add(lblNewLabel_1);

	}
}
