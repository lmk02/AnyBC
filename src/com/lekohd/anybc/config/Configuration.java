package com.lekohd.anybc.config;

import com.lekohd.anybc.AnyBC;
import com.lekohd.anybc.SettingsManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

/**
 * Created by Leon on 07.09.2014.
 * Project AnyBC
 * <p/>
 * Copyright (C) 2014 Leon167 { LekoHD
 */
public class Configuration {

    public static void setupData()
    {
        FileConfiguration data = SettingsManager.getInstance().getData();
        if(data.getList("spieler") == null)
        {
            AnyBC.players.add("Player5466");
            data.set("spieler", AnyBC.players);
        }
        if(data.getString("fehlermsg") == null)
        {
            data.set("fehlermsg", "Du darfst das nicht!");
        }
        SettingsManager.getInstance().saveData();
    }

    @SuppressWarnings("unchecked")
    public static void loadData()
    {
        FileConfiguration data = SettingsManager.getInstance().getData();
        AnyBC.players = (ArrayList<String>) data.get("spieler");
    }

    @SuppressWarnings("unchecked")
    public static void loadConfig()
    {
        FileConfiguration config = SettingsManager.getInstance().getConfig();
        AnyBC.enabledMessages = (ArrayList<String>) config.get("enabledMessages");
        AnyBC.enabledPrefix = (ArrayList<String>) config.get("enabledPrefix");
    }

    public static void reload()
    {
        AnyBC.enabledMessages.clear();
        AnyBC.enabledPrefix.clear();
        SettingsManager.getInstance().reloadConfig();
        SettingsManager.getInstance().reloadData();
        loadConfig();
        loadData();
    }

}
