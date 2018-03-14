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
import org.bukkit.plugin.Plugin;

/**
* dev.bukkit.org @ http://dev.bukkit.org/bukkit-plugins/newcommands/
* github @ https://github.com/ghacproductions/NewCommands
*
* @author ghac
*/
public class MyCommand extends AbstractCommand {
     Plugin t;
    public MyCommand(String command, String usage, String description, Plugin p) {
    super(command, usage, description);
    t = p;
    }
     
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        String command = cmd.getName().split(" ")[0];
        command = command.toLowerCase();
        command = command.replace("/", "");
            if(t.getConfig().getString("commands." + command) != null){ 
               Player p = (Player) sender;
               
               if(!p.isOp()){
               if(!p.hasPermission("*")){
                   if(p.hasPermission("newcommands.command." + command + ".deny")){
                       p.sendMessage(ChatColor.DARK_RED + "You don't have permission");
                       return true;
                   }
               }
               }
               
               String cmdanswer = t.getConfig().getString("commands." + command);
               cmdanswer = cmdanswer.replace("&", "�");
               cmdanswer = cmdanswer.replace("%MOTD%", t.getServer().getMotd()); // Variable for MOTD
               cmdanswer = cmdanswer.replace("%OP%", t.getServer().getOnlinePlayers().size()+""); // Variable for OnlinePlayers
               cmdanswer = cmdanswer.replace("%MAXP%", t.getServer().getMaxPlayers()+""); // Variable for MaxPlayers
               cmdanswer = cmdanswer.replace("%IP%", t.getServer().getIp()); // Variable for ServerIP
               cmdanswer = cmdanswer.replace("%VER%", t.getServer().getBukkitVersion().split("-")[0]); // Variable for Version
               cmdanswer = cmdanswer.replace("%SERN%", t.getServer().getServerName()); // Variable for Servername
               cmdanswer = cmdanswer.replace("%PLAN%", p.getName()); // Variable for Playername


               String[] answer = cmdanswer.split("%NL%", -1); // Split in lines

               for(int i = 0; i < answer.length; i++){
               p.sendMessage(answer[i]);
               }
               
            }
        return false;
    //do command stuff

    }

    }