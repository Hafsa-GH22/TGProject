package algorithmes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import elements.Arete;
import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class Wareshall implements Runnable {
	private SommetPanel sm = new SommetPanel();
//	private String trace = "";
//	private ArrayList<Arete> arw;
	private int etape = 0;
//	private ArrayList<Arete> aretesWarshall;
	private int[][] matrWar ;
	
	public Wareshall(SommetPanel sm)
	{
		this.sm = sm;
		InfosGraph.nameAlgo = "Warshall";
//		aretesWarshall = new ArrayList<Arete>();
	}

	@Override
	public void run() {
		MyWorkspace.txtTrace.setText("            Algorithme de Warshall\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "            --------------------------\n\n");
		for(Sommet s1 : sm.getSommets())
		{
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " y = " + s1.getNameSommet() + " :\n ");
			for(Sommet s2 : sm.getSommets())
			{
				try
				{
					Thread.sleep(600);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				if(sm.getMatriceAdjacence()[sm.getSommets().indexOf(s1)][sm.getSommets().indexOf(s2)] == 0)
				{
					MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "  (" + s1.getNameSommet() + " , " + s2.getNameSommet() + ") not in E  =>  Ajout de (" + s1.getNameSommet() + " , " + s2.getNameSommet() + ")\n");
					sm.addAreteWareshall(s1, s2);
					sm.repaint();
				}
//				else
//					MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "  (" + s1.getNameSommet() + " , " + s2.getNameSommet() + ") not in E  =>  Ajout de (" + s1.getNameSommet() + " , " + s2.getNameSommet() + ")\n");
			}
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n               Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "               ---------------------");
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\Warshall.png"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
		// --------------------------- Repaint du graphe --------------------------- 
		try 
		{
            Thread.sleep(3000);
        } 
		catch (InterruptedException ex) 
		{
            ex.printStackTrace();
        }
		InfosGraph.wareshall = false;
		sm.getAretesWareshall().clear();
		sm.unselected();
		sm.repaint();
	}

}
