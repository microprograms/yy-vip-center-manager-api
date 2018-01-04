package com.github.microprograms.yy_vip_center_manager_api.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Consts {
    public static String jdbc_driver;
    public static String jdbc_url;

    static {
        Config conf = ConfigFactory.load();
        jdbc_driver = conf.getString("jdbc_driver");
        jdbc_url = conf.getString("jdbc_url");
    }
}
