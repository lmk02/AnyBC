package com.lekohd.anybc.commands;

import com.lekohd.anybc.AnyBC;
import com.lekohd.anybc.MessageManager;
import com.lekohd.anybc.SettingsManager;
import com.lekohd.anybc.config.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;

/**
 * Created by Leon on 07.09.2014.
 * Project AnyBC
 * <p/>
 * Copyright (C) 2014 Leon167 { LekoHD
 */
public class Commands implements CommandExecutor {

    public AnyBC plugin;

    public Commands (AnyBC pl)
    {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if(!AnyBC.players.contains(p.getName()))
            {
                p.sendMessage(translateColorCodes(SettingsManager.getInstance().getData().getString("fehlermsg")));
                return true;
            }
            if (args.length == 0 || args[0].equalsIgnoreCase("info")) {
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "Plugin AnyBC");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "Developer: §3Leon167 / LekoHD");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "Commands: §3/anybc help");
                return true;
            }
            if (args[0].equalsIgnoreCase("help")) {
                p.sendMessage(ChatColor.GREEN + "----" + " §6AnyBC " + ChatColor.AQUA + "Commands " + ChatColor.GREEN + "----");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "§3/anybc info  §8Bekomme ein paar Infos");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "§3/anybc config <prefixnummerInConfig> <ZahlDerMsgInConfig>  §8Nachricht via Config senden");
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "§3/anybc msg <EinPrefix> <EineMessage>  §8Nachricht senden");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                Configuration.reload();
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO, "Reloaded configs!");
                return true;
            }
            if(args.length > 2) {
                if (args[0].equalsIgnoreCase("config")) {
                    String prefix = null;
                    String msg = null;
                    if(AnyBC.enabledPrefix.contains(args[1])) {
                        prefix = SettingsManager.getInstance().getConfig().getString("prefix." + args[1]);
                        prefix = translateColorCodes(prefix);
                    }
                    else
                    {
                        MessageManager.getInstance().msg(sender, MessageManager.MessageType.BAD, "Dieser prefix ist nicht enabled oder existiert nicht!");
                        return true;
                    }
                    if(AnyBC.enabledMessages.contains(args[2])) {
                        msg = SettingsManager.getInstance().getConfig().getString("messages." + args[2]);
                        msg = translateColorCodes(msg);
                    }
                    else
                    {
                        MessageManager.getInstance().msg(sender, MessageManager.MessageType.BAD, "Diese message ist nicht enabled oder existiert nicht!");
                        return true;
                    }
                    for(Player players : Bukkit.getOnlinePlayers())
                        players.sendMessage(prefix + " " + msg);
                    //Bukkit.getServer().broadcastMessage(prefix + " " + msg);
                }
                if (args[0].equalsIgnoreCase("msg")) {
                    String prefix = translateColorCodes(args[1]);
                    String msg = "";
                    for(int i = 2 ; i < args.length; i++)
                    {
                        msg = msg + " " + args[i];
                    }
                    msg = translateColorCodes(msg);
                    for(Player players : Bukkit.getOnlinePlayers())
                        players.sendMessage(prefix + msg);
                    //Bukkit.getServer().broadcastMessage(prefix + msg);
                }
            }
            else
            {
                MessageManager.getInstance().msg(sender, MessageManager.MessageType.BAD, "Zu wenig Argumente!");
            }
        }
        return true;
    }

    public String translateColorCodes(String msg)
    {
        String message = "";
        for(int i = 0; i < msg.length(); i++)
        {
            if(msg.charAt(i) == '&')
            {
                message = message + "§";
            }
            else
            {
                message = message + msg.charAt(i);
            }
        }
        return message;
    }

}
