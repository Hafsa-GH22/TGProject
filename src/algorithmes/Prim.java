package algorithmes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import elements.Arete;
import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class Prim implements Runnable {
	private SommetPanel sm;
	private ArrayList<Sommet> newSg = new ArrayList<Sommet>();
	private ArrayList<Arete> newAg = new ArrayList<Arete>();
	private double coutACM = 0;
	private String listA = "";
	private String listS = "";
	
	public Prim(SommetPanel sm)
	{
		InfosGraph.chooseColorSommet = true;
		InfosGraph.chooseColorArete = true;
		
		this.sm = sm;
		Collections.sort(sm.getSommets(), Collections.reverseOrder(new DegreeCompare()));
		newSg.add(sm.getSommets().get(0));
		sm.getSommets().get(0).setBackground(Color.BLUE);
		sm.repaint();
		InfosGraph.nameAlgo = "Prim";
	}

	@Override
	public void run() {
		MyWorkspace.txtTrace.setText("               Algorithme de Prim \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "               ----------------------\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Le déroulement de l'algorithme : \n\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " A = (" + sm.getSommets().get(0).getNameSommet() + ", inf)\n");
		listS += sm.getSommets().get(0).getNameSommet();
		while(newSg.size() < sm.getSommets().size())
		{
			try
			{
				Thread.sleep(800);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			ArrayList<Arete> mesAretes = new ArrayList<Arete>();
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " A = (");
			for(Sommet s : newSg)
			{
				listS = s.getNameSommet() + " ";
				MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + listS + ", ");
				for(Sommet v : s.getSomSort())
				{
					if(!newAg.contains(sm.getArete(s, v)) && !newSg.contains(v) || !newAg.contains(sm.getArete(v, s)) && !newSg.contains(v) )
					{
						mesAretes.add(sm.getArete(s, v));
					}
				}
			}
			Collections.sort(mesAretes, new CoutCompare()); // Trie des aretes possibles
			Sommet suivant = mesAretes.get(0).getSommet2(); // Sommet suivant à ajouter
			newSg.add(suivant); // Ajout de ce sommet à notre graphe
			listS = suivant.getNameSommet();
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + listS + ", ");
			for(Sommet s : newSg)
				s.setBackground(Color.BLUE);
			//suivant.setBackground(Color.BLUE); // Coloriage de ce sommet
			sm.repaint();
			Arete plus = mesAretes.get(0); // L'arête possible
			coutACM += plus.getCout();
			listA += "(" + plus.getSommet1().getNameSommet() + "," + plus.getSommet2().getNameSommet() + ") ";
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + listA + ")\n");
			newAg.add(plus); // Ajout à la liste des nouvels arêtes
			plus.setColor(Color.BLACK); // Coloriage de cette arête
			sm.repaint();
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   => Coût de l'ACM : " + coutACM);
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n\n                Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "                ---------------------");
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\Prim.png"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
		// ---------- Un peu de temps pour effacer la trace de l'algorithme ----------
		try
		{
			Thread.sleep(3000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
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
