package com.github.microprograms.yy_vip_center_manager_api.utils;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class IgnitionUtils {
    private static final Ignite ignite;

    static {
        Ignition.setClientMode(true);
        ignite = Ignition.start("ignite-config.xml");
    }

    public static Ignite getIgnite() {
        return ignite;
    }
}
