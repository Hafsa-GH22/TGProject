package elements;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DegreSommets extends AbstractTableModel {
	private ArrayList<Sommet> sommets;
	
	public DegreSommets(ArrayList<Sommet> sommets)
	{
		this.sommets = sommets;
	}
	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public int getColumnCount() {
		return sommets.size();
	}
	
	 @Override
    public String getColumnName(int column) {
        return sommets.get(column).getNameSommet();
    }
	 
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
//		if(rowIndex == 0)
//			return sommets.get(columnIndex).getNameSommet();
		return sommets.get(columnIndex).getDegree();
	}

}
