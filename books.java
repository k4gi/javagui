import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;

//didn't immediately work doesn't matter

public class Books extends JFrame {
	public Books() {
		var namehere = new JLabel("I'm hungry");
		var bookhere = new BookEntry("Why We Lie Sometimes", "Hesta", 2018);
		
		var bTitle = bookhere.title[0];
		var bAuthor = bookhere.author[0];
		var bYear = bookhere.year;
		
		createLayout(namehere);
		
		setTitle("Bok");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addComponent(arg[0])
		);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addComponent(arg[0])
		);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			var ex = new Books();
			ex.setVisible(true);
		});
	}
}

public class BookEntry {
	String[] title;
	String[] author;
	int year;
	
	public BookEntry(String a, String b, int c) {
		title[0] = a;
		author[0] = b;
		year = c;
	}
}