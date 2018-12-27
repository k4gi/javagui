import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class QuitButtonEx extends JFrame {

    public QuitButtonEx() {

        initUI();
    }

    private void initUI() {

        var quitButton = new JButton("Quit");
		//add more button
		var blankButton = new JButton("Hi");
		var eggButton = new JButton("egg");

        quitButton.addActionListener((event) -> System.exit(0));

        createLayout(quitButton, blankButton, eggButton);

        setTitle("Quit button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
				//add buttons
				.addComponent(arg[1])
				.addComponent(arg[2])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
				//add buttons
				.addComponent(arg[1])
				.addComponent(arg[2])
        );
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            var ex = new QuitButtonEx();
            ex.setVisible(true);
        });
    }
}