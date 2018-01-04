package com.github.microprograms.yy_vip_center_manager_api.utils;

import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_ignite.Config;
import com.github.microprograms.micro_oss_ignite.IgniteMicroOssProvider;
import com.github.microprograms.yy_vip_center_manager_api.public_api.AdminUser;

public class Commons {
    public static void init() {
        Config config = new Config();
        config.setDriver(Consts.jdbc_driver);
        config.setUrl(Consts.jdbc_url);
        MicroOss.set(new IgniteMicroOssProvider(config));
    }

    public static AdminUser queryAdminUserByToken(String token) throws MicroOssException {
        return MicroOss.queryObject(AdminUser.class, Condition.build("token=", token));
    }

    public static Operator<AdminUser> buildOperator(Class<?> apiClass, String token) throws MicroOssException {
        AdminUser adminUser = queryAdminUserByToken(token);
        return new Operator<AdminUser>(adminUser.getId(), adminUser.getLoginName(), false, adminUser);
    }
}
