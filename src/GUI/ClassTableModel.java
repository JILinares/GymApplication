package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import model.Class;
import model.Member;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ClassTableModel extends AbstractTableModel implements TableModelListener{
	
	private static String[] names = {"Is Enrolled", "Class Name", "Description", "Capacity", "# Enrolled"};
	private  ArrayList<Class> data = new ArrayList<>();
	//private  ArrayList<Object> modifiedData = new ArrayList<>();
	private boolean checked[];
	private int newEnrollment[];
	
	private void copyEnrollment()
	{	newEnrollment = new int[data.size()];
		for(int i = 0;i < data.size();i++){
			newEnrollment[i] = data.get(i).getEnrollment();
		}
		
	}
	
	public void readEnrollmentOfMember(Member member)
	{
		ArrayList<Class> enrolled = member.getClasses();
		
		for(int i=0;i < checked.length; i++)
			if(enrolled.contains(data.get(i)))
			{
				checked[i]=true;
			}
		
	}
	
	public ArrayList<Class> getNewEnrollment()
	{
		ArrayList<Class> ne = new ArrayList<Class>();
		for(int i = 0; i < checked.length; i++)
		{
			ne.add(data.get(i));
		}
		return ne;
	}
	
	public ClassTableModel(){
		data = Class.readFile();
		checked = new boolean[data.size()];
		copyEnrollment();

	}
	
	public ClassTableModel(ArrayList<Class> data){
		this.data = data; 
		if (data == null) {checked=new boolean[1];}
		else {checked = new boolean[data.size()];}
		copyEnrollment();
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
		} else if (columnIndex == 4)
			{return newEnrollment[rowIndex];}
		
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
		System.out.println(arg0.getFirstRow());
		
	}
	
	@Override
	public void setValueAt(Object value,int rowIndex,int columnIndex){
		//Will be responsible for setting the value if a student is signed up for this class.
		boolean newval= (Boolean) value;
		if (columnIndex == 0)
		{		if (!checked[rowIndex] && newval) //if previously unchecked
				{
					if(data.get(rowIndex).getCapacity() > newEnrollment[rowIndex])
					{
						checked[rowIndex]=newval;
						newEnrollment[rowIndex]++;
						this.fireTableCellUpdated(rowIndex, 4);
					}else{
						JOptionPane.showMessageDialog(null, 
								"Class is Full"
								, "Gym App", JOptionPane.ERROR_MESSAGE);
					}
				} else if(checked[rowIndex] && !newval){
					checked[rowIndex]=newval;
					newEnrollment[rowIndex]--;
					this.fireTableCellUpdated(rowIndex, 4);
				}
		}
		
	}

}
