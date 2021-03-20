package interfaceTravail;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class InformationSommet extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JPanel parent;
	public static Sommet s;
	public static JTextField txtNom;
	public static JTextField txtDegree;
	public static JTextField txtSommetEntr;
	public static JTextField txtSommetSort;
	public static int n;
	//private int degree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformationSommet dialog = new InformationSommet(s, n);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformationSommet(Sommet s, int n) {
		setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformationSommet.class.getResource("/Icons/Info_icon.png")));
		InformationSommet.s = s;
		this.n = n;
		/*if(InfosGraph.simple)
			degree = s.sommetEntrant.size();
		if(InfosGraph.oriente)
			degree = s.sommetEntrant.size() + s.sommetSortant.size();*/
		this.setLocationRelativeTo(null);
		setTitle("Informations sur le sommet");
		setBounds(100, 100, 295, 270);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInfos = new JLabel("Informations sur ce sommet");
		lblInfos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfos.setBounds(10, 10, 261, 35);
		contentPanel.add(lblInfos);
		
		JLabel lblNom = new JLabel("Nom du sommet : ");
		lblNom.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNom.setBounds(20, 53, 145, 33);
		contentPanel.add(lblNom);
		
		JLabel lblDegree = new JLabel("Degr\u00E9 : ");
		lblDegree.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblDegree.setBounds(20, 94, 145, 33);
		contentPanel.add(lblDegree);
		
		JLabel lblSommetEntr = new JLabel("Sommets entrants : ");
		lblSommetEntr.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblSommetEntr.setBounds(20, 135, 145, 33);
		contentPanel.add(lblSommetEntr);
		
		JLabel lblSommetSort = new JLabel("Sommets sortants : ");
		lblSommetSort.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblSommetSort.setBounds(20, 177, 145, 33);
		contentPanel.add(lblSommetSort);
		
		txtNom = new JTextField(s.getNameSommet());
		txtNom.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		txtNom.setHorizontalAlignment(SwingConstants.CENTER);
		txtNom.setBackground(new Color(240, 255, 240));
		txtNom.setEditable(false);
		txtNom.setBounds(194, 55, 77, 33);
		contentPanel.add(txtNom);
		txtNom.setColumns(10);
		
		txtDegree = new JTextField(Integer.toString(s.getDegree()));
		txtDegree.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		txtDegree.setHorizontalAlignment(SwingConstants.CENTER);
		txtDegree.setBackground(new Color(240, 255, 255));
		txtDegree.setEditable(false);
		txtDegree.setBounds(194, 96, 77, 33);
		contentPanel.add(txtDegree);
		txtDegree.setColumns(10);
		
		txtSommetEntr = new JTextField(s.getEntr());//Integer.toString(s.getNbreSommetEnt()));
		txtSommetEntr.setEditable(false);
		txtSommetEntr.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		txtSommetEntr.setHorizontalAlignment(SwingConstants.CENTER);
		txtSommetEntr.setBackground(new Color(240, 255, 255));
		txtSommetEntr.setBounds(194, 137, 77, 33);
		contentPanel.add(txtSommetEntr);
		txtSommetEntr.setColumns(10);
		
		txtSommetSort = new JTextField(s.getSort());//Integer.toString(s.getNbreSommetSort()));
		txtSommetSort.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		txtSommetSort.setHorizontalAlignment(SwingConstants.CENTER);
		txtSommetSort.setBackground(new Color(240, 255, 255));
		txtSommetSort.setEditable(false);
		txtSommetSort.setBounds(194, 179, 77, 33);
		contentPanel.add(txtSommetSort);
		txtSommetSort.setColumns(10);
		
		JLabel lblTypeDegre = new JLabel();
		lblTypeDegre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeDegre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTypeDegre.setBounds(79, 96, 112, 33);
		contentPanel.add(lblTypeDegre);
		if(s.getDegree() == 0)
			lblTypeDegre.setText("(Isolé)");
		if(s.getDegree() == 1)
			lblTypeDegre.setText("(Pendant(Feuille))");
		if(s.getDegree() == n - 1)
			lblTypeDegre.setText("(Dominant)");
	}
}
