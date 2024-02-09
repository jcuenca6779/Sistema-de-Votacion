package ec.edu.puce.elecciones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ec.edu.puce.elecciones.dominio.Candidato;

import ec.edu.puce.elecciones.dominio.Curso;
import ec.edu.puce.elecciones.dominio.Estudiante;
import ec.edu.puce.elecciones.dominio.Mesa;
import ec.edu.puce.elecciones.formulario.CrearCandidato;
import ec.edu.puce.elecciones.formulario.CrearCurso;
import ec.edu.puce.elecciones.formulario.CrearEstudiante;
import ec.edu.puce.elecciones.formulario.CrearMesa;

import ec.edu.puce.elecciones.formulario.ReportePadronElectoral;
import ec.edu.puce.elecciones.formulario.ResultadoGeneral;
import ec.edu.puce.elecciones.formulario.ResultadoMesa;
import ec.edu.puce.elecciones.sufragio.Sufragar;
import ec.edu.puce.elecciones.sufragio.VistaLogin;

import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contenedor;
	private JDesktopPane desktopPane;
	private JMenuItem mntmSalir;
	private JMenuItem mntmMesas;

	public List<Candidato> candidatos = new ArrayList<>();
	public List<Mesa> mesas = new ArrayList<Mesa>();
	public List<Estudiante> estudiantes = new ArrayList<Estudiante>();
	public List<Curso> cursos = new ArrayList<Curso>();
	public String[] nombresMesas = {""};
	public String[] cursosMesa1 = {""};
	public String[] cursosMesa2 = {""};
	public String[] cursosMesa3 = {""};
	public int idCandidato = 1;
	public int idEstudiantes = 1;
	private JMenuItem mntmResultadosBarras;
	private JMenuItem mntmResultadosPastel;
	private JMenuItem mntmPadronElectoral;
	private JMenuItem mntmCandidatos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		
		
	
		setTitle("SISTEMA DE VOTO ELECTRÓNICO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(250, 247, 205));
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnArchivo);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setFont(new Font("Dialog", Font.BOLD, 16));
		mnArchivo.add(mntmSalir);

		JMenu mnAdministracin = new JMenu("Administración");
		mnAdministracin.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnAdministracin);

		mntmMesas = new JMenuItem("Mesas");
		mntmMesas.setFont(new Font("Dialog", Font.BOLD, 16));
		mntmMesas.addActionListener(this);
		mnAdministracin.add(mntmMesas);
		
		JMenuItem mntmCursos = new JMenuItem("Cursos");
		mntmCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cursoVentana();
			}
		});
		mntmCursos.setFont(new Font("Dialog", Font.BOLD, 16));
		mnAdministracin.add(mntmCursos);
		
		JMenuItem mntmEstudiantes = new JMenuItem("Estudiantes");
		mntmEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estudianteVentana();
			}
		});
		mntmEstudiantes.setFont(new Font("Dialog", Font.BOLD, 16));
		mnAdministracin.add(mntmEstudiantes);
		
		mntmCandidatos = new JMenuItem("Candidatos");
		mntmCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				candidatoVentana();
			}
		});
		mntmCandidatos.setFont(new Font("Dialog", Font.BOLD, 16));
		mnAdministracin.add(mntmCandidatos);
				
				JMenu mnNewMenu_1 = new JMenu("Proceso");
				mnNewMenu_1.setFont(new Font("Dialog", Font.BOLD, 16));
				menuBar.add(mnNewMenu_1);
				
				JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sufragar");
				mntmNewMenuItem_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sufragarVentana();
					}
				});
				mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 16));
				mnNewMenu_1.add(mntmNewMenuItem_2);
		
				JMenu mnReportes = new JMenu("Reportes");
				mnReportes.setForeground(new Color(0, 0, 0));
				mnReportes.setFont(new Font("Dialog", Font.BOLD, 16));
				menuBar.add(mnReportes);
				
						mntmPadronElectoral = new JMenuItem("Consultar Padrón Electoral");
						mntmPadronElectoral.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ReportePadronElectoral rpe = new ReportePadronElectoral(mesas, cursos, estudiantes);
								desktopPane.add(rpe);
								rpe.setVisible(true);
							}
						});
						mntmPadronElectoral.setFont(new Font("Dialog", Font.BOLD, 16));
						mnReportes.add(mntmPadronElectoral);
						
								JMenuItem mntmResultadosPorCantn = new JMenuItem("Resultados por cantón o ciudad");
								mntmResultadosPorCantn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
								mntmResultadosPorCantn.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JMenu mnNewMenu = new JMenu("Resultados");
		mnNewMenu.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Resultados por Mesa");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 16));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Resultados Generales");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Dialog", Font.BOLD, 16));
		mnNewMenu.add(mntmNewMenuItem_1);

	


		JMenu mnGrficos = new JMenu("Gráficos");
		mnGrficos.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnGrficos);

		mntmResultadosBarras = new JMenuItem("Resultados Barras");
		mnGrficos.add(mntmResultadosBarras);
		mntmResultadosBarras.addActionListener(this);
		mntmResultadosBarras.setFont(new Font("Dialog", Font.BOLD, 16));

		mntmResultadosPastel = new JMenuItem("Resultados Pastel");
		mnGrficos.add(mntmResultadosPastel);
		mntmResultadosPastel.addActionListener(this);
		mntmResultadosPastel.setFont(new Font("Dialog", Font.BOLD, 16));
		contenedor = new JPanel();
		contenedor.setBackground(new Color(255, 255, 255));
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		contenedor.add(desktopPane, "name_35522358088801");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Usuario\\Downloads\\Diseño sin título.png"));
		lblNewLabel.setBounds(0, 0, 776, 526);
		desktopPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSalir) {
			dispose();
		} else if (e.getSource() == mntmMesas) {
			CrearMesa crearMesa = new CrearMesa(mesas);
			desktopPane.add(crearMesa);
			crearMesa.setVisible(true);

	}
		else if (e.getSource() == mntmResultadosBarras) {
		crearResultadosEnBarras();
	} else if (e.getSource() == mntmResultadosPastel) {
		crearResultadosEnPastel();
	} 
	}
	
	public void candidatoVentana() {
		CrearCandidato crearCandidato = new CrearCandidato(candidatos, idCandidato);
		desktopPane.add(crearCandidato);
		crearCandidato.setVisible(true);
	}
	
	public void resultadosMVentana() {
		ResultadoMesa resultadoM = new ResultadoMesa(mesas);
		desktopPane.add(resultadoM);
		resultadoM.setVisible(true);
	}
	public void resultadosGVentana() {
		ResultadoGeneral resultadoG = new ResultadoGeneral();
		desktopPane.add(resultadoG);
		resultadoG.setVisible(true);
	}
	
	public void sufragarVentana() {
		VistaLogin login = new VistaLogin(estudiantes,candidatos);
		login.setVisible(true);
	}
	//public void sufragarVentana() {
	//Sufragar login = new Sufragar(candidatos);
	//desktopPane.add(login);
	//login.setVisible(true);
	//}
	
	public void estudianteVentana() {
		CrearEstudiante crearEstudiante = new CrearEstudiante(cursos, estudiantes, idEstudiantes);
		desktopPane.add(crearEstudiante);
		crearEstudiante.setVisible(true);
	}
	
	public void cursoVentana() {
		CrearCurso crearCurso = new CrearCurso(mesas, cursos);
		desktopPane.add(crearCurso);
		crearCurso.setVisible(true);
	}
	private void crearResultadosEnBarras() {
		final JInternalFrame frame = new JInternalFrame("Resultado en Barras", true);

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Candidato candidato : candidatos) {
			dataset.addValue(candidato.getVotos(), "Candidato", candidato.getNombre());
		}
		final JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "Category", "Series", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600, 400));

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		panel.add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}

	private void crearResultadosEnPastel() {
		final JInternalFrame frame = new JInternalFrame("Resultado en Pastel", true);

		DefaultPieDataset datos = new DefaultPieDataset();
		for (Candidato candidato : candidatos) {
			datos.setValue(candidato.getNombre(), candidato.getVotos());
		}

		JFreeChart grafico = ChartFactory.createPieChart("Candidatos", datos);
		ChartPanel chartPanel = new ChartPanel(grafico);
		chartPanel.setBounds(10, 50, 450, 350);

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		panel.add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}
}

