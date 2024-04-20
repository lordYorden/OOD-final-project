
public interface InventoryBackupable {
	public Inventory loadInventory(String filePath) throws Exception;
	public void saveInventory(String filePath, Inventory inventory)throws Exception;
}
