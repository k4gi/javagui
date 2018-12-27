import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	// aaaaaaaaaaaaaaaaaaaaaaaaa
	public static void main(String[] args) {
		Frame geraldo = new Frame("HEADER goes HERE");
		// aaaaaaaaaaaaaaaaaaaaaaaaa
		Component button = new Button("label for a button");
		geraldo.add(button, BorderLayout.WEST);
		geraldo.setSize(500,500);
		geraldo.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
        });
		geraldo.setVisible(true);
		// aaaaaaaaaaaaaaaaaaaaaaaaa i did a thing
	}
}

