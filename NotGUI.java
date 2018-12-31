import java.util.Scanner;

public class NotGUI {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String in = "null";
		
		System.out.println("Welcome to the NotGUI program.\nType something now.");
		in = sc.nextLine();
		System.out.println("Repeat: " + in + "\nVery good. It is nice to meet you.\nNow I ask you to choose: A, or B?");
		in = sc.nextLine();
		while(!in.equalsIgnoreCase("a") & !in.equalsIgnoreCase("b")) {
			System.out.println("I must ask you to choose. A or B?");
			in = sc.nextLine();
		}
		System.out.println("Thank you. Now we begin with path " + in);
	}
}