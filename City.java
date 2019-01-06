import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
	This project is a city-building game of some sort.
	I don't know how I would show graphics in a GroupLayout, so...
	I'm going to represent it with text. Maybe a JList.
	Buttons down one side to control the game.
	---
	Which brings me to, what is the player going to do?
	Well, there's going to be a settlement to take care of, of course.
	A number of people. A number of houses. Food and wood.
	The player will assign tasks for each person.
	Tasks like 'cut wood' or 'fish/hunt/farm' or 'build something'.
	This game is turn-based. A button will advance time, and the game will report what happened.
	Example:	2 fishers have caught 19 fish.
				3 woodcutters have cut 54 wood.
				1 builder is working on a house. 2 turns left.
				---
				The people have eaten 12 food and burned 6 firewood.
	Ok this seems like a nice start. Making decisions. Yay.
	Now these actions require some kind of interface.
	A set of number inputs to assign workers. JSpinners or JTextFields or something.
	Something a little more complicated to manage building ... buildings. idk.
	And finally a 'confirm' button to make sure we're not assigning too many workers before we actually tick forward.
	---
	That's enough of that talk for now; I wanna talk about the greater area, outside town.
	I want there to be other towns. I want to interact with them. Trade? War? Immigration? A post service? Who knows.
	I want to send the townspeople out into the world, to explore. Find stuff. Map the area.
	This sure is going to be implemented later. I'm not going to try and guess how.
*/
public class City extends JFrame {
	public City() {
		int total_workers = 10; //ten? why not
		
		var fisher_label = new JLabel("Fishers:");
		var cutter_label = new JLabel("Cutters:");
		
		var fisher_read = new JTextField();
		var cutter_read = new JTextField();
		
		var check_workers = new JButton("Check Workers");
		check_workers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( check_workers_here( total_workers, fisher_read.getText(), cutter_read.getText() ) ) {
					fisher_read.setText("Hey it worked");
				} else cutter_read.setText("coffee broke");
			}
		});
		
		createLayout(fisher_label, fisher_read, cutter_label, cutter_read, check_workers);
		
		setTitle("City Builder");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
	}
	
	private boolean check_workers_here( int total, String fisher, String cutter ) {
		/*
			ive gotta put checks here (lol) for like...
				if the player gave the right kind of input (numbers!)
				then if the numbers together are greater than total
				wait was there more to it? it felt more complicated in my head
		*/
		int fisher_p;
		int cutter_p;
		try {
			fisher_p = Integer.parseInt(fisher);
			cutter_p = Integer.parseInt(cutter);
		} catch(NumberFormatException e) {
			return false;
		}
		if( fisher_p + cutter_p > total ) {
			return false;
		} else {
			return true;
		}
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup()
				.addGroup(gl.createSequentialGroup()
					.addComponent(arg[0])
					.addComponent(arg[1])
				)
				.addGroup(gl.createSequentialGroup()
					.addComponent(arg[2])
					.addComponent(arg[3])
				)
				.addComponent(arg[4])
			)
			
		);
		gl.setVerticalGroup(gl.createParallelGroup()
			.addGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup()
					.addComponent(arg[0])
					.addComponent(arg[1])
				)
				.addGroup(gl.createParallelGroup()
					.addComponent(arg[2])
					.addComponent(arg[3])
				)
				.addComponent(arg[4])
			)
		);
		
		pack(); //auto-size
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            var ex = new City();
            ex.setVisible(true);
        });
	}
}