import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import java.util.Hashtable;

public class Notes extends JFrame {
	boolean debugging = true;
	private void debug(String in) {
		if(debugging) System.out.println(in);
	}
	
	public Notes() {
		var title_field = new JTextArea("20XX-XX-XX title",1,50);
		var in_field = new JTextArea("HELLO THERE",10,50);
		in_field.setLineWrap(true);
		
		var pane = getContentPane();
		var gl = new GroupLayout(pane);
		pane.setLayout(gl);
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addComponent(title_field)
			.addComponent(in_field)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addComponent(title_field)
			.addComponent(in_field)
		);
		pack(); //auto-size window
		
		setTitle("Notes for the Note God");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			var ex = new Notes();
			ex.setVisible(true);
		});
	}
}