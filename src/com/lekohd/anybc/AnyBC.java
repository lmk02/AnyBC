package com.lekohd.anybc;

import com.lekohd.anybc.commands.Commands;
import com.lekohd.anybc.config.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Leon on 07.09.2014.
 * Project AnyBC
 * <p/>
 * Copyright (C) 2014 Leon167 { LekoHD
 */
public class AnyBC extends JavaPlugin{

    public static AnyBC instance;
    public static ArrayList<String> players = new ArrayList<String>();
    public static ArrayList<String> enabledPrefix = new ArrayList<String>();
    public static ArrayList<String> enabledMessages = new ArrayList<String>();

    public void onEnable()
    {
        instance = this;
        MessageManager.getInstance().log("Plugin by " + this.getDescription().getAuthors());
        SettingsManager.getInstance().setup(instance);
        PluginManager pm = this.getServer().getPluginManager();
        this.getCommand("anybc").setExecutor(new Commands(this));
        Configuration.setupData();
        Configuration.reload();
        /*System.out.println(enabledMessages);
        System.out.println(enabledPrefix);
        System.out.println(players);*/
    }

    public void onDisable()
    {
        MessageManager.getInstance().log("Plugin disabled!");
    }

    public static AnyBC getInstance()
    {
        return instance;
    }

}
