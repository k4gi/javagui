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

public class JasonDoesntKnow extends JFrame {
	
	public JasonDoesntKnow() {
		initUI();
	}
	
	private void outputToFile(String arg) {
		PrintWriter out = null;
		
		try {
			out = new PrintWriter("./jasonout.txt");
			out.println(arg);
		} catch (IOException e) {
			
		} finally {
			if (out != null) {
				out.close();
			}
		}
		
	}
	
	private void initUI() {
		var field = new JTextField(20);
		field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				outputToFile(field.getText());
			}
		});
		
		var pressA = new JButton("A");
		var pressB = new JButton("B");
		var pressC = new JButton("C");
		
		createLayout(field, pressA, pressB, pressC);
		setTitle("Jason Doesn't Know");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addComponent(arg[0])
			.addGroup(gl.createSequentialGroup()
				.addComponent(arg[1])
				.addComponent(arg[2])
				.addComponent(arg[3])
			)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addComponent(arg[0])
			.addGroup(gl.createParallelGroup()
				.addComponent(arg[1])
				.addComponent(arg[2])
				.addComponent(arg[3])
			)
		);
		
		pack(); //auto-size
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            var ex = new JasonDoesntKnow();
            ex.setVisible(true);
        });
    }
}