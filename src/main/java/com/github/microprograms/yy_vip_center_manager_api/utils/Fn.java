package com.github.microprograms.yy_vip_center_manager_api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.micro_oss_core.model.Field;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_core.model.dml.Where;
import com.github.microprograms.micro_oss_mysql.Config;
import com.github.microprograms.micro_oss_mysql.MysqlMicroOssProvider;
import com.github.microprograms.yy_vip_center_manager_api.public_api.AdminUser;
import com.github.microprograms.yy_vip_center_manager_api.public_api.Goods;
import com.github.microprograms.yy_vip_center_manager_api.public_api.GoodsBuyLimit;
import com.github.microprograms.yy_vip_center_manager_api.public_api.GoodsCategory;
import com.github.microprograms.yy_vip_center_manager_api.public_api.MixOrder;
import com.github.microprograms.yy_vip_center_manager_api.public_api.User;
import com.typesafe.config.ConfigFactory;

public class Fn {
    private static final Logger log = LoggerFactory.getLogger(Fn.class);

    public static GoodsBuyLimit queryGoodsBuyLimitByGoodsIdAndUserNickname(String goodsId, String userNickname) throws MicroOssException {
        return MicroOss.queryObject(GoodsBuyLimit.class, Where.and(Condition.build("goodsId=", goodsId), Condition.build("userNickname=", userNickname)));
    }

    public static User queryUserById(String userId) throws MicroOssException {
        return MicroOss.queryObject(User.class, Condition.build("id=", userId));
    }

    public static User queryUserByNickname(String userNickname) throws MicroOssException {
        return MicroOss.queryObject(User.class, Condition.build("nickname=", userNickname));
    }

    public static MixOrder queryMixOrderById(String mixOrderId) throws MicroOssException {
        return MicroOss.queryObject(MixOrder.class, Condition.build("id=", mixOrderId));
    }

    public static Goods queryGoodsById(String goodsId) throws MicroOssException {
        return MicroOss.queryObject(Goods.class, Condition.build("id=", goodsId));
    }

    public static GoodsCategory queryGoodsCategoryById(String goodsCategoryId) throws MicroOssException {
        return MicroOss.queryObject(GoodsCategory.class, Condition.build("id=", goodsCategoryId));
    }

    public static AdminUser queryAdminUserByToken(String token) throws MicroOssException {
        return MicroOss.queryObject(AdminUser.class, Condition.build("token=", token));
    }

    public static AdminUser queryAdminUserByLoginName(String loginName) throws MicroOssException {
        return MicroOss.queryObject(AdminUser.class, Condition.build("loginName=", loginName));
    }

    public static Operator<AdminUser> buildOperator(Class<?> apiClass, String token) throws MicroOssException {
        AdminUser adminUser = queryAdminUserByToken(token);
        if (adminUser == null) {
            return null;
        }
        return new Operator<AdminUser>(adminUser.getId(), adminUser.getLoginName(), false, adminUser);
    }

    public static void initOss() {
        com.typesafe.config.Config applicationConfig = getApplicationConfig();
        Config config = new Config();
        config.setDriver(applicationConfig.getString("jdbc_driver"));
        config.setUrl(applicationConfig.getString("jdbc_url"));
        config.setUser(applicationConfig.getString("jdbc_user"));
        config.setPassword(applicationConfig.getString("jdbc_password"));
        log.info("initOss> {}", JSON.toJSONString(config));
        MicroOss.set(new MysqlMicroOssProvider(config));
    }

    public static com.typesafe.config.Config getApplicationConfig() {
        return ConfigFactory.load();
    }

    public static List<Field> buildFieldsIgnoreBlank(Collection<Field> fields) {
        List<Field> list = new ArrayList<>();
        for (Field field : fields) {
            String name = field.getName();
            Object value = field.getValue();
            if (StringUtils.isBlank(name) || value == null || StringUtils.isBlank(value.toString())) {
                continue;
            }
            list.add(field);
        }
        return list;
    }
}
