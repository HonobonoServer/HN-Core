package so.wktk.honobonoserver.hncore;

public class PortableItems /*implements Listener, CommandExecutor*/ {
	/*
	private static Plugin instance = HNCore.getInstance();
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(new File(instance.getDataFolder(), "PortableChest.yml"));

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void openChest(PlayerInteractEvent event) {
		if(event.getPlayer().getItemInHand().getType() != Material.STICK) { return; }
		if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) { return; }
		if (!(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§R§BPocket-Chest"))){
			return;
		}
		List<ItemStack> i = ((List<ItemStack>)config.getList(event.getPlayer().getUniqueId().toString()));
		if(i == null) {
			config.set(event.getPlayer().getUniqueId().toString(),
					Bukkit.createInventory(null, InventoryType.CHEST,
							"§R§R§R§R" + event.getPlayer().getName()).getContents());
			i = (List<ItemStack>) (config.getList(event.getPlayer().getUniqueId().toString()));
		}
		ItemStack[] items = i.toArray(new ItemStack[0]);
		Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, event.getPlayer().getName());
		inv.setContents(items);
		event.getPlayer().openInventory(inv);
	}

	@EventHandler
	public void closeChest(InventoryCloseEvent event) {
		if(event.getInventory().getTitle().equalsIgnoreCase("§R§R§R§R" + event.getPlayer().getName())) {
			config.set(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
		}
	}

	public static FileConfiguration getInvConfig() {
		return config;
	}
	*/
}
