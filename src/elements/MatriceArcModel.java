package elements;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MatriceArcModel extends AbstractTableModel {
	private String [][] matriceArc;
	private ArrayList<Sommet> sommets;
	
	// -------------------------- Constructeur --------------------------
	public MatriceArcModel(String [][] matriceArc, ArrayList<Sommet> sommets)
	{
		this.matriceArc = matriceArc;
		this.sommets = sommets;
	}
	
	// -------------------------- Getters et Setters --------------------------
	public String[][] getMatriceInc() {
		return matriceArc;
	}

	public ArrayList<Sommet> getSommets() {
		return sommets;
	}
	
	public void setMatriceInc(String[][] matriceArc) {
		this.matriceArc = matriceArc;
	}

	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}
	
	// -------------------------- Fonctions --------------------------
	@Override
	public int getRowCount() {
		return this.matriceArc.length;
	}

	@Override
	public int getColumnCount() {
		return this.matriceArc.length + 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0)
			return this.sommets.get(rowIndex).getNameSommet();
		return this.matriceArc[rowIndex][columnIndex-1];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//super.setValueAt(aValue, rowIndex, columnIndex);
		this.matriceArc[rowIndex][columnIndex] = (String) aValue;
	}
	
	@Override
	public Class<? extends Object> getColumnClass(int columnIndex) {
		return String.class;
	}
	
	@Override
	public String getColumnName(int column) {
		if(column == 0)
			return "Sommet";
		return this.sommets.get(column-1).getNameSommet();
		//return super.getColumnName(column);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//return super.isCellEditable(rowIndex, columnIndex);
		/*if(columnIndex != 0)
			return true;
		else
			return false;*/
		return false;
	}

}
