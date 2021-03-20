package algorithmes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;

import elements.Arete;
import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class BellmanFord implements Runnable {
	private SommetPanel sm;
	private final ArrayList<Sommet> sommets;
    private final ArrayList<Arete> aretes;
    private Sommet first;
    private HashMap<String,Double> l;
    private HashMap<String,String> p;
    private int etape = 0;

    public BellmanFord(SommetPanel sm) {
    	this.sm = sm;
        this.sommets = sm.getSommets();
        this.aretes = sm.getAretes();
        this.l = new HashMap<>();
        this.p = new HashMap<>();
        this.first = sm.getSelected();
        InfosGraph.nameAlgo = "BellmanFord";
        Collections.sort(sommets, new DegreeCompare());
    }
    
	@Override
	public void run() {
		MyWorkspace.txtTrace.setText("         Algorithme de Bellman-Ford \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "         -------------------------------\n");
		for(Sommet s : sommets)
		{
			if(first == s)
			{
				l.put(s.getNameSommet(), 0.0d);
				p.put(s.getNameSommet(), null);
			}
			else
			{
				l.put(s.getNameSommet(), Double.POSITIVE_INFINITY);
				p.put(s.getNameSommet(), null);
			}
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Initialisation : \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   L = " + l + "\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   P = " + p + "\n");
		for(int i = 0; i < sommets.size(); i++)
		{
			try
			{
				Thread.sleep(800);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			etape += 1;
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n Etape " + etape + " :\n");
			for(Arete a : aretes)
			{
				double newCout = l.get(a.getSommet1().getNameSommet()) + a.getCout();
				if(l.get(a.getSommet2().getNameSommet()) > newCout)
				{
					l.put(a.getSommet2().getNameSommet(), newCout);
					p.put(a.getSommet2().getNameSommet(), a.getSommet1().getNameSommet());
				}
			}
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   L = " + l + "\n");
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   P = " + p + "\n");
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n               Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "               ---------------------");
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\BellmanFord.png"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
		// --------------------------- Repaint du graphe --------------------------- 
		sm.unselected();
		sm.repaint();
		new interfaceTravail.PlusCourtCheminResult(MyWorkspace.frmWorkspace, first, l, p, "Bellman-Ford").setVisible(true);
	}

}
