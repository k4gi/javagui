import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JasonDoesntKnow extends JFrame {
	
	public JasonDoesntKnow() {
		initUI();
	}
	
	private void outputToFile(String arg){ //i don't know how to do this so this code doesn't work
		PrintWriter out = null;
		
		try {
			out = new PrintWriter("./jasonout.txt");
			out.println(arg);
		} finally {
			if (out != null) {
				out.close();
			}
		}
		
	}
	
	private void initUI() {
		var field = new JTextField(20);
		field.addActionListener(new ActionListener() { //i don't know so many things
			@Override
			public void actionPerformed(ActionEvent event) {
				outputToFile(field.getText());
			}
		});
		
		createLayout(field);
		setTitle("Jason Doesn't Know");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createSequentialGroup()
			.addComponent(arg[0])
		);
		gl.setVerticalGroup(gl.createParallelGroup()
			.addComponent(arg[0])
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