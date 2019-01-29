import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;



public class Cipher extends JFrame {
	boolean debugging = true;
	
	public Cipher() {
		initUI();
	}
	
	private String dummyOutput(String in) {
		return in;
	}
	
	private void debug(String in) {
		if(debugging) System.out.println(in);
	}
	
	private String enigmaCipher(String in) {
		in = in.toUpperCase();
		String out = "";
		String keyboard = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String plugboard = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //default! not messing with this rn
		
		/*
			Gotta say a little bit about the stepping mechanism.
			Rotors I-V  have one step, rotors VI-VIII have two.
			The thing is they're written down as the place on the entry character
			so I guess I do need to keep track of both after all
			come to think of it why did I think I didn't? Of course I need to know where its turned to!
			Anyway. Yes.
			I'm just going to write them down right now as A-B, meaning when A clicks to B
		*/
		String[] wheel_i = { // Q-R
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"EKMFLGDQVZNTOWYHXUSPAIBRCJ"};
		String[] wheel_ii = { // E-F
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"AJDKSIRUXBLHWTMCQGZNPYFVOE"};
		String[] wheel_iii = { // V-W
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"BDFHJLCPRTXVZNYEIWGAKMUSQO"};
		String[] wheel_iv = { // J-K
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"ESOVPZJAYQUIRHXLNFTGKDCMWB"};
		String[] wheel_v = { // Z-A
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"VZBRGITYUPSDNHLXAWMJQOFECK"};
		String[] wheel_vi = { // Z-A, M-N
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"JPGVOUMFYQBENHZRDKASXLICTW"};
		String[] wheel_vii = { // Z-A, M-N
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"NZJHGRCXMYSWBOUFAIVLPEKQDT"};
		String[] wheel_viii = { // Z-A, M-N
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"FKQHTLXOCBJSPDZRAMEWNIUYGV"};
		//reflectors don't turn so i guess i can just compare them to the keyboard	
		String reflector_b = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
		String reflector_c = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
		// these wheels copied from http://users.telenet.be/d.rijmenants/en/enigmatech.htm
		
		/*
			Here is a video of a paper enigma machine in action.
			https://www.youtube.com/watch?v=pZsuxZXN33g
			Notice that the wheels, rotors, whatever, move BEFORE encoding the letter typed!
			
			The Enigma machine works by sending an electrical signal...
			From the keyboard, to the plugboard, to a static wheel that connects to the moving wheels...
			From there to wheel III, to wheel II, to wheel I, to the reflector, then back again...
			Wheel I, II, III, static wheel, plugboard, then the lampboard. Result!
			Oh right and in some versions there are four wheels!
			
			ugh i'm going to write that out again.
			1. Input character
			2. Shift rotors (or wheels) (do i have to pick one?)
			3. Plugboard
			4. Wheel III
			5. Wheel II
			6. Wheel I
			7. Reflector
			8. Wheel I
			9. Wheel II
			10. Wheel III
			11. Plugboard
			12. Output character!!!!!!!
			
			Wheel specs are (apparently) recorded here:
			https://en.wikipedia.org/wiki/Enigma_rotor_details
			http://users.telenet.be/d.rijmenants/en/enigmatech.htm
			https://www.codesandciphers.org.uk/enigma/rotorspec.htm
			The wikipedia page has more rotors than the others
		*/
		
		//I'm realising that I should have written more comments in the other Cipher files...
		//I don't know what's happening! I need to read it carefully...again!
		
		//I'm just going to go for it
		
		//Step 1: get input (that's already done yep)
		debug("Input: " + in);
		//Step 2: shift rotorwheel III, this code from Cipher2.java, line 58 is what I need...
		//wheel_2 = wheel_2.substring(1, wheel_2.length()) + wheel_2.charAt(0);
		//oh I need like 'working directory' wheels
		String[] working_wheel_i = wheel_i;
		String[] working_wheel_ii = wheel_ii;
		String[] working_wheel_iii = wheel_iii; //the numbers, match, yes, just because
		
		//start loop here
		//for each character in the input string
		for(int i = 0; i < in.length(); i++) {
			debug("==========START OF LOOP==========");
			
			char code_letter = in.charAt(i);
			debug("code_letter: " + code_letter);
			if(keyboard.indexOf(code_letter) == -1) { //skip character if it's not a letter, don't move wheels
				continue;
			}
			
			debug("Shifting rotors...");
			//Yeah so clearly there's something wrong with how I'm moving the wheels
			//Alphabet gets shifted, coded letters get shifted, that means it ends up being the same every time
			//So I'm doing it wrong. Gotta figure out the solution.
			//Good news, the code "works" at least!
		
			working_wheel_iii[0] = working_wheel_iii[0].substring(1, working_wheel_iii[0].length()) + working_wheel_iii[0].charAt(0);
			working_wheel_iii[1] = working_wheel_iii[1].substring(1, working_wheel_iii[1].length()) + working_wheel_iii[1].charAt(0);
			//i really hope i can just edit the strings like this
			//time for checking if i need to do it again
			if(working_wheel_iii[0].charAt(0) == 'W') { //HEY JASON DON"T LEAVE THIS HARDCODED
				
				working_wheel_ii[0] = working_wheel_ii[0].substring(1, working_wheel_ii[0].length()) + working_wheel_ii[0].charAt(0);
				working_wheel_ii[1] = working_wheel_ii[1].substring(1, working_wheel_ii[1].length()) + working_wheel_ii[1].charAt(0);
				
				if(working_wheel_ii[0].charAt(0) == 'F') { //MORE HARDCODED CHARS
					
					working_wheel_i[0] = working_wheel_i[0].substring(1, working_wheel_i[0].length()) + working_wheel_i[0].charAt(0);
					working_wheel_i[1] = working_wheel_i[1].substring(1, working_wheel_i[1].length()) + working_wheel_i[1].charAt(0);
					//since we're only doing three wheels (for now) we can stop here. The reflector doesn't move.
				}
			} //at this point it still compiles (though we're not using this code yet) so its probably fine right??
			//this is the end of turning the wheels now i need to encode
			//just a thought how the ufck am i going to test this code
			
			debug( "W-I:   " + working_wheel_i[0] + " / " + working_wheel_i[1] );
			debug( "W-II:  " + working_wheel_ii[0] + " / " + working_wheel_ii[1] );
			debug( "W-III: " + working_wheel_iii[0] + " / " + working_wheel_iii[1] );
			
			//Step 3: plugboard.... ummm its first thing in the morning i'll come back later
			//need to set up the main loop, so I can tell which character to be looking at, to go forward from here
			
			code_letter = plugboard.charAt( keyboard.indexOf(code_letter) );
			debug("Plugboard... " + code_letter);
			
			//just having some trouble here working out the code...
			//i'm sure it'll come together soon but its confusing...
			//found an Enigma project made with an arduino. Pretty cool.
			// https://www.instructables.com/id/Make-your-own-Enigma-Replica/
			//it's got the code there too so I can check that out when I'm done
			
			
			/*
				I think the problem I'm having is with the directionality of the wheels.
				Not sure though. I've just gotta work it out, make sure all the wheels are connected the right way.
				Each wheel has contacts that align with whatever position they're each at. It's pretty neat.
				I don't even know what my two sets of characters on each wheel really mean. It's a bit abstract.
				A becomes E? E becomes L? Z becomes J? Or is it the other way around? Does it matter? I guess it does.
				The site I got the characters from has some diagrams that would probably be helpful.
				http://users.telenet.be/d.rijmenants/en/enigmatech.htm
			*/
			
			
			//Step 4: Wheel III
			
			code_letter = working_wheel_iii[1].charAt( keyboard.indexOf(code_letter) );
			debug("Wheel III... " + code_letter);
			
			//Step 5: Wheel II
			
			code_letter = working_wheel_ii[1].charAt( working_wheel_iii[0].indexOf(code_letter) );
			debug("Wheel II...  " + code_letter);
			
			//Step 6: Wheel I
			
			code_letter = working_wheel_i[1].charAt( working_wheel_ii[0].indexOf(code_letter) );
			debug("Wheel I...   " + code_letter);
			
			//Step 7: Reflector ... Let's just use reflector B for now
			
			code_letter = reflector_b.charAt( working_wheel_i[0].indexOf(code_letter) );
			debug("Reflector... " + code_letter);
			
			//Step 8: Wheel I
			
			code_letter = working_wheel_i[1].charAt( working_wheel_ii[0].indexOf(code_letter) );
			debug("Wheel I...   " + code_letter);
			
			//Step 9: Wheel II
			
			code_letter = working_wheel_ii[1].charAt( working_wheel_iii[0].indexOf(code_letter) );
			debug("Wheel II...  " + code_letter);
			
			//Step 10: Wheel III
			
			code_letter = working_wheel_iii[1].charAt( keyboard.indexOf(code_letter) );
			debug("Wheel III... " + code_letter);
			
			//Step 11: Plugboard
			
			code_letter = plugboard.charAt( keyboard.indexOf(code_letter) );
			debug("Plugboard... " + code_letter);
			
			//Step 12: Output
			
			out += code_letter;
			
			
		}
		
		return out;
	}
	
	private void initUI() {
		var in_field = new JTextArea("Type here",5,50);
		in_field.setLineWrap(true);
		var out_field = new JTextArea("Read here",5,50);
		out_field.setLineWrap(true);
		
		
		
		var press_this = new JButton("Press to encode text!");
		press_this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out_field.setText( enigmaCipher( in_field.getText() ) );
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
