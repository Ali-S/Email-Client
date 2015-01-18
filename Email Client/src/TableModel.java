import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel{
	
	private Object[][] tableData;
	public TableModel(){
		
	}
	public void setValueAt (Object aValue, int rowIndex) {
		tableData[rowIndex][1] = aValue;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex] [columnIndex];
	}

}
