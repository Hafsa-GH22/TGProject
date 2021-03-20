package algorithmes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.imageio.ImageIO;

import elements.Arete;
import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class Kruskal implements Runnable {
	private SommetPanel sm;
	private ArrayList<Arete> mesAretesTrie;
	// ------------ new Graph -----------
	private ArrayList<Sommet> newSg = new ArrayList<Sommet>();
	private ArrayList<Arete> newAg = new ArrayList<Arete>();
	private int[][] newMatrAdj;
	private double coutACM = 0;
	
	public Kruskal(SommetPanel sm)
	{
		this.sm = sm;
		newSg.addAll(sm.getSommets());
		Collections.sort(sm.getAretes(), new CoutCompare()); // Trie des coûts des arêtes
		mesAretesTrie = sm.getAretes();
		newMatrAdj = new int[sm.getSommets().size()][sm.getSommets().size()];
		InfosGraph.chooseColorArete = true;
		InfosGraph.nameAlgo = "Kruskal";
	}
	
	public boolean existChemin(int[][] matrAdj, Sommet depart, Sommet arrivee)
	{
		int n = matrAdj.length;
		Stack<Sommet> file = new Stack<Sommet>();
		boolean[] visites = new boolean[n];
		for(int i = 0; i < n; i++)
		{
			visites[i] = false;
		}
		file.push(depart);
		while(!file.isEmpty())
		{
			Sommet courant = file.pop();
			visites[sm.getSommets().indexOf(courant)] = true;
			for(Sommet s : sm.getSommets())
			{
				if(matrAdj[sm.getSommets().indexOf(courant)][sm.getSommets().indexOf(s)] > 0 && visites[sm.getSommets().indexOf(s)] == false)
				{
					file.push(s);
					visites[sm.getSommets().indexOf(s)] = true;
				}
				else if(matrAdj[sm.getSommets().indexOf(courant)][sm.getSommets().indexOf(s)] > 0 && s == arrivee)
					return true;
			}
		}
		return false;
	}
	
	public boolean estCycle(int[][] matrAdj, Sommet depart)
	{
		int n = matrAdj.length;
		Sommet[] parent = new Sommet[n];
		Stack<Sommet> file = new Stack<Sommet>();
		boolean[] visites = new boolean[n];
		for(int i = 0; i < n; i++)
		{
			visites[i] = false;
		}
		visites[sm.getSommets().indexOf(depart)] = true;
		for(int i = 0; i < n; i++)
		{
			parent[i] = null;
		}
		parent[sm.getSommets().indexOf(depart)] = depart;
		file.push(depart);
		while(!file.isEmpty())
		{
			Sommet courant = file.pop();
			visites[sm.getSommets().indexOf(courant)] = true;
			for(Sommet s : sm.getSommets())
			{
				if(matrAdj[sm.getSommets().indexOf(courant)][sm.getSommets().indexOf(s)] > 0 && visites[sm.getSommets().indexOf(s)] == false)
				{
					file.push(s);
					visites[sm.getSommets().indexOf(s)] = true;
					parent[sm.getSommets().indexOf(s)] = courant;
				}
				else if(matrAdj[sm.getSommets().indexOf(courant)][sm.getSommets().indexOf(s)] > 0 && visites[sm.getSommets().indexOf(s)] == true && parent[sm.getSommets().indexOf(courant)] != s)
					return true;
			}
		}
		return false;
	}
	
	@Override
	public void run() 
	{
		MyWorkspace.txtTrace.setText("              Algorithme de Kruskal \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "              -------------------------\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Le déroulement de l'algorithme : \n\n");
		for(Arete a : mesAretesTrie)
		{
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			// Avant d'ajouter une arête on doit vérifier l'existence d'une boucle
			Sommet s1 = a.getSommet1();
			Sommet s2 = a.getSommet2();
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "  (" + s1.getNameSommet() + "," + s2.getNameSommet() + ") : " + a.getCout() + " -> ");
			newMatrAdj[sm.getSommets().indexOf(s1)][sm.getSommets().indexOf(s2)] = 1;
			newMatrAdj[sm.getSommets().indexOf(s2)][sm.getSommets().indexOf(s1)] = 1;
			if(estCycle(newMatrAdj, s1) || estCycle(newMatrAdj, s2))
			{
				MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "NOK ! \n");
			}
			else
			{
				MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "OK \n");
				newAg.add(a);
				a.setColor(Color.RED);
				sm.repaint();
				coutACM += a.getCout();
			}
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   => Coût de l'ACM : " + coutACM);
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n\n                Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "                ---------------------");
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\Kruskal.png"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
		// ---------- Un peu de temps pour effacer la trace du graphe colorié ---------- 
		try
		{
			Thread.sleep(3000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		// ---------- Cette partie ne concerne pas l'algorithme :) ----------
		for(Sommet s : sm.getSommets())
			s.setBackground(Color.PINK);
		for(Arete a : sm.getAretes())
			a.setColor(Color.GREEN);
		InfosGraph.chooseColorArete = false;
		InfosGraph.chooseColorSommet = false;
		sm.unselected();
		sm.repaint();
	}
}
