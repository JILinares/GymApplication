package src.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import model.Class;
import model.Member;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ClassTableModel extends AbstractTableModel implements TableModelListener{
	
	private String[] names = {"Is Enrolled", "Class Name", "Description", "Capacity", "# Enrolled"};
	private  ArrayList<Class> data = new ArrayList<>();
	private  ArrayList<Object> modifiedData = new ArrayList<>();
	private boolean checked[];
	
	public ClassTableModel(){
		data = Class.readFile();
		checked = new boolean[data.size()];
		//for(int i = 0;i < data.size();i++){
		//	for(int j = 0; j < )
			
		//}
	}
	
	public ClassTableModel(ArrayList<Class> data){
		this.data = data; 
		if (data == null) {checked=new boolean[1];}
		else {checked = new boolean[data.size()];}
	}
	
	//private static Object[][] dataSet = getValues();
	
	@Override
	public String getColumnName(int i){
		return names[i];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		Class cla = (Class) data.get(rowIndex);
		if(columnIndex == 0){
			return checked[rowIndex];
		}
		return cla.getValue(columnIndex - 1);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex,int columnIndex){
		if (columnIndex == 0) {
			return true;
		}
		return false;
	}
	
	public java.lang.Class<?> getColumnClass(int c){
		if(c == 0){ return Boolean.class; }
		return String.class;
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setValueAt(Object value,int rowIndex,int columnIndex){
		//Will be responsible for setting the value if a student is signed up for this class.
		if (columnIndex == 0){checked[rowIndex]=(Boolean)value;}
	}

}
