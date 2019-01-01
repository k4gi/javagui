import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cipher extends JFrame {
	public Cipher() {
		initUI();
	}
	
	private String caesarCipher(String in) {
		int key = 1;
		String out = "";
		// create two Strings of each letter in sequence
		// shift one over using split() and join()
		//for each character in input:
		//	find the character in sequence 1
		//  find character at same index in sequence 2
		//  concatenate to output
		
		return in;
	}
	
	private void initUI() {
		var in_field = new JTextArea("Type here",5,50);
		in_field.setLineWrap(true);
		var out_field = new JTextArea("Read here",5,50);
		out_field.setLineWrap(true);
		
		var press_this = new JButton("Press to encode text!");
		press_this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out_field.setText( caesarCipher(in_field.getText()) );
			}
		});
		
		
		createLayout(in_field, press_this, out_field);
		setTitle("It's cipher tiem");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addComponent(arg[0])
			.addComponent(arg[1])
			.addComponent(arg[2])
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addComponent(arg[0])
			.addComponent(arg[1])
			.addComponent(arg[2])
		);
		
		pack(); //auto-size
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            var ex = new Cipher();
            ex.setVisible(true);
        });
	}
}