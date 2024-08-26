package org.hinoob.security.util;

import org.hinoob.security.SecurityPlugin;

// bit ot static abuse
public class Configuration {

    public static boolean KICK = false;

    public static void load(SecurityPlugin plugin) {
        KICK = plugin.getConfig().getBoolean("kick");
    }
}
