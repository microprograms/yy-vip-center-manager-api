package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;

import com.github.microprograms.micro_api_runtime.model.Request;

public class InsertObject {
    public static void main(String[] args) throws Exception {
    }

    private static void gen(Class<?> target, Class<? extends Request> source) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String targetClassName = target.getSimpleName();
        System.out.println(String.format("%s obj = new %s();", targetClassName, targetClassName));
        for (Field x : target.getDeclaredFields()) {
            String fieldName = x.getName();
            if (fieldName.equals("id")) {
                System.out.println(String.format("obj.set%s(UUID.randomUUID().toString());", StringUtils.capitalize(fieldName)));
                continue;
            }
            if (fieldName.equals("dtCreate")) {
                System.out.println(String.format("obj.set%s(System.currentTimeMillis());", StringUtils.capitalize(fieldName)));
                continue;
            }
            Class<?> fieldType = x.getType();
            String sourceGetterName = "get" + StringUtils.capitalize(fieldName);
            try {
                source.getMethod(sourceGetterName);
                System.out.println(String.format("obj.set%s(req.%s());", StringUtils.capitalize(fieldName), sourceGetterName));
            } catch (Exception e) {
                if (fieldType == String.class) {
                    System.out.println(String.format("obj.set%s(\"\");", StringUtils.capitalize(fieldName)));
                } else if (fieldType == Integer.class) {
                    System.out.println(String.format("obj.set%s(0);", StringUtils.capitalize(fieldName)));
                } else if (fieldType == Long.class) {
                    System.out.println(String.format("obj.set%s(0L);", StringUtils.capitalize(fieldName)));
                }
            }
        }
        System.out.println("Commons.insertObject(obj);");
    }
}
