import java.util.Scanner;

public class Menu {
	
	public static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		MenuOption menuOption;
		do {
			menuOption = MenuOption.getMenuOptionFromUser(input);
			switch (menuOption) {
			case Exit:
				System.out.println("Bye Bye!");
				break;
			default:
				break;
			}
			
		}while(menuOption != MenuOption.Exit);
	}
}
