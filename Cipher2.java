import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;

public class Cipher extends JFrame {
	public Cipher() {
		initUI();
	}
	
	private String vigenereCipher(String in, boolean flip_in, String key_in) {
		String out = "";
		String wheel_1 = "abcdefghijklmnopqrstuvwxyz";
		String wheel_2 = wheel_1;
		in = in.toLowerCase(); //removes case worries?
		key_in = key_in.toLowerCase();
		
		
		/*
		if(flip_in) { // thanks to https://stackoverflow.com/a/20252287
			for(int i = 0; i < key_in; i++) {
				wheel_2 = wheel_2.charAt(wheel_2.length() - 1) + wheel_2.substring(0, wheel_2.length() - 1);
			}
		} else {
			for(int i = 0; i < key_in; i++) {
				wheel_2 = wheel_2.substring(1, wheel_2.length()) + wheel_2.charAt(0);
			}
		}
		
		//ok its output time
		for(int i = 0; i < in.length(); i++) {
			int j = wheel_1.indexOf(in.charAt(i));
			if(j != -1) {
				out = out + wheel_2.charAt(j);
			}
		}
		
		return out; */
		
		return key_in + "..." + in;
	}
	
	private void initUI() {
		var in_field = new JTextArea("Type here",5,50);
		in_field.setLineWrap(true);
		var out_field = new JTextArea("Read here",5,50);
		out_field.setLineWrap(true);
		
		var keydisp = new JLabel("...");
		/*
		var slide = new JSlider(1,26,1);
		slide.setMinorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.addChangeListener((ChangeEvent e) -> {
			keydisp.setText(Integer.toString(slide.getValue()));
		}); */
		
		var keyword = new JTextField("Key goes here");
		
		var flip = new JToggleButton("Press to flip");
		
		var press_this = new JButton("Press to encode text!");
		press_this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out_field.setText( vigenereCipher(in_field.getText(), flip.isSelected(), keyword.getText() ) );
			}
		});
		
		createLayout(flip, keydisp, keyword, in_field, press_this, out_field);
		setTitle("It's cipher tiem");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addGroup(gl.createSequentialGroup()
				.addComponent(arg[0])
				.addComponent(arg[1])
				.addComponent(arg[2])
			)
			.addComponent(arg[3])
			.addComponent(arg[4])
			.addComponent(arg[5])
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup()
				.addComponent(arg[0])
				.addComponent(arg[1])
				.addComponent(arg[2])
			)
			.addComponent(arg[3])
			.addComponent(arg[4])
			.addComponent(arg[5])
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