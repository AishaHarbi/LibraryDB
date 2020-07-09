package booksDOA;

import java.util.*;

import com.mysql.jdbc.jdbc2.optional.PreparedStatementWrapper;

import core.Books;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;
public class BooksDOA {
	private Connection myconn;
	public BooksDOA() throws Exception {
		Properties prop = new Properties();
		// get properties info
		prop.load(new FileInputStream("essen.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
				//"jdbc:mysql://localhost:3306/libproject?verifyServerCertificate=false&useSSL=true";
				//
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// connect to DB
		myconn = DriverManager.getConnection(dburl,user,password);
		System.out.println("connection successful to "+ dburl);
	}
	public List<core.Books> findBook(String bookTitle) throws SQLException{
		List<core.Books> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			bookTitle += "%";
			myStmt = myconn.prepareStatement("select * from BOOKS where book_title like ?");

			myStmt.setString(1, bookTitle);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				core.Books tempEmployee = convertRowToBook(myRs);
				list.add(tempEmployee);
			}

			return list;
		}
		finally {
			close(myStmt, myRs);
		}
		
		
	}
	public void addBook(core.Books book) throws SQLException {
		PreparedStatement myst = null;
		try {
			//prepare statement
			myst = myconn.prepareStatement("INSERT into BOOKS "
					+ "(isbn ,book_title, category, rental_price, status, author, publisher)"
					+ " values( ?, ?, ?, ?, ?, ?, ?)");
			//set values
			myst.setLong(1, book.getISBN());
			myst.setString(2, book.getBookTitle());
			myst.setString(3, book.getCategory());
			myst.setLong(4, (long) book.getRental_price());
			myst.setString(5, book.getStatus());
			myst.setString(6, book.getAuthor());
			myst.setString(7, book.getPublisherHouse());
			//exe update
			myst.executeUpdate();
			
		}
		finally {
			myst.close();
		}
		
	}
	
	
	
	
	
	
	
	public List<core.Books> getAllBooks() throws SQLException{
		List<core.Books> books = new ArrayList<>();
		Statement mystat = null;
		ResultSet myresult = null;
		try {
			mystat = myconn.createStatement();
			myresult = mystat.executeQuery("SELECT * FROM BOOKS");
			while (myresult.next()) {
				core.Books tempbook = convertRowToBook(myresult);
				books.add(tempbook);
				//System.out.println(tempbook.toString());
			
				
			}
			return books;
		}
		finally {
			close(myconn,mystat,myresult);
		}
		
	}
	private void close(Connection myConn,Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
		
	}
	private void close(Statement myst, ResultSet myres) throws SQLException {
		close(null,myst,myres);
	}
	private core.Books convertRowToBook(ResultSet myresult) throws SQLException {
		int ISBN = myresult.getInt("ISBN");
		String bookTitle = myresult.getString("book_title");
		String category = myresult.getString("category");
		int rental_price = myresult.getInt("rental_price");
		String status = myresult.getString("status");
		String publisherHouse = myresult.getString("publisher");
		String author = myresult.getString("author");
		core.Books temp = new core.Books(ISBN, bookTitle, category, rental_price, status, publisherHouse, author);
		return temp;
	}
	public static void main(String[] args) throws Exception {
		BooksDOA obj = new BooksDOA();
		//System.out.println(obj.getAllBooks());
		System.out.println(obj.findBook("Zami"));
	}

}
