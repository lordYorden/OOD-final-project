import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinaryFileBackup implements InventoryBackupable {
	
	@Override
	public Inventory loadInventory(String filePath) throws Exception {
		ObjectInputStream toLoad = new ObjectInputStream(new FileInputStream(filePath));
		Inventory inventory = (Inventory) toLoad.readObject();
		toLoad.close();
		return inventory;
	}

	@Override
	public void saveInventory(String filePath, Inventory inventory) throws Exception {
		ObjectOutputStream toSave = new ObjectOutputStream(new FileOutputStream(filePath));
		toSave.writeObject(inventory);
		toSave.close();
	}

}
