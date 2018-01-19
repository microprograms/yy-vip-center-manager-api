package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import com.github.microprograms.yy_vip_center_manager_api.public_api.Goods;

public class PrintInsertObjectStatement {
    public static void main(String[] args) {
        print(Goods.class);
    }

    public static void print(Class<?> clz) {
        for (Field x : clz.getDeclaredFields()) {
            String capitalize = StringUtils.capitalize(x.getName());
            System.out.println(String.format("obj.set%s(req.get%s());", capitalize, capitalize));
        }
    }
}
