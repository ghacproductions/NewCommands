/*
* The MIT License
*
* Copyright 2014 ghac.
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package de.ghac.newcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
* dev.bukkit.org @ http://dev.bukkit.org/bukkit-plugins/newcommands/
* github @ https://github.com/ghacproductions/NewCommands
*
* @author ghac
*/
public class NewCommands extends JavaPlugin implements Listener{

    @Override
    public void onDisable() {

        System.out.println("[" + this.getName()+"] Plugin deaktiviert!");

    }
    
    @Override
    public void onEnable() {
        System.out.println("[" + this.getName()+"] Plugin by ghac!");
        System.out.println("[" + this.getName()+"] Plugin aktiviert!");
        getConfig().addDefault("commands.newtestcommand", "&4Awesome Plugin");
        getConfig().options().copyDefaults(true);
        this.saveConfig();
        
        registerCommand();
}
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
    Player p = (Player) sender;
    if(args.length == 1){
        if(args[0].equalsIgnoreCase("reload")){
            if(p.hasPermission("newcommands.reload")){
            registerCommand();
            reloadConfig();
            p.sendMessage(ChatColor.GOLD + "[" + this.getName()+"]" + ChatColor.DARK_GREEN + " Reloaded.");
            return true;
            }else{
                p.sendMessage(ChatColor.DARK_RED + "You don't have permission");
                return true;
            }
        }else if(args[0].equalsIgnoreCase("list")){
            if(p.hasPermission("newcommands.list")){
                sendList(p);
            }
        }
    }
    p.sendMessage(ChatColor.GOLD + "[" + this.getName()+"]" + ChatColor.DARK_GREEN + " NewCommands Plugin by ghac. 2014.");
    return true;
    }
    
    

    
  public void registerCommand(){
      for (String command : getConfig().getConfigurationSection("commands").getKeys(false)) {           
          AbstractCommand myCommand = new MyCommand(command, "/<command>", "Command by NewCommands", this);
          myCommand.register();
         }     
  }
  
  public void sendList(Player p){
      p.sendMessage(ChatColor.GOLD + "[" + this.getName()+"]" +  ChatColor.DARK_GREEN + " List of all NewCommands:");
      for (String command : getConfig().getConfigurationSection("commands").getKeys(false)) {
          p.sendMessage(ChatColor.GOLD + "/" + command + ": " + ChatColor.AQUA+ getConfig().getString("commands."+command));
      }
  }
  
}
