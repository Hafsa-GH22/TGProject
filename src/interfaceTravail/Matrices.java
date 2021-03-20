package interfaceTravail;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elements.InfosGraph;
import elements.MatriceAdjacenceModel;
import elements.MatriceArcModel;
import elements.MatriceIncidenceModel;
import elements.Sommet;
import elements.SommetPanel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class Matrices extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableMatriceAdj;
	private JScrollPane scrollPaneMatriceAdj;
	public static int [][] matriceAdj;
	public static int [][] matriceInc;
	public static String [][] matriceArc;
	private static String liste;
	public static ArrayList<Sommet> sommets = new ArrayList<Sommet>();
	private JLabel typeGraphComplet;
	private JTable tableMatriceInc;
	private JScrollPane scrollPaneMatriceInc;
	private JTable tableMatriceArc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Matrices dialog = new Matrices(matriceAdj, matriceInc, matriceArc, liste, sommets);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Matrices(int [][] matriceAdj, int[][] matriceInc, String[][] matriceArc, String liste, ArrayList<Sommet> sommets) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Matrices.class.getResource("/Icons/matrix_icon.png")));
		Matrices.matriceAdj = matriceAdj;
		Matrices.matriceInc = matriceInc;
		Matrices.matriceArc = matriceArc;
		this.liste = liste;
		Matrices.sommets = sommets;

		this.setLocationRelativeTo(null);
		setTitle("Repr\u00E9sentation du graphe");
		setBounds(100, 100, 824, 471);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAdj = new JLabel("Matrice d'adjacence");
		lblAdj.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdj.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblAdj.setBounds(10, 10, 390, 35);
		contentPanel.add(lblAdj);
		
		scrollPaneMatriceAdj = new JScrollPane();
		scrollPaneMatriceAdj.setBounds(10, 50, 390, 138);
		contentPanel.add(scrollPaneMatriceAdj);
		
		tableMatriceAdj = new JTable(new MatriceAdjacenceModel(matriceAdj, sommets));
		//this.tableMatrice.setModel(new MatriceAdjacenceModel(MatriceAdjacence.matriceAdj, MatriceAdjacence.sommets));
		scrollPaneMatriceAdj.setViewportView(tableMatriceAdj);
		tableMatriceAdj.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		tableMatriceAdj.setBackground(new Color(240, 255, 255));
		
		typeGraphComplet = new JLabel("Graphe Complet");
		typeGraphComplet.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		typeGraphComplet.setBounds(10, 391, 178, 35);
		contentPanel.add(typeGraphComplet);
		
		scrollPaneMatriceInc = new JScrollPane();
		scrollPaneMatriceInc.setBounds(410, 50, 390, 138);
		contentPanel.add(scrollPaneMatriceInc);
		
		tableMatriceInc = new JTable(new MatriceIncidenceModel(matriceInc, sommets));
		tableMatriceInc.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		scrollPaneMatriceInc.setViewportView(tableMatriceInc);
		tableMatriceInc.setBackground(new Color(240, 255, 255));
		
		JLabel lblinc = new JLabel("Matrice d'incidence");
		lblinc.setHorizontalAlignment(SwingConstants.CENTER);
		lblinc.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblinc.setBounds(410, 10, 390, 35);
		contentPanel.add(lblinc);
		
		JLabel lblArc = new JLabel("Matrice aux arcs");
		lblArc.setHorizontalAlignment(SwingConstants.CENTER);
		lblArc.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblArc.setBounds(10, 198, 390, 35);
		contentPanel.add(lblArc);
		
		JScrollPane scrollPaneMatriceArc = new JScrollPane();
		scrollPaneMatriceArc.setBounds(10, 243, 390, 138);
		contentPanel.add(scrollPaneMatriceArc);
		
		tableMatriceArc = new JTable(new MatriceArcModel(matriceArc, sommets));
		tableMatriceArc.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		tableMatriceArc.setBackground(new Color(240, 255, 255));
		scrollPaneMatriceArc.setViewportView(tableMatriceArc);
		
		JLabel lblTitleListe = new JLabel("Liste du graphe");
		lblTitleListe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleListe.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblTitleListe.setBounds(410, 198, 390, 35);
		contentPanel.add(lblTitleListe);
		
		JScrollPane scrollPaneListe = new JScrollPane();
		scrollPaneListe.setBounds(410, 243, 390, 138);
		contentPanel.add(scrollPaneListe);
		
		JTextArea textAreaListe = new JTextArea(liste);
		scrollPaneListe.setViewportView(textAreaListe);
		textAreaListe.setBackground(new Color(240, 255, 255));
		textAreaListe.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		
		JLabel lblnoncomplet = new JLabel("Graphe non complet");
		lblnoncomplet.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		lblnoncomplet.setBounds(198, 391, 202, 35);
		contentPanel.add(lblnoncomplet);
		
		lblnoncomplet.setVisible(false);
		typeGraphComplet.setVisible(false);
		if(InfosGraph.complet)
			typeGraphComplet.setVisible(true);
		else
			lblnoncomplet.setVisible(true);
	}
}
