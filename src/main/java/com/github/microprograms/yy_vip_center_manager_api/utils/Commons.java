package com.github.microprograms.yy_vip_center_manager_api.utils;

import java.sql.SQLException;

import com.github.microprograms.ignite_utils.IgniteConfig;
import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.ignite_utils.sql.dml.Condition;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.yy_vip_center_manager_api.public_api.AdminUser;

public class Commons {
    public static void initIgnite() {
        IgniteConfig igniteConfig = new IgniteConfig();
        igniteConfig.setJdbcUrl(Consts.jdbc_url);
        IgniteUtils.setIgniteConfig(igniteConfig);
    }

    public static AdminUser queryAdminUserByToken(String token) throws SQLException {
        return IgniteUtils.queryObject(AdminUser.class, Condition.build("token=", token));
    }

    public static Operator<AdminUser> buildOperator(Class<?> apiClass, String token) throws SQLException {
        AdminUser adminUser = queryAdminUserByToken(token);
        return new Operator<AdminUser>(adminUser.getId(), adminUser.getLoginName(), false, adminUser);
    }
}
