package de.ghac.newcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NewCommands extends JavaPlugin implements Listener{

    @Override
    public void onDisable() {

        System.out.println("[" + this.getName()+"] Plugin deaktiviert!");

    }
    
    @Override
    public void onEnable() {
        System.out.println("[" + this.getName()+"] Plugin by ghac!");
        System.out.println("[" + this.getName()+"] Plugin aktiviert!");
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().addDefault("commands.newtestcommand", "&4Awesome Plugin");
        getConfig().options().copyDefaults(true);
        this.saveConfig();
}
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
    Player p = (Player) sender;
    p.sendMessage(ChatColor.GOLD + "[" + this.getName()+"]" + ChatColor.DARK_GREEN + " NewCommands Plugin by ghac. 2014.");
    return true;  
    }
    
    
    @EventHandler(priority = EventPriority.HIGH)
  public void onPlayerSendCommand(PlayerCommandPreprocessEvent e){

     String cmd = e.getMessage();
     String command = cmd.split(" ")[0];
     command = command.replace("/", "");

     
         if(getConfig().getString("commands." + command) != null){ 
            String cmdanswer = getConfig().getString("commands." + command);
            cmdanswer = cmdanswer.replace("&", "§");
            cmdanswer = cmdanswer.replace("%MOTD%", getServer().getMotd()); // Variable for MOTD
            cmdanswer = cmdanswer.replace("%OP%", getServer().getOnlinePlayers().length+""); // Variable for OnlinePlayers
            cmdanswer = cmdanswer.replace("%MAXP%", getServer().getMaxPlayers()+""); // Variable for MaxPlayers
            cmdanswer = cmdanswer.replace("%IP%", getServer().getIp()); // Variable for ServerIP
            cmdanswer = cmdanswer.replace("%VER%", getServer().getBukkitVersion().split("-")[0]); // Variable for Version
            cmdanswer = cmdanswer.replace("%SERN%", getServer().getServerName()); // Variable for Servername


            String[] answer = cmdanswer.split("%NL%", -1); // Split in lines

            for(int i = 0; i < answer.length; i++){
            e.getPlayer().sendMessage(answer[i]);
            }
            e.setCancelled(true);
             
             
         }

  }
}
