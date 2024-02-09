package ec.edu.puce.elecciones.sufragio;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import ec.edu.puce.elecciones.dominio.Candidato;
import ec.edu.puce.elecciones.dominio.Estudiante;


public class Sufragar extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private List<Candidato> candidatos;
	private List<Estudiante> estudiantes;
	private JPanel panel;
	
	

	public Sufragar(List<Candidato> candidatos) {
		this.candidatos = candidatos;
		panel = new JPanel();
		setBounds(100, 100, 680, 295);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Eliga un candidato para votarlo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 18));
		lblNewLabel.setBounds(177, 26, 340, 48);
		getContentPane().add(lblNewLabel);
		
		panel.setBounds(45, 97, 593, 127); 
	    getContentPane().add(panel);
        panel.setLayout(null);
        
        cargarCandidatos();
      
	}
	 private void cargarCandidatos() {
	        int x = 0;
	        panel.removeAll();
	        for (Candidato candidato : candidatos) {
	                JButton btnCandidato = new JButton(candidato.getNombre());
	                btnCandidato.setBounds(x * 155, 0, 150, 80);
	                btnCandidato.addActionListener(this);
	                panel.add(btnCandidato);
	                x++;
	        }
	        panel.revalidate();
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		for (Candidato candidato : candidatos) {
            if (candidato.getNombre().equals(((JButton) e.getSource()).getText())) {
                candidato.setVotos(candidato.getVotos() + 1);
                mostrarMensajeTemporal("¡Gracias por su voto!");
            }
		}
	}
	private void mostrarMensajeTemporal(String mensaje) {
	    JOptionPane.showMessageDialog(this, mensaje);
	    Timer timer = new Timer(1000, e -> {
	        volverAVistaLogin(); // Vuelve a la pantalla de login después de 5 segundos
	    });
	    timer.setRepeats(false); // No se repite, solo se ejecuta una vez
	    timer.start();
	}
	

	private void volverAVistaLogin() {
	    VistaLogin vistaLogin = new VistaLogin(estudiantes, candidatos); 
	    vistaLogin.setVisible(true);
	    this.dispose(); 
	}
}
