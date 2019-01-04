import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTable;
import java.awt.EventQueue;

public class TableTest extends JFrame {
	public TableTest() {
		String[] columnNames = {"First Name",
                                "Last Name",
                                "Sport",
                                "# of Years",
                                "Vegetarian"};

        Object[][] data = {
	    {"Kathy", "Smith",
	     "Snowboarding", new Integer(5), new Boolean(false)},
	    {"John", "Doe",
	     "Rowing", new Integer(3), new Boolean(true)},
	    {"Sue", "Black",
	     "Knitting", new Integer(2), new Boolean(false)},
	    {"Jane", "White",
	     "Speed reading", new Integer(20), new Boolean(true)},
	    {"Joe", "Brown",
	     "Pool", new Integer(10), new Boolean(false)}
        };
		
		var table = new JTable(data, columnNames);
		
		createLayout(table);
		
		setTitle("TableTest");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
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
		
		pack();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            var ex = new TableTest();
            ex.setVisible(true);
        });
	}
}