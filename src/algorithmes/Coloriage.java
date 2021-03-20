package algorithmes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;

import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class Coloriage implements Runnable {
	private SommetPanel sm; // La panel de mon graphe
	private ArrayList<Sommet> mesSommets; // Les sommets de mon graphe
	private ArrayList<Sommet> colored = new ArrayList<Sommet>(); // Les sommets colori�s
	private ArrayList<Sommet> nonColored = new ArrayList<Sommet>(); // Les sommets pas encore colori�s
	private ArrayList<Color> colors = new ArrayList<Color>(); // Liste des couleurs
	private Sommet first; // Le sommet ayant le plus grand degr�
//	private Random rand = new Random();
//	private int red = (int)(Math.random()*256);
//	private int green = (int)(Math.random()*256);
//	private int blue = (int)(Math.random()*256);
	private int etape = 0;
	private String dg = "";
	private int nbreColors = 0;
	
	// ------------------------ Constructeur ------------------------
	public Coloriage(SommetPanel sm)
	{
		this.sm = sm;
		mesSommets = sm.getSommets(); // R�cup�ration des sommets du graphe
		nonColored.addAll(mesSommets); // On commence par tous les sommets (aucun sommet n'est colori�)
		Collections.sort(mesSommets, Collections.reverseOrder(new DegreeCompare())); // Trie des degr�s des sommets par ordre d�croissant
		/* DegreeCompare() : La classe de comparaison des degr�s des sommets */
		//InfosGraph.chooseColorSommet = true; // Pas interessant :) passer
		first = mesSommets.get(0); // On r�cup�re le sommet ayant le plus grand degr�
		//sm.repaint();
		colored.add(first); // Ajout du sommet first � la table des sommets colori�s
		nonColored.removeAll(colored); // On enl�ve � chaque fois les sommets colori�s de la table des sommets non colori�s
		InfosGraph.nameAlgo = "WelchPowell";
		// ----------------- Ajout des couleurs � utiliser -----------------
		colors.add(Color.GREEN);
		colors.add(Color.WHITE);
		colors.add(Color.BLUE);
		colors.add(Color.CYAN.darker());
		colors.add(Color.GRAY);
		colors.add(Color.ORANGE);
		colors.add(Color.RED);
		colors.add(Color.MAGENTA);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN.darker());
		colors.add(Color.BLACK);
		colors.add(new Color(192, 0, 255));
	}
	
	// -------------- Fonction qui va se r�p�ter � chaque �tape --------------
	public void col(Sommet s, int i)
	{
		s.setBackground(colors.get(i));//new Color(red, green, blue)); // Coloriage du sommet
		sm.repaint(); 
		//MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Avec la couleur " + etape + " (" + first.getBackground() + "), on colorie successivement (et dans l'ordre) les sommets : ");
		for(Sommet v : mesSommets) // Pour chaque sommet du graphe
		{
			if(nonColored.contains(v)) // S'il n'est pas encore colori� (c'est-�-dire appartient � la table des sommets non colori�s encore)
			{
				if(!s.getSomSort().contains(v)) // S'il n'appartient pas aux voisins du sommet s, (getSomSort() : la fonction qui retourne les voisins d'un sommet)
				{
					MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + v.getNameSommet() + "  ");
					v.setBackground(s.getBackground()); // On colorie ce sommet par la m�me couleur du sommet s
					sm.repaint();
					colored.add(v); // Et on l'ajoute � la liste des sommets colori�s
				}
			}
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n");
		nonColored.removeAll(colored); // � chaque �tape on enl�ve les sommets d�j� colori�s de la table des sommets non colori�s
		// Un peu de temps avant la prochaine �tape :)
		try
		{
			Thread.sleep(800);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private String getNameColor(Color c)
	{
		HashMap<Color, String> col = new HashMap<Color, String>();
		col.put(Color.GREEN, "Vert");
		col.put(Color.WHITE, "Blanc");
		col.put(Color.BLUE, "Bleu");
		col.put(Color.CYAN.darker(), "Cyan fonc�");
		col.put(Color.GRAY, "Gris");
		col.put(Color.ORANGE, "Orange");
		col.put(Color.RED, "Rouge");
		col.put(Color.MAGENTA, "Rose fonc�");
		col.put(Color.YELLOW, "Jaune");
		col.put(Color.GREEN.darker(), "Vert fonc�");
		col.put(Color.BLACK, "Noir");
		col.put(new Color(192, 0, 255), "Violet");
		
		return (String) col.get(c);
	}
	
	// ------------------------ La fonction run() ------------------------
	@Override
	public void run() {
		for(Sommet s : mesSommets)
			dg += s.getDegree() + " ";
		MyWorkspace.txtTrace.setText("          Algorithme de Welch et Powell \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "          ----------------------------------\n\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Initialisation : \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   Liste des deg�s des sommets dans l'ordre d�croissant : " + dg + "\n");
		int i = 0;
		etape += 1;
		nbreColors += 1;
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " - Etape " + etape + " : \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "     Avec la couleur " + etape + " (" + getNameColor(colors.get(i)/*first.getBackground()*/) + "), on colorie successivement (et dans l'ordre) les sommets : " + first.getNameSommet() + " ");
		col(first, i); // On commence par le premier sommet
		while(colored.size() < mesSommets.size()) // On boucle jusqu'� ce qu'on colorie tous les sommets (jusqu'� ce que le size() des sommets colori�s = au nbre de sommets du graphe)
		{
//			etape += 1;
//			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Etape " + etape + " : \n");
			for(Sommet s : mesSommets)
			{
//				red = (int)(Math.random()*256);
//				green = (int)(Math.random()*256);
//				blue = (int)(Math.random()*256);
				i += 1; // S'incr�mente pour changer de couleur
				if(nonColored.contains(s))
				{
					etape += 1;
					MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " - Etape " + etape + " : \n");
					MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "     Avec la couleur " + etape + " (" + getNameColor(colors.get(i)/*s.getBackground()*/) + "), on colorie successivement (et dans l'ordre) les sommets : ");
					col(s, i);
					nbreColors += 1;
				}
			}
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n               Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "               ---------------------\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n Solution : \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "  Liste des degr�s : (" + dg + ")\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "    -> Nombre de couleur minimum : " + nbreColors);
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\Coloriage.png"));
 		} 
		catch (IOException e) 
		{
 			e.printStackTrace();
 		}
		// ---------- Un peu de temps pour effacer la trace du graphe colori� ---------- 
		try
		{
			Thread.sleep(3000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		// ---------- Cette partie ne concerne pas l'algorithme :) ----------
		//InfosGraph.chooseColorSommet = false;
		sm.unselected();
		for(Sommet s : sm.getSommets())
			s.setBackground(Color.PINK);
		sm.unselected();
		sm.repaint();
	}

}
