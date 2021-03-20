package algorithmes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;

import elements.InfosGraph;
import elements.Sommet;
import elements.SommetPanel;
import interfaceTravail.MyWorkspace;

public class Residuelle {
	private SommetPanel sm;
    
	private int[] parent;
    private Queue<Integer> queue;
    private int numberOfVertices;
    private boolean[] visited;
    private int source;
    private int goal;
    private int i = 0;
    private int etape = 0;
    private Sommet src;
    private Sommet pt;
 
    public Residuelle(SommetPanel sm)
    {
    	this.sm = sm;
    	src = sm.getSource();
    	pt = sm.getPuits();
        this.numberOfVertices = sm.getSommets().size();
        this.queue = new LinkedList<Integer>();
        parent = new int[numberOfVertices + 1];
        visited = new boolean[numberOfVertices + 1];
        for(Sommet s : sm.getSommets())
        {
        	i += 1;
        	s.setParent(i);
        }
        this.source = sm.getSource().getParent();
        this.goal = sm.getPuits().getParent();
        InfosGraph.nameAlgo = "FordFulkerson_Residuelle";
    }
 
    public boolean bfs(int source, int goal, int graph[][])
    {
        boolean pathFound = false;
        int destination, element;
 
        for(int vertex = 1; vertex <= numberOfVertices; vertex++)
        {
            parent[vertex] = -1;
            visited[vertex] = false;
        }
 
        queue.add(source);
        parent[source] = -1;
        visited[source] = true;
 
        while (!queue.isEmpty())
        { 
            element = queue.remove();
            destination = 1;
 
            while (destination <= numberOfVertices)
            {
                if (graph[element][destination] > 0 &&  !visited[destination])
                {
                    parent[destination] = element;
                    queue.add(destination);
                    visited[destination] = true;
                }
                destination++;
            }
        }
        if(visited[goal])
        {
            pathFound = true;
        }
        return pathFound;
    }
 
    public int fordFulkerson(int graph[][])
    {
    	MyWorkspace.txtTrace.setText("        Algorithme de Ford-Fulkerson \n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "        ---------------------------------\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Initialisation :\n   On pose F = 0 (qui est un flot compatible et réalisable )\n   Source : " + src.getNameSommet() + "\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "   Puits : " + pt.getNameSommet() + "\n");
		int u, v;
        int maxFlow = 0;
        int pathFlow;
 
        int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];
        for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++)
        {
            for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++)
            {
                residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex-1][destinationVertex-1];
            }
        }
 
        while (bfs(source ,goal, residualGraph))
        {
            pathFlow = Integer.MAX_VALUE;
            for (v = goal; v != source; v = parent[v])
            {
                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }
            for (v = goal; v != source; v = parent[v])
            {
                u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
            etape += 1;
            MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + " Etape " + etape + " : \n");
            MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "    -> F = " + (maxFlow-pathFlow) + " + " + pathFlow + " = " + maxFlow + "\n");
        }
        MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "\n                Fin de l'algorithme\n");
		MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "                ---------------------\n");
        MyWorkspace.txtTrace.setText(MyWorkspace.txtTrace.getText() + "    Il n’y a plus de chaîne améliorante \n      Donc le flot max est Fmax = " + maxFlow);
        // --------------------------- Capture du graphe --------------------------- 
        try 
        {
        	BufferedImage screen = new BufferedImage(MyWorkspace.pnlSm.getWidth(), MyWorkspace.pnlSm.getHeight(), BufferedImage.TYPE_INT_RGB);
        	MyWorkspace.pnlSm.printAll(screen.getGraphics());
        	ImageIO.write(screen, "png", new File("src\\ScreenAlgos\\FordFulkersonResiduelle.png"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
        return maxFlow;
    }
}
