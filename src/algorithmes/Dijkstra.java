package algorithmes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import elements.Arete;
import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class Dijkstra implements Runnable {
	private SommetPanel sm;
	private ArrayList<Sommet> sommets;
	private ArrayList<Arete> aretes;
	private Sommet first;
	private ArrayList<String> s;
	private ArrayList<String> t;
	private HashMap<String, Double> l;
	private HashMap<String, String> p;
	private int etape = 0;
	
	public Dijkstra(SommetPanel sm)
	{
		this.sm = sm;
		this.sommets = sm.getSommets();
		this.aretes = sm.getAretes();
		this.s = new ArrayList<String>();
		this.t = new ArrayList<String>();
		this.l = new HashMap<>();
		this.p = new HashMap<>();
		this.first = sm.getSelected();
		
		Collections.sort(sommets, new DegreeCompare());
		s.add(first.getNameSommet());
		InfosGraph.nameAlgo = "Dijkstra";
	}
	
	public Sommet getByName(String name)
	{
		for(Sommet s : sommets)
		{
			if(s.getNameSommet().equals(name))
				return s;
		}
		return null;
	}
	
	public String selectMinCout()
	{
		Collection<Double> coutNotVisited = new ArrayList<>();
		for(Map.Entry<String, Double> entry : l.entrySet())
		{
			String key = entry.getKey();
			Double value = entry.getValue();
			if(t.contains(key))
			{
				coutNotVisited.add(value);
			}
		}
		for(Map.Entry<String, Double> entry : l.entrySet()) {
            String key = entry.getKey(); 
            Double value = entry.getValue();
            if(t.contains(key) && value <= Collections.min(coutNotVisited)){
                return key;
            }
        }
        return null;
	}
	
	@Override
	public void run() {
		MyWorkspace.txtTrace.setText("              Algorithme de Dijkstra \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "              -------------------------\n");
		for(Sommet s : sommets)
		{
			if(first == s)
			{
				l.put(s.getNameSommet(), 0.0d);
				p.put(s.getNameSommet(), null);
			}
			else
			{
				t.add(s.getNameSommet());
				Arete a = sm.getArete(first, s);
				if(a != null)
				{
					l.put(s.getNameSommet(), a.getCout());
					p.put(s.getNameSommet(), first.getNameSommet());
				}
				else
				{
					l.put(s.getNameSommet(), Double.POSITIVE_INFINITY);
					p.put(s.getNameSommet(), null);
				}
			}
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Initialisation : \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   L = " + l + "\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   P = " + p + "\n");
		while(!t.isEmpty())
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
			String j = selectMinCout();
			Sommet js = getByName(j);
			if(js != null)
			{
				for(Sommet i : sm.getVoisinsNonVisites(js, s))
				{
					Double newCout = l.get(j) + sm.getArete(js, i).getCout();
					if(l.get(i.getNameSommet()) > newCout)
					{
						l.put(i.getNameSommet(), newCout);
						p.put(i.getNameSommet(), j);
					}
				}
				s.add(j);
				t.remove(j);
			}
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   L = " + l + "\n");
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   P = " + p + "\n");
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n                Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "                -------------------");
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\Dijkstra.png"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
		// --------------------------- Repaint du graphe --------------------------- 
		sm.unselected();
		sm.repaint();
		new interfaceTravail.PlusCourtCheminResult(MyWorkspace.frmWorkspace, first, l, p, "Dijkstra").setVisible(true);
	}

}
