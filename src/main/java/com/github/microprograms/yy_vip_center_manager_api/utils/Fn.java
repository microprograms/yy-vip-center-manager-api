package com.github.microprograms.yy_vip_center_manager_api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_ignite.Config;
import com.github.microprograms.micro_oss_ignite.IgniteMicroOssProvider;
import com.github.microprograms.yy_vip_center_manager_api.public_api.AdminUser;
import com.typesafe.config.ConfigFactory;

public class Fn {
    private static final Logger log = LoggerFactory.getLogger(Fn.class);

    public static AdminUser queryAdminUserByToken(String token) throws MicroOssException {
        return MicroOss.queryObject(AdminUser.class, Condition.build("token=", token));
    }

    public static Operator<AdminUser> buildOperator(Class<?> apiClass, String token) throws MicroOssException {
        AdminUser adminUser = queryAdminUserByToken(token);
        return new Operator<AdminUser>(adminUser.getId(), adminUser.getLoginName(), false, adminUser);
    }

    public static void initOss() {
        com.typesafe.config.Config applicationConfig = getApplicationConfig();
        Config config = new Config();
        config.setDriver(applicationConfig.getString("jdbc_driver"));
        config.setUrl(applicationConfig.getString("jdbc_url"));
        log.info("initOss> {}", JSON.toJSONString(config));
        MicroOss.set(new IgniteMicroOssProvider(config));
    }

    public static com.typesafe.config.Config getApplicationConfig() {
        return ConfigFactory.load();
    }
}
