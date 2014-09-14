package com.lekohd.anybc;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/*
 * Copyright (C) 2014 Leon167 and XxChxppellxX 
 */

public class MessageManager {
		public enum MessageType {
			INFO(ChatColor.GRAY,""),
			ERROR(ChatColor.RED,"§cError: "),
			BAD(ChatColor.RED,"");
			
			private ChatColor color;
			private String prefix;
			
			MessageType(ChatColor color,String prefix) {this.color = color; this.prefix = prefix;}
			public ChatColor getColor() {return color;}
			public String getPrefix() {return prefix;}
	}
		
	private MessageManager() {}
	
	private static MessageManager instance = new MessageManager();
	
	private String messagePrefix = ChatColor.DARK_AQUA + "[AnyBC]" + ChatColor.RESET;
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	public void log(String message){
		System.out.println(messagePrefix + " " + message);
	}
	
	public void msg(CommandSender sender, MessageType type, String message){
			sender.sendMessage(messagePrefix + type.getPrefix() + type.getColor() + message);
	}
	
	public void broadcast(MessageType type, String message){
		AnyBC.getInstance().getServer().broadcastMessage(messagePrefix + type.getPrefix() + type.getColor() + message);
	}
	
	
}
