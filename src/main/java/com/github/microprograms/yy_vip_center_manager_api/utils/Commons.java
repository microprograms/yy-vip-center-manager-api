package com.github.microprograms.yy_vip_center_manager_api.utils;

import com.github.microprograms.ignite_utils.IgniteConfig;
import com.github.microprograms.ignite_utils.IgniteUtils;

public class Commons {
    public static void initIgnite() {
        IgniteConfig igniteConfig = new IgniteConfig();
        igniteConfig.setJdbcUrl(Consts.jdbc_url);
        IgniteUtils.setIgniteConfig(igniteConfig);
    }
}
