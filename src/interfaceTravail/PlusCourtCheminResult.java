package interfaceTravail;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import elements.DijkstraModel;
import elements.Sommet;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class PlusCourtCheminResult extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableResult;
	
	public static JFrame parent;
	public static Sommet first;
	public static HashMap<String,Double> l;
	public static HashMap<String,String> p;
	public static String nameAlgo = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlusCourtCheminResult dialog = new PlusCourtCheminResult(parent ,first, l, p, nameAlgo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlusCourtCheminResult(JFrame parent, Sommet first, HashMap<String,Double> l, HashMap<String,String> p, String nameAlgo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlusCourtCheminResult.class.getResource("/Icons/algorithm_icon.png")));
		setTitle("Plus court chemin");
		//super(parent, true);
		this.parent= parent;
        this.first = first;
        this.l = l;
        this.p = p;
        this.nameAlgo = nameAlgo;
        
		setBounds(100, 100, 514, 408);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 240, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 134, 477, 60);
		contentPanel.add(scrollPane);
		
		tableResult = new JTable();
		tableResult.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		tableResult.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(tableResult);
		
		JTextArea textAreaResult = new JTextArea();
		textAreaResult.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		textAreaResult.setBackground(new Color(224, 255, 255));
		textAreaResult.setBounds(13, 230, 477, 131);
		contentPanel.add(textAreaResult);
		
		JLabel lblDepart = new JLabel("");
		lblDepart.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		lblDepart.setBounds(169, 53, 103, 41);
		contentPanel.add(lblDepart);
		
		lblDepart.setText(first.getNameSommet());
		tableResult.setModel(new DijkstraModel(this.l, this.p));
		
		StringBuffer sb = new StringBuffer();
        
        for (Map.Entry<String, Double> entry : l.entrySet()) {
            String key = entry.getKey(); 
            Double value = entry.getValue();
            
            if(!key.equals(first.getNameSommet())){
                
                StringBuffer line = new StringBuffer();
                line.append(" : "+value+"");
                line.insert(0, " -> " + key);
                String pre = p.get(key);
                while((pre!=null) && !pre.equals(first.getNameSommet())){
                    line.insert(0, " -> " + pre);
                    pre = p.get(pre);
                }
                line.insert(0, pre);
                if(pre!=null)sb.append(line.toString() + "\n");
            }
        }
        textAreaResult.setText(sb.toString());
        
        JLabel lblTitle = new JLabel("R\u00E9sultat de l'algorithme de ");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
        lblTitle.setBounds(10, 10, 480, 41);
        contentPanel.add(lblTitle);
        lblTitle.setText(lblTitle.getText() + nameAlgo);
        
        JLabel lblsmd = new JLabel("Sommet de d\u00E9part : ");
        lblsmd.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
        lblsmd.setBounds(13, 53, 167, 41);
        contentPanel.add(lblsmd);
        
        JLabel lblres = new JLabel("R\u00E9sultats : ");
        lblres.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
        lblres.setBounds(13, 99, 155, 33);
        contentPanel.add(lblres);
        
        JLabel lblInterp = new JLabel("Interpr\u00E9tation : ");
        lblInterp.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
        lblInterp.setBounds(13, 195, 167, 33);
        contentPanel.add(lblInterp);
	}
}
