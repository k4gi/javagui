import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;

public class PointsEx extends JFrame {

    public PointsEx() {
        
        initUI();
    }

    private void initUI() {

        DrawPanel drawPanel = new DrawPanel();
		
		var quitButton = new JButton("Quit");
		//add more button
		var blankButton = new JButton("Hi");
		var eggButton = new JButton("egg");

        quitButton.addActionListener((event) -> System.exit(0));

        //createLayout(quitButton, blankButton, eggButton);
        add(drawPanel);

        setSize(350, 250);
        setTitle("Points");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            PointsEx ex = new PointsEx();
            ex.setVisible(true);
        });
    }
}

class DrawPanel extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        for (int i = 0; i <= 1000; i++) {

            Dimension size = getSize();
            Insets insets = getInsets();

            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;

            Random r = new Random();
            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}