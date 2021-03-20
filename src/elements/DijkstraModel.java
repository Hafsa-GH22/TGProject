package elements;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

public class DijkstraModel extends AbstractTableModel {
	private final HashMap<String,Double> l;
    private final HashMap<String,String> p;

    public DijkstraModel(HashMap<String, Double> l, HashMap<String, String> p) {
        this.l = l;
        this.p = p;
    }

    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return l.size() + 1;
    }

    @Override
    public String getColumnName(int column) {
    	if(column == 0)
    		return "Sommets";
        return (String) l.keySet().toArray()[column - 1];
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == 0)
        {
        	if(columnIndex == 0)
        		return "L : longueur";
        	else
        		return l.get(getColumnName(columnIndex));
        }
        else
        {
        	if(columnIndex == 0)
        		return "P : prédécesseur";
        	else
        		return p.get(getColumnName(columnIndex));
        }
    }
}
