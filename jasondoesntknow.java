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

public class JasonDoesntKnow extends JFrame {
	
	public JasonDoesntKnow() {
		initUI();
	}
	
	private void initUI() {
		var listModel = new DefaultListModel();
		var textDisplay = new JList(listModel);
		
		var field = new JTextField(20);
		field.addActionListener(new ActionListener() {
			@Override //what is this
			public void actionPerformed(ActionEvent event) {
				outputToFile(field.getText());
			}
		});
		
		var pressA = new JButton("A");
		pressA.addActionListener(new ActionListener() {
			@Override //what is this
			public void actionPerformed(ActionEvent event) {
				updateDisplay(listModel, "Button A");
			}
		});
		var pressB = new JButton("B");
		pressB.addActionListener(new ActionListener() {
			@Override //what is this
			public void actionPerformed(ActionEvent event) {
				updateDisplay(listModel, "Button B");
			}
		});
		var pressC = new JButton("C");
		pressC.addActionListener(new ActionListener() {
			@Override //what is this
			public void actionPerformed(ActionEvent event) {
				updateDisplay(listModel, "Button C");
			}
		});
		
		createLayout(field, pressA, pressB, pressC, textDisplay);
		setTitle("Jason Doesn't Know");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
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
	
	private void updateDisplay(DefaultListModel model, String arg) {
		if(!model.isEmpty()) {
			model.clear();
		}
		model.addElement(arg);
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addComponent(arg[0],300,300,300)
			.addGroup(gl.createSequentialGroup()
				.addComponent(arg[1],100,100,100)
				.addComponent(arg[2],100,100,100)
				.addComponent(arg[3],100,100,100)
			)
			.addComponent(arg[4],300,300,300)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addComponent(arg[0],50,50,50)
			.addGroup(gl.createParallelGroup()
				.addComponent(arg[1],50,50,50)
				.addComponent(arg[2],50,50,50)
				.addComponent(arg[3],50,50,50)
			)
			.addComponent(arg[4],100,100,100)
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