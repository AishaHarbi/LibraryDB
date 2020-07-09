package core;

import java.math.BigDecimal;

public class Books {
int ISBN;
String bookTitle;
String category;
int rental_price;
String status;
String publisherHouse;
String author;
public Books(int iSBN, String bookTitle, String category, int rental_price, String status, String publisherHouse,
		String author) {
	
	ISBN = iSBN;
	this.bookTitle = bookTitle;
	this.category = category;
	this.rental_price = rental_price;
	this.status = status;
	this.publisherHouse = publisherHouse;
	this.author = author;
}
public int getISBN() {
	return ISBN;
}
public void setISBN(int iSBN) {
	ISBN = iSBN;
}
public String getBookTitle() {
	return bookTitle;
}
public void setBookTitle(String bookTitle) {
	this.bookTitle = bookTitle;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getRental_price() {
	return rental_price;
}
public void setRental_price(int rental_price) {
	this.rental_price = rental_price;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getPublisherHouse() {
	return publisherHouse;
}
public void setPublisherHouse(String publisherHouse) {
	this.publisherHouse = publisherHouse;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String toString() {
return String
		.format("Book [ISBN=%s, book title=%s, category=%s, rental price=%s, status=%s, author =%s, publisher hous=%s]",
				ISBN, bookTitle, category, rental_price, status,author,publisherHouse);
}
}
