package ec.edu.puce.elecciones.formulario;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ResultadoGeneral extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultadoGeneral frame = new ResultadoGeneral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResultadoGeneral() {
		setBounds(100, 100, 450, 300);

	}

}
