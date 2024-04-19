import java.util.Scanner;

public enum MenuOption {
	HardCoddedSystem     (1,"Start system with hard codded properties!"),
	AddProduct          (2,"Add a product"),
	RemoveProduct        (3,"Remove a product"),
	UpdateProductStock   (4,"Update Stock for a product"),
	AddOrder             (5,"Add an Order"),
	UndoOrder            (6,"Undo the most recent order"),
	ShowProductInfo      (7,"Display product info"),
	ShowInventory        (8,"Display inventroy"),
	PrintOrdersOfProduct (9,"Display order history for a product"),
	Backup               (10,"Backup the entire system"),
	RestoreBackup        (11,"Restore backedup data"),
	Exit        		 (-1,"Exit");
	
	int value;
	String description;
	
	private MenuOption(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	@Override
    public String toString() {
        return String.format("%02d) %s", value, description);
    }

    public static MenuOption getMenuOption(int value) {
        for(MenuOption v : values())
            if(v.getValue() == value) return v;
        throw new IllegalArgumentException("Error! no such option, Try again!");
    }
    
    public static MenuOption getMenuOptionFromUser(Scanner input) {
		while(true) {
			System.out.println("Please select an option:");
			for (MenuOption m : MenuOption.values()) {
				System.out.println(m);
			}
			
			int select = 0;
			MenuOption menuOption = null;
			select = input.nextInt();
			input.nextLine(); // clears buffer
			
			try {
				menuOption = MenuOption.getMenuOption(select);
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
			
			return menuOption;
		}
	}
	
	
}
