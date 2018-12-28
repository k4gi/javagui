import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class JasonDoesntKnow extends JFrame {
	
	public JasonDoesntKnow() {
		initUI();
	}
	
	private void outputToFile(String arg) throws IOException{ //i don't know how to do this so this code doesn't work
		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream("output.txt");
			int c;
			while ((c = arg.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
		
	}
	
	private void initUI() {
		var field = new JTextField(20);
		field.addActionListener(new ActionListener() { //i don't know so many things
			//@Override
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