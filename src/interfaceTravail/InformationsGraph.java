package interfaceTravail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import elements.DegreSommets;
import elements.InfosGraph;
import elements.SommetPanel;
import javax.swing.JScrollPane;

public class InformationsGraph extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static String type;
	public static SommetPanel sm;
	private JTable tableDegres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			if(InfosGraph.simple)
				type = "Simple";
			if(InfosGraph.oriente)
				type = "Orienté";
			InformationsGraph dialog = new InformationsGraph(sm);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformationsGraph(SommetPanel sm) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformationsGraph.class.getResource("/Icons/About_icon.png")));
		InformationsGraph.sm = sm;
		
		this.setLocationRelativeTo(null); 
		setTitle("Information sur le graphe");
		setBounds(100, 100, 433, 552);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInfosGraph = new JLabel("Information sur ce graphe");
		lblInfosGraph.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblInfosGraph.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfosGraph.setBounds(10, 10, 416, 35);
		contentPanel.add(lblInfosGraph); 
		
		JLabel lblg = new JLabel("Type du graphe : ");
		lblg.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblg.setBounds(10, 55, 125, 33);
		contentPanel.add(lblg);

		JLabel lbls = new JLabel("Les sommets : ");
		lbls.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lbls.setBounds(10, 98, 125, 33);
		contentPanel.add(lbls);

		JLabel lbla = new JLabel("Les ar\u00EAtes : ");
		lbla.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lbla.setBounds(10, 184, 125, 33);
		contentPanel.add(lbla);

		JLabel lblSimpleOriente = new JLabel("");
		lblSimpleOriente.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblSimpleOriente.setBounds(145, 55, 90, 33);
		contentPanel.add(lblSimpleOriente);
		// ---- Simple / Orienté ----
		if(InfosGraph.simple)
			lblSimpleOriente.setText("Non orienté");
		if(InfosGraph.oriente)
			lblSimpleOriente.setText("Orienté");

		JLabel lblPondere = new JLabel("Pond\u00E9r\u00E9");
		lblPondere.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblPondere.setBounds(237, 55, 69, 33);
		contentPanel.add(lblPondere);
		// ---- Pondéré ----
		if(InfosGraph.pondere)
			lblPondere.setVisible(true);
		else
			lblPondere.setVisible(false);

		JLabel lblSommets = new JLabel("");
		lblSommets.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblSommets.setBounds(145, 98, 264, 33);
		contentPanel.add(lblSommets);
		lblSommets.setText(sm.getSommetsNames());

		JLabel lblAretes = new JLabel("");
		lblAretes.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblAretes.setBounds(145, 184, 264, 33);
		contentPanel.add(lblAretes);
		lblAretes.setText(sm.getAretesNames());

		JLabel lblOr = new JLabel("Ordre du graphe :");
		lblOr.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblOr.setBounds(10, 141, 137, 33);
		contentPanel.add(lblOr);

		JLabel lblOrdreGraph = new JLabel(Integer.toString(sm.getSommets().size()));
		lblOrdreGraph.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdreGraph.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblOrdreGraph.setBounds(157, 141, 60, 33);
		contentPanel.add(lblOrdreGraph);

		JLabel lblTai = new JLabel("Taille du graphe : ");
		lblTai.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblTai.setBounds(10, 227, 125, 33);
		contentPanel.add(lblTai);

		JLabel lblTailleGraph = new JLabel(Integer.toString(sm.getAretes().size()));
		lblTailleGraph.setHorizontalAlignment(SwingConstants.CENTER);
		lblTailleGraph.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblTailleGraph.setBounds(157, 227, 60, 33);
		contentPanel.add(lblTailleGraph);


		JLabel lblComp = new JLabel("Graphe complet ?");
		lblComp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblComp.setBounds(10, 270, 147, 33);
		contentPanel.add(lblComp);
// ---------------------------------------- Complet -------------------------------------------		
		JCheckBox chckbxOuiComplet = new JCheckBox("Oui");
		chckbxOuiComplet.setEnabled(false);
		chckbxOuiComplet.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxOuiComplet.setBackground(new Color(224, 255, 255));
		chckbxOuiComplet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxOuiComplet.setBounds(167, 270, 60, 33);
		contentPanel.add(chckbxOuiComplet);
		if(InfosGraph.complet)
			chckbxOuiComplet.setSelected(true);
		else
			chckbxOuiComplet.setSelected(false);
		
		JCheckBox chckbxNonComplet = new JCheckBox("Non");
		chckbxNonComplet.setEnabled(false);
		chckbxNonComplet.setBackground(new Color(224, 255, 255));
		chckbxNonComplet.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxNonComplet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNonComplet.setBounds(246, 270, 60, 33);
		contentPanel.add(chckbxNonComplet);
		if(!InfosGraph.complet)
			chckbxNonComplet.setSelected(true);
		else
			chckbxNonComplet.setSelected(false);
//----------------------------------------------------------------------------------------------		
		JLabel lblEul = new JLabel("Graphe eul\u00E9rien ?");
		lblEul.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblEul.setBounds(10, 313, 137, 33);
		contentPanel.add(lblEul);
// ---------------------------------------- Eulerien -------------------------------------------			
		JCheckBox chckbxOuiEulerien = new JCheckBox("Oui");
		chckbxOuiEulerien.setEnabled(false);
		chckbxOuiEulerien.setBackground(new Color(224, 255, 255));
		chckbxOuiEulerien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxOuiEulerien.setBounds(167, 313, 60, 33);
		contentPanel.add(chckbxOuiEulerien);
		if(InfosGraph.eulerien)
			chckbxOuiEulerien.setSelected(true);
		else
			chckbxOuiEulerien.setSelected(false);
		
		JCheckBox chckbxNonEulerien = new JCheckBox("Non");
		chckbxNonEulerien.setEnabled(false);
		chckbxNonEulerien.setBackground(new Color(224, 255, 255));
		chckbxNonEulerien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNonEulerien.setBounds(246, 313, 60, 33);
		contentPanel.add(chckbxNonEulerien);
		if(!InfosGraph.eulerien)
			chckbxNonEulerien.setSelected(true);
		else
			chckbxNonEulerien.setSelected(false);
// ---------------------------------------------------------------------------------------------
		JLabel lblChaine = new JLabel("Admet une cha\u00EEne eul\u00E9rienne");
		lblChaine.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblChaine.setBounds(167, 352, 211, 31);
		contentPanel.add(lblChaine);
		if(InfosGraph.chaineEulerienne)
			lblChaine.setVisible(true);
		else
			lblChaine.setVisible(false);
		
		JLabel lblRegulier = new JLabel("Graphe r\u00E9gulier ?");
		lblRegulier.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblRegulier.setBounds(10, 391, 125, 33);
		contentPanel.add(lblRegulier);
// ---------------------------------------- Regulier -------------------------------------------			
		JCheckBox chckbxOuiRegulier = new JCheckBox("Oui");
		chckbxOuiRegulier.setEnabled(false);
		chckbxOuiRegulier.setBackground(new Color(224, 255, 255));
		chckbxOuiRegulier.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxOuiRegulier.setBounds(167, 391, 60, 33);
		contentPanel.add(chckbxOuiRegulier);
		if(InfosGraph.regulier)
			chckbxOuiRegulier.setSelected(true);
		else
			chckbxOuiRegulier.setSelected(false);
		
		JCheckBox chckbxNonRegulier = new JCheckBox("Non");
		chckbxNonRegulier.setEnabled(false);
		chckbxNonRegulier.setBackground(new Color(224, 255, 255));
		chckbxNonRegulier.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNonRegulier.setBounds(246, 391, 60, 33);
		contentPanel.add(chckbxNonRegulier);
		if(!InfosGraph.regulier)
			chckbxNonRegulier.setSelected(true);
		else
			chckbxNonRegulier.setSelected(false);
// --------------------------------------------------------------------------------------------
		JLabel lblKRegulier = new JLabel(Integer.toString(sm.getSommets().get(0).getDegree()));
		lblKRegulier.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKRegulier.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblKRegulier.setBounds(312, 391, 26, 33);
		contentPanel.add(lblKRegulier);
		if(InfosGraph.regulier)
			lblKRegulier.setVisible(true);
		else
			lblKRegulier.setVisible(false);
		
		JLabel lblReguliertxt = new JLabel("- R\u00E9gulier");
		lblReguliertxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblReguliertxt.setBounds(342, 391, 69, 33);
		contentPanel.add(lblReguliertxt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 460, 399, 46);
		contentPanel.add(scrollPane);
		
		tableDegres = new JTable(new DegreSommets(sm.getSommets()));
		tableDegres.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		scrollPane.setViewportView(tableDegres);
		tableDegres.setBackground(new Color(224, 255, 255));
		
		JLabel lblDegres = new JLabel("Liste des sommets et leur degr\u00E9 :");
		lblDegres.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblDegres.setBounds(10, 427, 275, 33);
		contentPanel.add(lblDegres);
		if(InfosGraph.regulier)
			lblReguliertxt.setVisible(true);
		else
			lblReguliertxt.setVisible(false);
	}
}
