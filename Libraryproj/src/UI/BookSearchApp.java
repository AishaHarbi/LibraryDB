package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BookSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	booksDOA.BooksDOA bookdoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearchApp frame = new BookSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookSearchApp() {
		setTitle("Book Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("book's title motherfucker");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton searchbutt = new JButton("search now");
		searchbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<core.Books> list = null;
				try {
				//get book's title from the text field
				String bookTitle = textField.getText();
				
				
				//call doa and get book info for the title
				//if book's title is empty, get all book
				//print out books
				
				
				
					bookdoa = new booksDOA.BooksDOA();
					if (bookTitle != null && bookTitle.trim().length() > 0) {
						
						list = bookdoa.findBook(bookTitle);
					}
					else {
					list =	bookdoa.getAllBooks();
					}
				//create the table model
					BookTableModel model = new BookTableModel(list);
					table.setModel(model);
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(BookSearchApp.this, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			
			}
		});
		panel.add(searchbutt);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Add a book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create dialog
				AddBookdialog dialog = new AddBookdialog(BookSearchApp.this,bookdoa);
				
				//show dialog
				dialog.setVisible(true);
				
				
			}
		});
		panel_1.add(btnNewButton);
	}

	public void refreshBookDBView() throws SQLException {
		
		try {
			List<core.Books> list = bookdoa.getAllBooks();
			BookTableModel model = new BookTableModel(list);
			table.setModel(model);
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(BookSearchApp.this, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
	}

}
