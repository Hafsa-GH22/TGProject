package interfaceTravail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

//import com.sun.javafx.application.PlatformImpl;
//
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.layout.VBox;
//import javafx.scene.web.WebView;

public class MyFrame {

	public static JFrame frmWelcome;
	public String test="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					MyFrame window = new MyFrame();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("/Icons/Home_icon.png")));
		frmWelcome.setTitle("Home page");
		frmWelcome.setBounds(100, 100, 429, 460);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.setLocationRelativeTo(null);
		
		JPanel principalPanel = new JPanel();
		principalPanel.setBackground(new Color(255, 245, 238));
		frmWelcome.getContentPane().add(principalPanel, BorderLayout.CENTER);
		principalPanel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBackground(new Color(154, 205, 50));
		lblWelcome.setForeground(new Color(154, 205, 50));
		lblWelcome.setFont(new Font("Segoe UI Symbol", Font.BOLD, 32));
		lblWelcome.setBounds(10, 57, 395, 49);
		principalPanel.add(lblWelcome);
		
		JButton btnGuide = new JButton(" Guide d'utilisation");
		btnGuide.setBackground(new Color(255, 192, 203));
		btnGuide.setIcon(new ImageIcon(MyFrame.class.getResource("/Icons/guide_icon2.png")));
		btnGuide.setToolTipText("Cliquez ici pour afficher le guide d'utilisation de cette application");
		btnGuide.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
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
		            				ImageIcon ic = new ImageIcon("src/Icons/guide_icon.png");
		            				Image im = ic.getImage();
		            				web.setIconImage(im);

		            		        WebView webView = new WebView();

		            		        URL url = this.getClass().getResource("GuideUtilisation/index.html");
		            		        webView.getEngine().load(url.toString());

		            		        VBox vBox = new VBox(webView);
		            		        //HBox hBox = new HBox();
		            		        Scene scene = new Scene(vBox, 1100, 700);

		            		        JFXPanel pan = new JFXPanel();
		            		        pan.setScene(scene);
		            		        
		            		        web.add(pan);
		            		        web.setVisible(true);
		            		        web.pack();
		            			}
		            			);
		            	//Platform.exit();
		            	//Platform.setImplicitExit(false);
		            } catch (Exception ex)
		            {
		                System.out.println(ex);
		            }
		        }
				
//				File f = new File("src\\GuideUtilisation\\Guide.html");
//		        try {
//		            Desktop.getDesktop().open(f);
//		        } catch (IOException ex) {
//		            ex.printStackTrace();
//		        }
				
				
//				try {
//			        Desktop.getDesktop().browse(new URL("https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button").toURI());
//			    } catch (Exception ex) {
//			        ex.printStackTrace();
//			    }
				
				
//				WebView webView = new WebView();
//				URL url = this.getClass().getResource("src\\GuideUtilisation\\Guide.html");
//				WebEngine engine = webView.getEngine();
//				engine.load(url.toString());;
//				
//				VBox vBox = new VBox(webView);
//		        Scene scene = new Scene(vBox, 960, 600);
//		        
//		        vBox.getChildren().add(webView);
//		        Stage stage = new Stage();
//		        stage.setScene(scene);
//		        stage.show();
		        
//				if (Desktop.isDesktopSupported())
//		        {
//		            try
//		            {
//		            	PlatformImpl.startup(
//		            			()->{
//		            				JFrame web = new JFrame("Guide d'utilisation");
//
//		            		        WebView webView = new WebView();
//
//		            		        URL url = this.getClass().getResource("src\\GuideUtilisation\\Guide.html");
//		            		        webView.getEngine().load(url.toString());
//
//		            		        VBox vBox = new VBox(webView);
//		            		        Scene scene = new Scene(vBox, 960, 600);
//
//		            		        JFXPanel pan = new JFXPanel();
//		            		        pan.setScene(scene);
//		            		        
//		            		        web.add(pan);
//		            		        web.setVisible(true);
//		            		        web.pack();
//		            			}
//		            			);          
//		            } catch (Exception ex)
//		            {
//		                System.out.println(ex);
//		            }
//		        }
				
				// pdf
//				try 
//				{
//					Desktop.getDesktop().open(new java.io.File("C:\\Users\\LENOVO\\Desktop\\CI LSI\\S2\\Théorie des graphes\\Cours\\Chap3-ACM.pdf"));
//				} 
//				catch (IOException e1) 
//				{
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		btnGuide.setBounds(108, 109, 204, 40);
		principalPanel.add(btnGuide);
		
		JPanel plTypeGraph = new JPanel();
		plTypeGraph.setBackground(new Color(240, 255, 240));
		plTypeGraph.setBounds(117, 156, 185, 165);
		principalPanel.add(plTypeGraph);
		plTypeGraph.setLayout(null); 
		
		JRadioButton rdbtnPondere = new JRadioButton("Non orient\u00E9 et pond\u00E9r\u00E9");
		rdbtnPondere.setToolTipText("Dessiner un graphe non orient\u00E9 pond\u00E9r\u00E9");
		rdbtnPondere.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		rdbtnPondere.setBackground(new Color(245, 255, 250));
		rdbtnPondere.setForeground(new Color(0, 0, 0));
		rdbtnPondere.setBounds(6, 48, 171, 30);
		plTypeGraph.add(rdbtnPondere);
		
		JRadioButton rdbtnOriente = new JRadioButton("Orient\u00E9");
		rdbtnOriente.setToolTipText("Dessiner un graphe orient\u00E9");
		rdbtnOriente.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		rdbtnOriente.setBackground(new Color(245, 255, 250));
		rdbtnOriente.setForeground(new Color(0, 0, 0));
		rdbtnOriente.setBounds(6, 89, 171, 30);
		plTypeGraph.add(rdbtnOriente);
		
		JRadioButton rdbtnOrtPond = new JRadioButton("Orient\u00E9 et pond\u00E9r\u00E9");
		rdbtnOrtPond.setToolTipText("Dessiner un graphe orient\u00E9 pond\u00E9r\u00E9");
		rdbtnOrtPond.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		rdbtnOrtPond.setBackground(new Color(245, 255, 250));
		rdbtnOrtPond.setForeground(new Color(0, 0, 0));
		rdbtnOrtPond.setBounds(6, 129, 171, 30);
		plTypeGraph.add(rdbtnOrtPond);
		
		JRadioButton rdbtnSimple = new JRadioButton("Non orient\u00E9");
		rdbtnSimple.setToolTipText("Dessiner un graphe non orient\u00E9");
		rdbtnSimple.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		rdbtnSimple.setBackground(new Color(245, 255, 250));
		rdbtnSimple.setBounds(6, 7, 171, 30);
		plTypeGraph.add(rdbtnSimple);
		
		ButtonGroup typeGraph = new ButtonGroup();
		typeGraph.add(rdbtnPondere);
		typeGraph.add(rdbtnOriente);
		typeGraph.add(rdbtnOrtPond);
		typeGraph.add(rdbtnSimple);
		
		
		JButton btnStart = new JButton("");
		btnStart.setToolTipText("Vous devez tout d'abord choisir un type de graphe pour pouvoir commencer !");
		btnStart.setBackground(new Color(240, 255, 240));
		btnStart.setIcon(new ImageIcon(MyFrame.class.getResource("/Icons/start_icon.png")));
		btnStart.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frmWelcome.setVisible(false);
//				MyWorkspace myworkspace = new MyWorkspace();
//				myworkspace.frmWorkspace.setVisible(true);
				// ______________ Récupération du type sélectionné ______________
				for (Enumeration<AbstractButton> buttons = typeGraph.getElements(); 
						buttons.hasMoreElements();) {
			        AbstractButton button = buttons.nextElement();
			        if (button.isSelected()) {
			        	if(button.getText() == null)
			        	{
			        		System.out.println("null");
			        		JOptionPane.showMessageDialog(frmWelcome, "You should choose a type !", "Type du graphe !", JOptionPane.ERROR_MESSAGE);
			        	}
			        	else
			        	{
			        		frmWelcome.setVisible(false);
							MyWorkspace myworkspace = new MyWorkspace(button.getText());
							myworkspace.frmWorkspace.setVisible(true);
							//myworkspace.setType(button.getText());
							
			        	}
			        		//myworkspace.setLbl(button.getText());
			        		//myworkspace.type = button.getText();
			        		//test = button.getText();
			        }
			    }
			}
		});
		btnStart.setBounds(342, 331, 48, 48);
		principalPanel.add(btnStart);
		
		ImageIcon frame = new ImageIcon("src\\Icons\\frame.png");
		Image img = frame.getImage();  
		Image newimg = img.getScaledInstance(415, 423,  java.awt.Image.SCALE_SMOOTH);
		frame = new ImageIcon( newimg );
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 415, 423);
		lblNewLabel.setIcon(frame);
		principalPanel.add(lblNewLabel);
	}
}
