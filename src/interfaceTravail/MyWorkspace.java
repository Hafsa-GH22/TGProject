package interfaceTravail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import algorithmes.BFS;
import algorithmes.BellmanFord;
import algorithmes.Coloriage;
import algorithmes.DFS;
import algorithmes.Dijkstra;
import algorithmes.Kruskal;
import algorithmes.Marquage;
import algorithmes.Prim;
import algorithmes.Residuelle;
import algorithmes.Wareshall;
import elements.InfosGraph;
import elements.SommetPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class MyWorkspace {

	public static JFrame frmWorkspace;
	JLabel sommet;
	JLabel lblNewLabel;
	public String type;
	public static JTextField txtSommet;
	public static JTextField txtArete;
	public static JTextField txtDensite;
	public static JTextArea txtTrace;
	public static JButton btnMatriceAdjacence;
	public static JButton btnBFS;
	public static JButton btnDFS;
	public static JButton btnWareshall;
	public static JButton btnPrim;
	public static JButton btnKruskal;
	public static JButton btnDijkstra;
	public static JButton btnBellmanFord;
	public static JButton btnMarquage;
	public static JButton btnResiduelle;
	public static JButton btnColoriage;
	public static SommetPanel pnlSm;
	public static JScrollPane jscrollSommet;
	public static JPanel panelGraph;
	public static int details = 0;
	
	public static int [][] matriceAdj;
	
	public void setLbl(String text)
	{
		lblNewLabel.setText(text);
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getType()
	{
		return this.type;
	}
	public void setSommet(String txt)
	{
		txtSommet.setText(txt);
	}
	
	public MyWorkspace(String type)
	{
		if(type == "Non orienté")
		{
			initialize();
			InfosGraph.simple = true;
			MyWorkspace.btnColoriage.setEnabled(true);
		}
		if(type == "Orienté")
		{
			initialize();
			InfosGraph.oriente = true;
		}
		if(type == "Non orienté et pondéré")
		{
			initialize();
			InfosGraph.simple = true;
			InfosGraph.pondere = true;
			MyWorkspace.btnPrim.setEnabled(true);
			MyWorkspace.btnKruskal.setEnabled(true);
			MyWorkspace.btnColoriage.setEnabled(true);
		}
		if(type == "Orienté et pondéré")
		{
			initialize();
			InfosGraph.oriente = true;
			InfosGraph.pondere = true;
			MyWorkspace.btnMarquage.setEnabled(true);
			MyWorkspace.btnResiduelle.setEnabled(true);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					MyWorkspace window = new MyWorkspace();
					window.frmWorkspace.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyWorkspace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWorkspace = new JFrame();
		frmWorkspace.setIconImage(Toolkit.getDefaultToolkit().getImage(MyWorkspace.class.getResource("/Icons/workspace_icon.png")));
		frmWorkspace.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		frmWorkspace.setTitle("Th\u00E9orie des graphes");
		frmWorkspace.setBackground(new Color(255, 245, 238));
		frmWorkspace.setBounds(100, 100, 1200, 690);
		frmWorkspace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWorkspace.setLocationRelativeTo(null);
		
		JPanel principalPanel = new JPanel();
		principalPanel.setBackground(new Color(255, 245, 238));
		frmWorkspace.getContentPane().add(principalPanel, BorderLayout.CENTER);
		principalPanel.setLayout(null);
		
		JPanel pnlInfos = new JPanel();
		pnlInfos.setBackground(new Color(226,254,242));
		pnlInfos.setBounds(10, 10, 293, 217);
		principalPanel.add(pnlInfos);
		pnlInfos.setLayout(null);
		
		JLabel lblDensite = new JLabel("Densit\u00E9 : ");
		lblDensite.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblDensite.setBounds(20, 175, 186, 33);
		pnlInfos.add(lblDensite);
		
//		SommetPanel sp = new SommetPanel();
		txtSommet = new JTextField();
		txtSommet.setBackground(new Color(255, 245, 238));
		txtSommet.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtSommet.setHorizontalAlignment(SwingConstants.CENTER);
		txtSommet.setText("0");
		txtSommet.setEditable(false);
		txtSommet.setBounds(233, 91, 50, 33);
		pnlInfos.add(txtSommet);
		txtSommet.setColumns(10);
		
		JLabel lblArrete = new JLabel("Nombre d'ar\u00EAtes (Arcs) :");
		lblArrete.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblArrete.setBounds(20, 132, 186, 33);
		pnlInfos.add(lblArrete);
		
		JLabel lblSommet = new JLabel("Nombre de sommets : ");
		lblSommet.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblSommet.setBounds(20, 89, 186, 33);
		pnlInfos.add(lblSommet);
		
		txtDensite = new JTextField();
		txtDensite.setBackground(new Color(255, 245, 238));
		txtDensite.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtDensite.setEditable(false);
		txtDensite.setHorizontalAlignment(SwingConstants.CENTER);
		txtDensite.setText("0");
		txtDensite.setBounds(233, 175, 50, 33);
		pnlInfos.add(txtDensite);
		txtDensite.setColumns(10);
		
		txtArete = new JTextField();
		txtArete.setBackground(new Color(255, 245, 238));
		txtArete.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtArete.setText("0");
		txtArete.setHorizontalAlignment(SwingConstants.CENTER);
		txtArete.setEditable(false);
		txtArete.setBounds(233, 134, 50, 33);
		pnlInfos.add(txtArete);
		txtArete.setColumns(10);
		
		JLabel lblInfos = new JLabel("Informations sur le graphe");
		lblInfos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 19));
		lblInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfos.setBounds(10, 47, 273, 33);
		pnlInfos.add(lblInfos);
		
		btnMatriceAdjacence = new JButton(" Les matrices du graphe");
		btnMatriceAdjacence.setIcon(new ImageIcon(MyWorkspace.class.getResource("/Icons/matrix_icon.png")));
		btnMatriceAdjacence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlSm.isComplet();
				//pnlSm.liste();
				new Matrices(pnlSm.getMatriceAdjacence(), pnlSm.getMatriceIncidence(), pnlSm.getMatriceArc(), pnlSm.liste(), pnlSm.getSommets()).setVisible(true);;
			}
		});
		btnMatriceAdjacence.setBackground(new Color(255, 228, 225));
		btnMatriceAdjacence.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		btnMatriceAdjacence.setBounds(20, 215, 263, 37);
		pnlInfos.add(btnMatriceAdjacence);
		
		
		JPanel pnlDessinGraph = new JPanel();
		pnlDessinGraph.setBackground(new Color(240, 255, 240));
		pnlDessinGraph.setBounds(313, 10, 560, 261);
		
		principalPanel.add(pnlDessinGraph);
		pnlDessinGraph.setLayout(null);
		
		lblNewLabel = new JLabel("test");
		lblNewLabel.setBounds(10, 10, 121, 37);
		pnlDessinGraph.add(lblNewLabel);
		
		JPanel pnlAlgos = new JPanel();
		pnlAlgos.setBackground(new Color(245, 255, 250));
		pnlAlgos.setBounds(883, 10, 293, 543);
		principalPanel.add(pnlAlgos);
		pnlAlgos.setLayout(null);
		
		JLabel lblTitleAlgo = new JLabel("Algorithmes");
		lblTitleAlgo.setToolTipText("Les algorithmes");
		lblTitleAlgo.setBackground(new Color(102, 255, 255));
		lblTitleAlgo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 28));
		lblTitleAlgo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleAlgo.setBounds(10, 5, 273, 43);
		pnlAlgos.add(lblTitleAlgo);
		
		btnBFS = new JButton("BFS");
		btnBFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new BFS(pnlSm)).start();
			}
		});
		btnBFS.setEnabled(false);
		btnBFS.setBackground(new Color(255, 228, 225));
		btnBFS.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnBFS.setBounds(20, 81, 123, 35);
		pnlAlgos.add(btnBFS);
		
		JLabel lblParcours = new JLabel("Parcours du graphe :");
		lblParcours.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblParcours.setHorizontalAlignment(SwingConstants.LEFT);
		lblParcours.setBounds(10, 49, 273, 29);
		pnlAlgos.add(lblParcours);
		
		btnDFS = new JButton("DFS");
		btnDFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new DFS(pnlSm)).start();
			}
		});
		btnDFS.setEnabled(false);
		btnDFS.setBackground(new Color(255, 228, 225));
		btnDFS.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnDFS.setBounds(153, 81, 123, 35);
		pnlAlgos.add(btnDFS);
		
		JLabel lblACM = new JLabel("Arbre couvrant minimal :");
		lblACM.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblACM.setHorizontalAlignment(SwingConstants.LEFT);
		lblACM.setBounds(10, 160, 273, 29);
		pnlAlgos.add(lblACM);
		
		btnWareshall = new JButton("Warshall");
		btnWareshall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Wareshall(pnlSm)).start();
			}
		});
		btnWareshall.setBackground(new Color(255, 228, 225));
		btnWareshall.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnWareshall.setBounds(50, 120, 190, 35);
		pnlAlgos.add(btnWareshall);
		
		btnPrim = new JButton("Prim");
		btnPrim.setEnabled(false);
		btnPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Prim(pnlSm)).start();
			}
		});
		btnPrim.setBackground(new Color(255, 228, 225));
		btnPrim.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnPrim.setBounds(50, 192, 190, 35);
		pnlAlgos.add(btnPrim);
		
		btnKruskal = new JButton("Kruskal");
		btnKruskal.setEnabled(false);
		btnKruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Kruskal(pnlSm)).start();
			}
		});
		btnKruskal.setBackground(new Color(255, 228, 225));
		btnKruskal.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnKruskal.setBounds(50, 231, 190, 35);
		pnlAlgos.add(btnKruskal);
		
		btnDijkstra = new JButton("Dijkstra");
		btnDijkstra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlSm.circuit();
				if(InfosGraph.circuitAbsorbant)
					JOptionPane.showMessageDialog(null, "Le graphe contient un circuit absorbant !");
				else
					new Thread(new Dijkstra(pnlSm)).start();
			}
		});
		btnDijkstra.setEnabled(false);
		btnDijkstra.setBackground(new Color(255, 228, 225));
		btnDijkstra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDijkstra.setBounds(50, 303, 190, 33);
		pnlAlgos.add(btnDijkstra);
		
		JLabel lblCourtChemin = new JLabel("Plus court chemin :");
		lblCourtChemin.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourtChemin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblCourtChemin.setBounds(10, 271, 273, 29);
		pnlAlgos.add(lblCourtChemin);
		
		btnBellmanFord = new JButton("Bellman-Ford");
		btnBellmanFord.setEnabled(false);
		btnBellmanFord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new BellmanFord(pnlSm)).start();
			}
		});
		btnBellmanFord.setBackground(new Color(255, 228, 225));
		btnBellmanFord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBellmanFord.setBounds(50, 342, 190, 33);
		pnlAlgos.add(btnBellmanFord);
		
		JLabel lblReseauTransport = new JLabel("R\u00E9seau de transport :");
		lblReseauTransport.setHorizontalAlignment(SwingConstants.LEFT);
		lblReseauTransport.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblReseauTransport.setBounds(10, 379, 273, 29);
		pnlAlgos.add(lblReseauTransport);
		
		btnMarquage = new JButton("M\u00E9thode de marquage");
		btnMarquage.setEnabled(false);
		btnMarquage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marquage fordFulkerson = new Marquage(pnlSm);
		        fordFulkerson.fordFulkerson(pnlSm.getMatriceCout(pnlSm.getMatriceAdjacence()));
			}
		});
		btnMarquage.setBackground(new Color(255, 228, 225));
		btnMarquage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMarquage.setBounds(50, 411, 190, 33);
		pnlAlgos.add(btnMarquage);
		
		
		btnResiduelle = new JButton("M\u00E9thode r\u00E9siduelle");
		btnResiduelle.setEnabled(false);
		btnResiduelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Residuelle fordFulkerson = new Residuelle(pnlSm);
		        fordFulkerson.fordFulkerson(pnlSm.getMatriceCout(pnlSm.getMatriceAdjacence()));
//				Residuelle res = new Residuelle(pnlSm.getMatriceCout(pnlSm.getMatriceAdjacence()), pnlSm.getSommets().size(), pnlSm.getSommets(), (Graphics2D)pnlSm.getGraphics());
//				details += res.fordFulkerson(pnlSm.getSommets().indexOf(pnlSm.getSource().getNameSommet())+1, pnlSm.getSommets().indexOf(pnlSm.getPuits().getNameSommet())+1);
//				txtTrace.setText(details);
				//new Thread(new Residuelle(pnlSm, pnlSm.getSource(), pnlSm.getPuits())).start();
			}
		});
		btnResiduelle.setBackground(new Color(255, 228, 225));
		btnResiduelle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResiduelle.setBounds(50, 450, 190, 33);
		pnlAlgos.add(btnResiduelle);
		
		JLabel lblColoriage = new JLabel("Coloriage :");
		lblColoriage.setHorizontalAlignment(SwingConstants.LEFT);
		lblColoriage.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblColoriage.setBounds(10, 487, 273, 29);
		pnlAlgos.add(lblColoriage);
		
		btnColoriage = new JButton("Welch et Powell");
		btnColoriage.setEnabled(false);
		btnColoriage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Coloriage(pnlSm)).start();
			}
		});
		btnColoriage.setBackground(new Color(255, 228, 225));
		btnColoriage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnColoriage.setBounds(50, 519, 190, 33);
		pnlAlgos.add(btnColoriage);
		
		JPanel pnlTrace = new JPanel();
		pnlTrace.setBackground(new Color(250, 250, 210));
		pnlTrace.setBounds(10, 237, 293, 316);
		principalPanel.add(pnlTrace);
		pnlTrace.setLayout(null);
		
		ImageIcon iconPdf = new ImageIcon("src\\Icons\\pdf_icon.png");
		JButton btnExporterPDF = new JButton("  Exporter PDF");
		btnExporterPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String file = "src\\TraceAlgorithmesPDF\\" + InfosGraph.nameAlgo + ".pdf";
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream(file));
					document.open();
					Paragraph trace = new Paragraph(txtTrace.getText());
					document.add(trace);
					document.close();
					JOptionPane.showMessageDialog(frmWorkspace, "Votre pdf est enregistré dans le dossier \"TraceAlgorithmesPDF\" :)", "PDF enregistré", JOptionPane.INFORMATION_MESSAGE, null);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnExporterPDF.setBackground(new Color(255, 228, 225));
		btnExporterPDF.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		btnExporterPDF.setBounds(40, 335, 210, 40);
		btnExporterPDF.setIcon(iconPdf);
		pnlTrace.add(btnExporterPDF);
		
		
		JLabel lblTrace = new JLabel("Trace de l'algorithme");
		lblTrace.setBackground(new Color(255, 245, 238));
		lblTrace.setFont(new Font("Segoe UI Symbol", Font.BOLD, 19));
		lblTrace.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrace.setBounds(10, 10, 273, 31);
		pnlTrace.add(lblTrace);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 278, 275);
		pnlTrace.add(scrollPane);
		
		txtTrace = new JTextArea();
		txtTrace.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
		scrollPane.setViewportView(txtTrace);
		txtTrace.setColumns(10);
		
		//---------------------------------------------- 
		JScrollPane jscrollAlgo = new JScrollPane(pnlAlgos);
		
		JPanel panelPlusInfos = new JPanel();
		panelPlusInfos.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panelPlusInfos.setBackground(new Color(250, 240, 230));
		panelPlusInfos.setBounds(0, 559, 327, 88);
		pnlAlgos.add(panelPlusInfos);
		panelPlusInfos.setLayout(null);
		
		JButton btnInfosGraph = new JButton(" A propos du graphe");
		btnInfosGraph.setBackground(new Color(255, 228, 225));
		btnInfosGraph.setIcon(new ImageIcon(MyWorkspace.class.getResource("/Icons/About_icon.png")));
		btnInfosGraph.setToolTipText("Plus d'information sur votre graphe");
		btnInfosGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlSm.isComplet();
				pnlSm.isEulerien();
				pnlSm.isRegulier();
				new InformationsGraph(pnlSm).setVisible(true);;
			}
		});
		btnInfosGraph.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnInfosGraph.setBounds(50, 7, 230, 37);
		panelPlusInfos.add(btnInfosGraph);
		
		JButton btnGuide = new JButton(" Guide");
		btnGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported())
		        {
		            try
		            {
		            	Platform.startup(
		            			()->{
		            				JFrame web = new JFrame("Guide d'utilisation");
		            				//web.setLocationRelativeTo(MyWorkspace.frmWorkspace);
		            				//web.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		            		        WebView webView = new WebView();

		            		        URL url = this.getClass().getResource("GuideUtilisation/index.html");
		            		        webView.getEngine().load(url.toString());

		            		        VBox vBox = new VBox(webView);
		            		        
		            		        BorderPane borderPane = new BorderPane();
		            		        borderPane.setStyle("-fx-background-color: green;");
		            		           
		            		        Scene scene = new Scene(vBox, 1100, 900);

		            		        JFXPanel pan = new JFXPanel();
		            		        pan.setScene(scene);
		            		        
		            		        web.add(pan);
		            		        web.setVisible(true);
		            		        web.pack();
		            			}
		            			);          
		            } catch (Exception ex)
		            {
		                System.out.println(ex);
		            }
		        }
			}
		});
		btnGuide.setBackground(new Color(255, 228, 225));
		btnGuide.setToolTipText("Guide d'utilisation");
		btnGuide.setIcon(new ImageIcon(MyWorkspace.class.getResource("/Icons/guide_icon2.png")));
		btnGuide.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		btnGuide.setBounds(93, 45, 140, 37);
		panelPlusInfos.add(btnGuide);
		//jscrollAlgo.setMinimumSize(new Dimension(60, 400));
		jscrollAlgo.setPreferredSize(new Dimension(60, 0));
		//SommetPanel pnlSm = new SommetPanel();
		//pnlSm.addMouseListener(new SommetListener(pnlSm));
		jscrollSommet = new JScrollPane();
		//jscrollSommet.setViewportView(pnlSm);
		
		JSplitPane jsplitAlgo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jscrollSommet, jscrollAlgo);
		jsplitAlgo.setEnabled(false);//.setMinimumSize(new Dimension(30,0));
		
		panelGraph = new JPanel();
		panelGraph.setBackground(new Color(255, 250, 250));
		//________ pnlSm : Mon espace de dessin ________
		pnlSm = new SommetPanel();
		jscrollSommet.setViewportView(pnlSm);
		panelGraph.setLayout(new BorderLayout(0, 0));
		//panelGraph.add(pnlSm);
		//panelGraph.addMouseListener(new SommetListener(pnlSm));//+++
		frmWorkspace.getContentPane().add(jsplitAlgo);
		jsplitAlgo.setResizeWeight(0.7);
		jsplitAlgo.setDividerSize(3);
		
		JSplitPane jsplitTraceInfos = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlInfos, pnlTrace);
		jsplitTraceInfos.setBackground(new Color(255, 240, 245));
		jsplitTraceInfos.setEnabled(false);
		
		ImageIcon icon = new ImageIcon("src\\Icons\\goback_icon.png");
		JButton btnRevenirPageAcceuil = new JButton(" Revenir \u00E0 la page d'accueil");
		btnRevenirPageAcceuil.setBackground(new Color(255, 192, 203));
		btnRevenirPageAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour = JOptionPane.showConfirmDialog(frmWorkspace, "Vous allez perdre votre graphe et toutes les informations y liées ! \n Etes-vous sûr de vouloir revenir à la page d'accueil ?!", "Attention !", JOptionPane.OK_CANCEL_OPTION);
				if(retour == JOptionPane.OK_OPTION)
				{
					InfosGraph.simple = false;
					InfosGraph.oriente = false;
					InfosGraph.pondere = false;
					btnBFS.setEnabled(false);
					btnDFS.setEnabled(false);
					frmWorkspace.setVisible(false);
					MyFrame myframe = new MyFrame();
					myframe.frmWelcome.setVisible(true);
				}
			}
		});
		btnRevenirPageAcceuil.setFont(new Font("Segoe UI Symbol", Font.BOLD, 17));
		btnRevenirPageAcceuil.setBounds(10, 10, 278, 35);
		btnRevenirPageAcceuil.setIcon(icon);
		pnlInfos.add(btnRevenirPageAcceuil);
		
		jsplitTraceInfos.setPreferredSize(new Dimension(300, 0));
		jsplitTraceInfos.setResizeWeight(0.4);
		jsplitTraceInfos.setDividerSize(7);
		//jsplitTraceInfos.setEnabled(false);
		
		JSplitPane jsplitlast = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jsplitTraceInfos, jsplitAlgo);
		jsplitlast.setDividerSize(3);
		jsplitlast.setEnabled(false);
		frmWorkspace.getContentPane().add(jsplitlast);
		//---------------------------------------------- 
		//jsplitlast.setEnabled(false);
		
		/*SommetPanel pnlSm = new SommetPanel();
		//frmWorkspace.setContentPane(pnlSm);
		//pnlDessinGraph.add(pnlSm);
		pnlSm.addMouseListener(new SommetListener(pnlSm));*/
		//pnlSm.addSommet(new Sommet(300, 300, 50, Color.BLACK));
		//pnlSm.setVisible(true);
		//pnlDessinGraph.add(pnlSm);
		/*pnlDessinGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SommetPanel().addSommet(new Sommet(e.getX(), e.getY(), 50, Color.BLUE));
				System.out.println(e.getX() + "," + e.getY());
			}
		});*/
		//lblNewLabel.setText(MyFrame.getTest());
	}
}
