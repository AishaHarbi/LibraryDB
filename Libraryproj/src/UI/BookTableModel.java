package UI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Books;

public class BookTableModel extends AbstractTableModel {
	private static final int ISBN = 0;
	private static final int bookTitle = 1;
	private static final int Category = 2;
	private static final int rental_price = 3;
	private static final int status = 4;
	private static final int author = 5;
	private static final int publisher = 6;
    private String[] colName = {"ISBN","BookTitle","Category","rental_price","status","author","publisher"};
	private List<core.Books> books;
	
	
    public BookTableModel(List<Books> books) {
		this.books = books;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return books.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colName.length;
	}
	@Override
    public String getColumnName(int col) {
		return colName[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		core.Books temp = books.get(rowIndex);
		switch (columnIndex) {
		case ISBN:
			return temp.getISBN();
		case bookTitle:
			return temp.getBookTitle();
		case Category:
			return temp.getCategory();
		case rental_price:
			return temp.getRental_price();
		case status:
			return temp.getStatus();
		case author:
			return temp.getAuthor();
		case publisher:
			return temp.getPublisherHouse();
			default:
			return temp.getBookTitle();
		
		}
	
	
	}
	
	public Class getColClass(int c) {
		return getValueAt(0,c).getClass();
	}
	
	
	
	

}
