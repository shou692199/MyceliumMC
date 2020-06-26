package MyceliumMC.server.command.internal;

import MyceliumMC.server.MyceliumMC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandMyceliumMC extends Command {
    public CommandMyceliumMC(String name) {
        super(name);
        this.description = "MyceliumMC related commands";
        this.usageMessage = "/MyceliumMC reload";
        setPermission("MyceliumMC.command.MyceliumMC");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        if (args[0].equals("reload")) {
            MyceliumMC.getConfig().loadConfig();
            sender.sendMessage(ChatColor.GREEN + "Configuration reload complete.");
        }

        return true;
    }
}
