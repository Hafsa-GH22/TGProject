package algorithmes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;

import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class BFS implements Runnable {
	private SommetPanel sm;
	private Sommet selected;
	private Stack<Sommet> f;
	private Stack<Sommet> m;
	private int etape = 0;
	private String fliste = "";
	private String mliste = "";
	
	public BFS(SommetPanel sm)
	{
		this.sm = sm;
		this.selected = sm.getSelected();
		f = new Stack<Sommet>();
		m = new Stack<Sommet>();
		f.push(selected);
		m.push(selected);
		selected.setAlgo(true);
		sm.repaint();
		InfosGraph.nameAlgo = "ParcoursBFS";
	}

	@Override
	public void run() {
		for(int i = 0; i < f.size(); i++)
			fliste += f.get(i).getNameSommet() + " ";
		for(int i = 0; i < m.size(); i++)
			mliste += m.get(i).getNameSommet() + " ";
		MyWorkspace.txtTrace.setText("                   Parcours BFS \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "                   ----------------\n\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " F = ] " + fliste + "]  ;  M = { " + mliste + "}\n");
		while(!f.isEmpty())
		{  
			fliste = "";
			for(int i = 0; i < f.size(); i++)
				fliste += f.get(i).getNameSommet() + " ";
			try
			{
				Thread.sleep(800);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			etape += 1;
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Etape " + etape + " : \n");
			MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "     - Les voisins de " + selected.getNameSommet() + " :\n");
			selected = f.pop();
			for(Sommet s : selected.sommetSortant)
			{
				MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "        > " + s.getNameSommet() + " : \n");
				if(!m.contains(s))
				{
					f.push(s);
					m.push(s);
					s.setAlgo(true);
					sm.repaint();
				}
				sm.repaint();
				fliste = "";
				mliste = "";
				for(int i = 0; i < f.size(); i++)
					fliste += f.get(i).getNameSommet() + " ";
				for(int i = 0; i < m.size(); i++)
					mliste += m.get(i).getNameSommet() + " ";
				MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "            => F = ] " + fliste +" ]\n");
				MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "            => M = { " + mliste +" }\n");
			}
			sm.repaint();
		}
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n               Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "               ---------------------\n");
		mliste = "";
		for(int i = 0; i < m.size(); i++)
			mliste += m.get(i).getNameSommet() + " ";
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Conclusion : \n   M = { " + mliste + " }");
		// --------------------------- Capture du graphe --------------------------- 
		try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\BFS.png"));
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
		for(Sommet s : sm.getSommets())
			s.setAlgo(false);
		sm.unselected();
		sm.repaint();
	}

}
