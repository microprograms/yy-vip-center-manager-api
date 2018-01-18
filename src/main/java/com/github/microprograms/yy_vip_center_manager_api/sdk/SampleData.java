package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.util.UUID;

import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.yy_vip_center_manager_api.public_api.AdminUser;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

public class SampleData {

    public static void main(String[] args) throws Exception {
        Fn.initOss();
        addAdminUsers();
    }

    private static void addAdminUsers() throws Exception {
        AdminUser admin = new AdminUser();
        admin.setId(UUID.randomUUID().toString());
        admin.setCreaterId("");
        admin.setCreaterLoginName("");
        admin.setDtCreate(System.currentTimeMillis());
        admin.setLoginName("admin");
        admin.setLoginPassword("pass");
        admin.setToken(UUID.randomUUID().toString());
        MicroOss.insertObject(admin);
    }
}
