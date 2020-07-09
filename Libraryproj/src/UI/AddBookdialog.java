package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import booksDOA.BooksDOA;
import core.Books;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class AddBookdialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	
	private BooksDOA bookdoa;
	private BookSearchApp app;
	

	public AddBookdialog(BookSearchApp app, BooksDOA obj) {
		this();
		
		this.bookdoa = obj;
		this.app = app;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddBookdialog dialog = new AddBookdialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBookdialog() {
		setTitle("Add a book");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("450px"),},
			new RowSpec[] {
				RowSpec.decode("239px"),
				RowSpec.decode("39px"),}));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "1, 1, fill, fill");
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("ISBN");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2");
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("BookTitle");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4");
			textField_1.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Category/Genre");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 6");
			textField_2.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Rental price");
			contentPanel.add(lblNewLabel_3, "2, 8, right, default");
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3, "4, 8");
			textField_3.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Status/Availability");
			contentPanel.add(lblNewLabel_4, "2, 10, right, default");
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4, "4, 10");
			textField_4.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Author's name");
			contentPanel.add(lblNewLabel_5, "2, 12, right, default");
		}
		{
			textField_5 = new JTextField();
			contentPanel.add(textField_5, "4, 12");
			textField_5.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Publisher");
			contentPanel.add(lblNewLabel_6, "2, 14, right, default");
		}
		{
			textField_6 = new JTextField();
			contentPanel.add(textField_6, "4, 14, fill, default");
			textField_6.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, "1, 2, fill, top");
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveBook();
					}

					private void saveBook() {
						//get the book's info from the gui 
						try {
							int isbn = Integer.parseInt(textField.getText());
							String bookTitle = textField_1.getText();
							String category = textField_2.getText();
							int rental_price = Integer.parseInt(textField_3.getText());
							String status = textField_4.getText();
							String author = textField_5.getText();
							String publisher = textField_6.getText();
							//save to the db
							Books temp = new Books(isbn,bookTitle,category,rental_price,status,author,publisher);
							bookdoa.addBook(temp);
							
						   //close dialog
						  setVisible(false);
						  dispose();
						
						//refresh gui table
						app.refreshBookDBView();
						
						//show success message
						JOptionPane.showMessageDialog(app, "Book addes successfully!","Book added",JOptionPane.INFORMATION_MESSAGE);
						
						}
						catch (Exception e) {
							JOptionPane.showMessageDialog(app, "Error saving the book: " + e, "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
