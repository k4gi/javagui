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
	
	private String enigmaCipher(String in, String in_wheel1, int in_start1, String in_wheel2, int in_start2, String in_wheel3, int in_start3) {
		debug("==========STARTING enigmaCipher()==========");
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
			"EKMFLGDQVZNTOWYHXUSPAIBRCJ",
			"R"};
		String[] wheel_ii = { // E-F
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"AJDKSIRUXBLHWTMCQGZNPYFVOE",
			"F"};
		String[] wheel_iii = { // V-W
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"BDFHJLCPRTXVZNYEIWGAKMUSQO",
			"W"};
		String[] wheel_iv = { // J-K
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"ESOVPZJAYQUIRHXLNFTGKDCMWB",
			"K",};
		String[] wheel_v = { // Z-A
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"VZBRGITYUPSDNHLXAWMJQOFECK",
			"A"};
		String[] wheel_vi = { // Z-A, M-N
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"JPGVOUMFYQBENHZRDKASXLICTW",
			"AN"};
		String[] wheel_vii = { // Z-A, M-N
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"NZJHGRCXMYSWBOUFAIVLPEKQDT",
			"AN"};
		String[] wheel_viii = { // Z-A, M-N
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"FKQHTLXOCBJSPDZRAMEWNIUYGV",
			"AN"};
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
		debug("Slot 1: " + in_wheel1);
		debug("Setting 1: " + in_start1);
		debug("Slot 2" + in_wheel2);
		debug("Setting 2: " + in_start2);
		debug("Slot 3: " + in_wheel3);
		debug("Setting 3: " + in_start3);
		// It's March 15 and I've just got some settings passing through to this function.
		// It's time to make them actually set some things.
		// I've got Strings to tell me which wheels to use in each of the three slots,
		// And I've got ints to tell me what their settings should be.
		// Hey, are the roll-over points still hard-coded just down there?
		// No it looks like I've dealt with that already.


		//Step 2: shift rotorwheel III, this code from Cipher2.java, line 58 is what I need...
		//wheel_2 = wheel_2.substring(1, wheel_2.length()) + wheel_2.charAt(0);
		//oh I need like 'working directory' wheels
		//String[] working_wheel_i = wheel_i;
		//String[] working_wheel_ii = wheel_ii;
		//String[] working_wheel_iii = wheel_iii; //the numbers, match, yes, just because
		String[] working_wheel_i;
		String[] working_wheel_ii;
		String[] working_wheel_iii;
		switch(in_wheel1) {
			case "Wheel I" :
				working_wheel_i = wheel_i;
				break;
			case "Wheel II" :
				working_wheel_i = wheel_ii;
				break;
			case "Wheel III" :
				working_wheel_i = wheel_iii;
				break;
			case "Wheel IV" :
				working_wheel_i = wheel_iv;
				break;
			case "Wheel V" :
				working_wheel_i = wheel_v;
				break;
			case "Wheel VI" :
				working_wheel_i = wheel_vi;
				break;
			case "Wheel VII" :
				working_wheel_i = wheel_vii;
				break;
			case "Wheel VIII" :
				working_wheel_i = wheel_viii;
				break;
			default:
				working_wheel_i = wheel_i;
		}



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
			if( working_wheel_iii[0].charAt(0) == working_wheel_iii[2].charAt(0) ) { //need to add the second tick for wheels VI, VII, VIII
				
				working_wheel_ii[0] = working_wheel_ii[0].substring(1, working_wheel_ii[0].length()) + working_wheel_ii[0].charAt(0);
				working_wheel_ii[1] = working_wheel_ii[1].substring(1, working_wheel_ii[1].length()) + working_wheel_ii[1].charAt(0);
				
				if( working_wheel_ii[0].charAt(0) == working_wheel_ii[2].charAt(0) ) { //need to add the second tick for wheels VI, VII, VIII
					
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
			
			/*
				OK it's most of the way through February and I'm back to finish this off
				I've been mulling it over for a long while
				And I think I have the solution.
				Each wheel has two parts, right!
				The left, which is the normal alphabet
				And the  right, which is the scrambled alphabet.
				The way I have the wheels represented right now is insufficient,
				because it doesn't take into account the relative positions of,
				the contacts of the left and right, and how they're wired to each other.
				...
				ugh, no, that's not right, it's fine how they're represented.
				I just need to do some of the transformations backwards.
				Or something. You know what maybe I should just have another go at it.
				yeah here goes
				
				...
				
				its nearly the end of february and i'm back again trying to make this happen oh boy
				beginning of march! look i'm getting there ok
				
				the steps to move forward (left) through the enigma machine are...
				1. Identify the entry point
					To make it easier I'm going to transform code_letter into its un-rotated form between each wheel
					This is derived from the position of string[0] compared to keyboard
					there's some charAt / indexOf calculations that can take care of that pretty easy
					but they're not coming to mind right now but that's ok
				2. Move through the wheel
					Going forward, this is a simple case of matching the entry character in string [0] with its counterpart in [1]
				3. Identify exit point
					So you have the new letter from string[1]. But it needs to exit at the position of string[0]!
					Tranform code_letter into its un-rotated form, based on that position.
					
				the steps to move backward (right) are...
				1. Identify the entry point
					This is the same as going forward. Transform the un-rotated letter with string[0]
				2. Move through the wheel
					Going through the wheel backwards has been fucking with me for so long but I really think I have it this time.
					We just need to find code_letter in string[1], and transform it into the matching char in string [0]
				3. Identify the exit point
					Since we're going backwards we're already in the right position to un-rotate code_letter
				
				steps to move through the reflector...
				1. transform code_letter
					The reflector doesn't rotate so just find code_letter in keyboard and match the position to the reflector
				2. exit reflector
					you know what i don't think there's anything else to do here
				
				steps to move through the plugboard
				1. do the same thing as for the reflector
			*/
			
			
			//Step 4: Wheel III
			
			//entry
			code_letter = working_wheel_iii[0].charAt( keyboard.indexOf(code_letter) );
			//encode
			code_letter = working_wheel_iii[1].charAt( working_wheel_iii[0].indexOf(code_letter) );
			//exit
			code_letter = keyboard.charAt( working_wheel_iii[0].indexOf(code_letter) );
			
			//code_letter = working_wheel_iii[1].charAt( keyboard.indexOf(code_letter) );
			
			//which contact am i touching?? keyboard.indexOf(code_letter)
			//which letter (left side) is connected to that contact?? i don't know
			//i need to know this
			debug("Wheel III... " + code_letter);
			
			//Step 5: Wheel II
			
			//entry
			code_letter = working_wheel_ii[0].charAt( keyboard.indexOf(code_letter) );
			//encode
			code_letter = working_wheel_ii[1].charAt( working_wheel_ii[0].indexOf(code_letter) );
			//exit
			code_letter = keyboard.charAt( working_wheel_ii[0].indexOf(code_letter) );
			
			//code_letter = working_wheel_ii[1].charAt( working_wheel_iii[0].indexOf(code_letter) );
			debug("Wheel II...  " + code_letter);
			
			//Step 6: Wheel I
			
			//entry
			code_letter = working_wheel_i[0].charAt( keyboard.indexOf(code_letter) );
			//encode
			code_letter = working_wheel_i[1].charAt( working_wheel_i[0].indexOf(code_letter) );
			//exit
			code_letter = keyboard.charAt( working_wheel_i[0].indexOf(code_letter) );
			
			//code_letter = working_wheel_i[1].charAt( working_wheel_ii[0].indexOf(code_letter) );
			debug("Wheel I...   " + code_letter);
			
			//Step 7: Reflector ... Let's just use reflector B for now
			
			code_letter = reflector_b.charAt( keyboard.indexOf(code_letter) );
			
			//code_letter = reflector_b.charAt( working_wheel_i[0].indexOf(code_letter) );
			debug("Reflector... " + code_letter);
			
			//Step 8: Wheel I
			
			//entry
			code_letter = working_wheel_i[0].charAt( keyboard.indexOf(code_letter) ); //ready to go through wheel
			//encode (but backwards!}
			code_letter = working_wheel_i[0].charAt( working_wheel_i[1].indexOf(code_letter) ); //passing backwards through wheel
			//so how about it did i do it this time??? 2019-02-26
			//yes i thignk i might have 2019-03-05
			//exit
			code_letter = keyboard.charAt( working_wheel_i[0].indexOf(code_letter) );
			
			//code_letter = working_wheel_i[1].charAt( working_wheel_ii[0].indexOf(code_letter) );
			debug("Wheel I...   " + code_letter);
			
			//Step 9: Wheel II
			
			//entry
			code_letter = working_wheel_ii[0].charAt( keyboard.indexOf(code_letter) );
			//encode
			code_letter = working_wheel_ii[0].charAt( working_wheel_ii[1].indexOf(code_letter) );
			//exit
			code_letter = keyboard.charAt( working_wheel_ii[0].indexOf(code_letter) );
			
			//code_letter = working_wheel_ii[1].charAt( working_wheel_iii[0].indexOf(code_letter) );
			debug("Wheel II...  " + code_letter);
			
			//Step 10: Wheel III
			
			//entry
			code_letter = working_wheel_iii[0].charAt( keyboard.indexOf(code_letter) );
			//encode
			code_letter = working_wheel_iii[0].charAt( working_wheel_iii[1].indexOf(code_letter) );
			//exit
			code_letter = keyboard.charAt( working_wheel_iii[0].indexOf(code_letter) );
			
			//code_letter = working_wheel_iii[1].charAt( keyboard.indexOf(code_letter) );
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
		
		
		
		
		var label0 = new JLabel("Change wheel settings here...");
		var label1 = new JLabel("Slot I..");
		var label2 = new JLabel("Slot II.");
		var label3 = new JLabel("Slot III");
		var labelp = new JLabel("Change plugboard settings here...");
		
		var slide1 = new JSlider(1,26,1);
		var slide2 = new JSlider(1,26,1);
		var slide3 = new JSlider(1,26,1);

		slide1.setMinorTickSpacing(1);
		slide2.setMinorTickSpacing(1);
		slide3.setMinorTickSpacing(1);
		slide1.setPaintTicks(true);
		slide2.setPaintTicks(true);
		slide3.setPaintTicks(true);
		slide1.setPaintLabels(true);
		slide2.setPaintLabels(true);
		slide3.setPaintLabels(true);
		
		JLabel[] plugboard_labels = new JLabel[26];
		JComboBox[] plugboard_menu = new JComboBox[26];
		for(int i = 0; i < 26; i ++) {
			plugboard_menu[i] = new JComboBox();
			plugboard_menu[i].addItem("---");
		}
		
		String keyboard_copy = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //duplicate
		Hashtable markers = new Hashtable();
		for(int i = 0; i < 26; i ++) {
			markers.put(i+1, new JLabel( Character.toString(keyboard_copy.charAt(i)) ));
			
			plugboard_labels[i] = new JLabel( keyboard_copy.charAt(i) + " = ");
			
			for(int j = 0; j < 26; j ++) {
				if(i != j) plugboard_menu[j].addItem( Character.toString(keyboard_copy.charAt(i)) );
			} 
		}
		
		slide1.setLabelTable(markers);
		slide2.setLabelTable(markers);
		slide3.setLabelTable(markers);

		String[] wheel_list = {"Wheel I", "Wheel II", "Wheel III", "Wheel IV", "Wheel V", "Wheel VI", "Wheel VII", "Wheel VIII"};
		String[] reflector_list = {"Reflector B", "Reflector C"};

		var select1 = new JComboBox(wheel_list);
		var select2 = new JComboBox(wheel_list);
		var select3 = new JComboBox(wheel_list);
		
		var selectr = new JComboBox(reflector_list);

		var press_this = new JButton("Press to encode text!");
		press_this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out_field.setText( enigmaCipher( in_field.getText(), String.valueOf(select1.getSelectedItem()), slide1.getValue(), String.valueOf(select2.getSelectedItem()), slide2.getValue(), String.valueOf(select3.getSelectedItem()), slide3.getValue() ) );
			}
		});

		createLayout(
			plugboard_labels[0], plugboard_labels[1], plugboard_labels[2], plugboard_labels[3], plugboard_labels[4], plugboard_labels[5], plugboard_labels[6], plugboard_labels[7], plugboard_labels[8], plugboard_labels[9], plugboard_labels[10], plugboard_labels[11], plugboard_labels[12], plugboard_labels[13], plugboard_labels[14], plugboard_labels[15], plugboard_labels[16], plugboard_labels[17], plugboard_labels[18], plugboard_labels[19], plugboard_labels[20], plugboard_labels[21], plugboard_labels[21], plugboard_labels[23], plugboard_labels[24], plugboard_labels[25], 
			plugboard_menu[0], plugboard_menu[1], plugboard_menu[2], plugboard_menu[3], plugboard_menu[4], plugboard_menu[5], plugboard_menu[6], plugboard_menu[7], plugboard_menu[8], plugboard_menu[9], plugboard_menu[10], plugboard_menu[11], plugboard_menu[12], plugboard_menu[13], plugboard_menu[14], plugboard_menu[15], plugboard_menu[16], plugboard_menu[17], plugboard_menu[18], plugboard_menu[19], plugboard_menu[20], plugboard_menu[21], plugboard_menu[22], plugboard_menu[23], plugboard_menu[24], plugboard_menu[25], 
			in_field, press_this, out_field, label0, label1, select1, slide1, label2, select2, slide2, label3, select3, slide3, selectr, labelp);
		setTitle("It's cipher tiem");
		setLocationRelativeTo(null); //centre window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //make X button work
	}
	
	private void createLayout(JComponent... arg) {
		var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
			.addComponent(arg[52])
			.addComponent(arg[53])
			.addComponent(arg[54])
			.addComponent(arg[55])
			.addGroup(gl.createSequentialGroup()
				.addComponent(arg[56])
				.addComponent(arg[57])
				.addComponent(arg[58])
			)
			.addGroup(gl.createSequentialGroup()
				.addComponent(arg[59])
				.addComponent(arg[60])
				.addComponent(arg[61])
			)
			.addGroup(gl.createSequentialGroup()
				.addComponent(arg[62])
				.addComponent(arg[63])
				.addComponent(arg[64])
			)
			.addComponent(arg[65])
			.addComponent(arg[66])
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[0])
					.addComponent(arg[26])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[1])
					.addComponent(arg[27])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[2])
					.addComponent(arg[28])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[3])
					.addComponent(arg[29])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[4])
					.addComponent(arg[30])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[5])
					.addComponent(arg[31])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[6])
					.addComponent(arg[32])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[7])
					.addComponent(arg[33])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[8])
					.addComponent(arg[34])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[9])
					.addComponent(arg[35])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[10])
					.addComponent(arg[36])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[11])
					.addComponent(arg[37])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[12])
					.addComponent(arg[38])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[13])
					.addComponent(arg[39])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[14])
					.addComponent(arg[40])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[15])
					.addComponent(arg[41])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[16])
					.addComponent(arg[42])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[17])
					.addComponent(arg[43])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[18])
					.addComponent(arg[44])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[19])
					.addComponent(arg[45])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[20])
					.addComponent(arg[46])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[21])
					.addComponent(arg[47])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[22])
					.addComponent(arg[48])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[23])
					.addComponent(arg[49])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[24])
					.addComponent(arg[50])
				)
			.addGroup(gl.createSequentialGroup()
					.addComponent(arg[25])
					.addComponent(arg[51])
				)
			
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addComponent(arg[52])
			.addComponent(arg[53])
			.addComponent(arg[54])
			.addComponent(arg[55])
			.addGroup(gl.createParallelGroup()
				.addComponent(arg[56])
				.addComponent(arg[57])
				.addComponent(arg[58])
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(arg[59])
				.addComponent(arg[60])
				.addComponent(arg[61])
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(arg[62])
				.addComponent(arg[63])
				.addComponent(arg[64])
			)
			.addComponent(arg[65])
			.addComponent(arg[66])
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[0])
					.addComponent(arg[26])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[1])
					.addComponent(arg[27])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[2])
					.addComponent(arg[28])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[3])
					.addComponent(arg[29])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[4])
					.addComponent(arg[30])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[5])
					.addComponent(arg[31])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[6])
					.addComponent(arg[32])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[7])
					.addComponent(arg[33])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[8])
					.addComponent(arg[34])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[9])
					.addComponent(arg[35])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[10])
					.addComponent(arg[36])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[11])
					.addComponent(arg[37])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[12])
					.addComponent(arg[38])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[13])
					.addComponent(arg[39])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[14])
					.addComponent(arg[40])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[15])
					.addComponent(arg[41])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[16])
					.addComponent(arg[42])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[17])
					.addComponent(arg[43])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[18])
					.addComponent(arg[44])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[19])
					.addComponent(arg[45])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[20])
					.addComponent(arg[46])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[21])
					.addComponent(arg[47])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[22])
					.addComponent(arg[48])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[23])
					.addComponent(arg[49])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[24])
					.addComponent(arg[50])
				)
			.addGroup(gl.createParallelGroup()
					.addComponent(arg[25])
					.addComponent(arg[51])
				)
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
