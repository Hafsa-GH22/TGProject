package elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;


public class Arete {
	private Sommet sommet1;
	private Sommet sommet2;
	private double cout;
	private Double flux = null;
    private boolean dashed = false; // Wareshall
	private Color color;
	private Rectangle bounds; 
	
	// ____________________________ Constructeurs ____________________________
	public Arete(Sommet sommet1, Sommet sommet2)
	{
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
	}
	
	public Arete(Sommet sommet1, Sommet sommet2, Double cout)
	{
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
		this.cout = cout;
	}
	
	public Arete(Arete a)
	{
		this.sommet1 = a.sommet1;
		this.sommet2 = a.sommet2;
		this.cout = a.cout;
		this.flux = a.flux;
		this.color = a.color;
	}
	
	// ____________________________ Getters et Setters ____________________________
	public Sommet getSommet1() {
		return sommet1;
	}


	public Sommet getSommet2() {
		return sommet2;
	}


	public double getCout() {
		return cout;
	}


	public Double getFlux() {
		return flux;
	}


	public boolean isDashed() {
		return dashed;
	}


	public Color getColor() {
		return color;
	}


	public void setSommet1(Sommet sommet1) {
		this.sommet1 = sommet1;
	}


	public void setSommet2(Sommet sommet2) {
		this.sommet2 = sommet2;
	}


	public void setCout(double cout) {
		this.cout = cout;
	}


	public void setFlux(Double flux) {
		this.flux = flux;
	}


	public void setDashed(boolean dashed) {
		this.dashed = dashed;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean exist(Arete a)
	{
		if(this.sommet1 == a.sommet1 && this.sommet2 == a.sommet2)
			return true;
		else
			return false;
	}
	
	public Rectangle getBounds()
	{
		return this.bounds;
	}
	
	private double angleBetween(int x1, int y1, int x2, int y2/*Point2D from, Point2D to*/) {
	    double x = x1;
	    double y = y1;
	    
	    double deltaX = x2 - x;
	    double deltaY = y2 - y;

	    double rotation = -Math.atan2(deltaX, deltaY);
	    rotation = Math.toRadians(Math.toDegrees(rotation) + 180);

	    return rotation;
	}
	
	private Point2D getPointOnCircle(int xs, int ys,/*Point2D center,*/ double radians, double radius) {

	    double x = xs;
	    double y = ys;

	    radians = radians - Math.toRadians(90.0); 
	    
	    double xPosy = Math.round((float) (x + Math.cos(radians) * radius));
	    double yPosy = Math.round((float) (y + Math.sin(radians) * radius));

	    return new Point2D.Double(xPosy, yPosy);

	}

	// ____________________________ Dessin de l'arete ____________________________
	public void dessinerArete(Graphics2D a)
	{
		double from = angleBetween(sommet1.getX(), sommet1.getY(), sommet2.getX(), sommet2.getY());
		double to = angleBetween(sommet2.getX(), sommet2.getY(), sommet1.getX(), sommet1.getY());

		Point2D pointFrom = getPointOnCircle(sommet1.getX(), sommet1.getY(), from, 15);
		Point2D pointTo = getPointOnCircle(sommet2.getX(), sommet2.getY(), to, 15);
		
		Line2D line = new Line2D.Double(pointFrom, pointTo);
		
		if(this.sommet1.isEqual(this.sommet2))
		{
			a.setColor(Color.ORANGE);
			a.setStroke(new BasicStroke(2));
			a.drawArc(sommet1.getX() - 10, sommet1.getY() - (15 + 15 /2), 20, 15, 0, 360);
			return;
		}
		if(InfosGraph.simple)
		{
			if(InfosGraph.pondere)
			{
				a.setColor(Color.GREEN);
				a.setStroke(new BasicStroke(2));
				a.draw(line);
				drawCout(a, (int)pointFrom.getX(), (int)pointFrom.getY(), (int)pointTo.getX(), (int)pointTo.getY());
				//a.drawString(Double.toString(cout), sommet1.getX() + 20, sommet2.getX() - 20);
			}
			else
			{
				a.setColor(Color.CYAN);
				a.setStroke(new BasicStroke(2));
				a.draw(line);
				bounds = line.getBounds2D().getBounds();
				/*bounds.x = (int)a.getClipBounds().getX();
				bounds.y = (int)a.getClipBounds().getY();
				bounds.width = 2;
				bounds.x = (int)line.getBounds2D().getX();
				bounds.y = (int)line.getBounds2D().getY();
				bounds.width = 2;
				bounds.height = (int)line.getBounds2D().getHeight();*/
			}
		}
		if(InfosGraph.oriente)
		{
			if(InfosGraph.pondere)
			{
				a.setColor(Color.BLUE);
				a.setStroke(new BasicStroke(2));
				a.draw(line);
				drawCout(a, (int)pointFrom.getX(), (int)pointFrom.getY(), (int)pointTo.getX(), (int)pointTo.getY());
				//a.drawString(Double.toString(cout), sommet1.getX() + 20, sommet2.getX() - 20);
				//---------------------- ArrowHead -------------------------- //
				Path2D.Double arrowHead = new Path2D.Double();
				arrowHead.moveTo(0, 10);
				arrowHead.lineTo(10 / 2, 0);
				arrowHead.lineTo(10, 10);
				AffineTransform at = AffineTransform.getTranslateInstance(
				                pointTo.getX() - (arrowHead.getBounds2D().getWidth() / 2d), 
				                pointTo.getY());
				at.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
				arrowHead.transform(at);
				a.draw(arrowHead);
				//--------------------------------------------------------- //
			}
			else
			{
				a.setColor(Color.MAGENTA);
				a.setStroke(new BasicStroke(2));
				a.draw(line);
				// ---------------------- ArrowHead -------------------------- //
				Path2D.Double arrowHead = new Path2D.Double();
				arrowHead.moveTo(0, 10);
				arrowHead.lineTo(10 / 2, 0);
				arrowHead.lineTo(10, 10);
				AffineTransform at = AffineTransform.getTranslateInstance(
				                pointTo.getX() - (arrowHead.getBounds2D().getWidth() / 2d), 
				                pointTo.getY());
				at.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
				arrowHead.transform(at);
				a.draw(arrowHead);
				// ---------------------------------------------------------- //
			}
		}
		if(InfosGraph.chooseColorArete)
		{
			//a.setStroke(new BasicStroke(3));
			a.setColor(color);
			a.draw(line);
		}
		a.setStroke(new BasicStroke(2));
    }
	
	public void drawCout(Graphics2D a, int x1, int y1, int x2, int y2) 
	{
		double dx = x2 - x1;
		double dy = y2 - y1;
		double theta = Math.atan2(dy,  dx);
		double x = 0;
		double y = 0;
		double rho = theta + Math.toRadians(15);
		x = x2 - 15 * Math.cos(rho);
		y = y2 - 15 * Math.sin(rho);
		a.drawString(Double.toString(cout), (int)x+10, (int)y+10);
	}
	
	public void dessinerWareshall(Graphics2D a)
	{
		a.setColor(Color.RED);
		a.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		
		double from = angleBetween(sommet1.getX(), sommet1.getY(), sommet2.getX(), sommet2.getY());
		double to = angleBetween(sommet2.getX(), sommet2.getY(), sommet1.getX(), sommet1.getY());

		Point2D pointFrom = getPointOnCircle(sommet1.getX(), sommet1.getY(), from, 15);
		Point2D pointTo = getPointOnCircle(sommet2.getX(), sommet2.getY(), to, 15);
		
		Line2D line = new Line2D.Double(pointFrom, pointTo);
//		if(InfosGraph.wareshall)
//		{
//			a.setColor(Color.RED);
//			a.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
//		}
		if(this.sommet1.isEqual(this.sommet2))
		{
			a.drawArc(sommet1.getX() - 10, sommet1.getY() - (15 + 15 /2), 20, 15, 0, 360);
			return;
		}
		
		if(this.sommet1.isEqual(this.sommet2))
		{
			a.drawArc(sommet1.getX() - 10, sommet1.getY() - (15 + 15 /2), 20, 15, 0, 360);
			return;
		}
		if(InfosGraph.simple)
		{
			if(InfosGraph.pondere)
			{
				a.draw(line);
				drawCout(a, (int)pointFrom.getX(), (int)pointFrom.getY(), (int)pointTo.getX(), (int)pointTo.getY());
				//a.drawString(Double.toString(cout), sommet1.getX() + 20, sommet2.getX() - 20);
			}
			else
				a.draw(line);
		}
		if(InfosGraph.oriente)
		{
			if(InfosGraph.pondere)
			{
				a.draw(line);
				drawCout(a, (int)pointFrom.getX(), (int)pointFrom.getY(), (int)pointTo.getX(), (int)pointTo.getY());
				//a.drawString(Double.toString(cout), sommet1.getX() + 20, sommet2.getX() - 20);
				//---------------------- ArrowHead -------------------------- //
				Path2D.Double arrowHead = new Path2D.Double();
				arrowHead.moveTo(0, 10);
				arrowHead.lineTo(10 / 2, 0);
				arrowHead.lineTo(10, 10);
				AffineTransform at = AffineTransform.getTranslateInstance(
				                pointTo.getX() - (arrowHead.getBounds2D().getWidth() / 2d), 
				                pointTo.getY());
				at.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
				arrowHead.transform(at);
				a.setStroke(new BasicStroke(2));
				a.draw(arrowHead);
				//--------------------------------------------------------- //
			}
			else
			{
				a.draw(line);
				//---------------------- ArrowHead -------------------------- //
				Path2D.Double arrowHead = new Path2D.Double();
				arrowHead.moveTo(0, 10);
				arrowHead.lineTo(10 / 2, 0);
				arrowHead.lineTo(10, 10);
				AffineTransform at = AffineTransform.getTranslateInstance(
				                pointTo.getX() - (arrowHead.getBounds2D().getWidth() / 2d), 
				                pointTo.getY());
				at.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
				arrowHead.transform(at);
				a.setStroke(new BasicStroke(2));
				a.draw(arrowHead);
				//--------------------------------------------------------- //
			}
		}
	}
	
	/*private void drawArrowHead(Graphics2D g2d)
    {
        double phi = Math.toRadians(25);
        if(InfosGraph.pondere)
        	g2d.setPaint(Color.BLUE);
        else
        	g2d.setPaint(Color.MAGENTA);
        double dx = sommet2.getX() - sommet1.getX();
        double dy = sommet2.getY() - sommet1.getY();
        double theta = Math.atan2(dy, dx);
        double x, y;
        double rho = theta + phi;
        for(int i = 0; i < 2; i++)
        {
        	x = sommet2.getX() - 15 * Math.cos(rho);
        	y = sommet2.getY() - 15 * Math.sin(rho);
        	g2d.draw(new Line2D.Double(sommet2.getX(),sommet2.getY(), x, y));
        	rho = theta - phi;
        }
    }*/

}
