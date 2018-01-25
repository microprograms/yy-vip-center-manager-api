package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.Transaction;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_core.model.dml.InsertCommand;
import com.github.microprograms.yy_vip_center_manager_api.public_api.AdminUser;
import com.github.microprograms.yy_vip_center_manager_api.public_api.ErrorCodeEnum;
import com.github.microprograms.yy_vip_center_manager_api.public_api.Goods;
import com.github.microprograms.yy_vip_center_manager_api.public_api.GoodsCategory;
import com.github.microprograms.yy_vip_center_manager_api.public_api.RechargeCard;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

public class SampleData {

    public static void main(String[] args) throws Exception {
        Fn.initOss();
        init();
    }

    public static void init() throws Exception {
        addAdminUsers();
        addGoodsCategories();
        addGoods();
        addRechargeCards();
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

    private static void addGoodsCategories() throws Exception {
        try (Transaction transaction = MicroOss.beginTransaction()) {
            transaction.insertObject(new InsertCommand(MicroOss.buildEntity(buildGoodsCategory(1, "分类一", "分类一的描述信息。"))));
            transaction.insertObject(new InsertCommand(MicroOss.buildEntity(buildGoodsCategory(2, "分类二", "描述信息，描述信息，描述信息，描述信息"))));
            transaction.insertObject(new InsertCommand(MicroOss.buildEntity(buildGoodsCategory(3, "分类三", "这是分类三的描述信息。"))));
            transaction.commit();
        }
    }

    private static GoodsCategory queryGoodsCategoryByName(String name) throws Exception {
        return MicroOss.queryObject(GoodsCategory.class, Condition.build("name=", name));
    }

    private static GoodsCategory buildGoodsCategory(int reorder, String name, String desc) throws Exception {
        GoodsCategory category = new GoodsCategory();
        category.setId(UUID.randomUUID().toString());
        category.setName(name);
        category.setDesc(desc);
        category.setReorder(reorder);
        category.setCreaterId("");
        category.setCreaterLoginName("");
        category.setDtCreate(System.currentTimeMillis());
        return category;
    }

    private static void addGoods() throws Exception {
        GoodsCategory category1 = queryGoodsCategoryByName("分类一");
        try (Transaction transaction = MicroOss.beginTransaction()) {
            for (int i = 1; i < 50; i++) {
                List<String> pictureUrls = Arrays.asList("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516641377045&di=dfd29501767acaf9e4ab7d372b34081d&imgtype=0&src=http%3A%2F%2Fpic31.photophoto.cn%2F20140608%2F0021033853003391_b.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516797152293&di=f117a064690b1929cde9d80059fbe8e5&imgtype=0&src=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20140701%2Fsy_64140121774.jpg");
                Goods goods = buildGoods(category1, i, "测试商品" + i, "测试商品的描述信息" + i, pictureUrls);
                transaction.insertObject(new InsertCommand(MicroOss.buildEntity(goods)));
            }
            transaction.commit();
        }
        GoodsCategory category2 = queryGoodsCategoryByName("分类二");
        try (Transaction transaction = MicroOss.beginTransaction()) {
            for (int i = 1; i < 50; i++) {
                List<String> pictureUrls = Arrays.asList("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516641377045&di=dfd29501767acaf9e4ab7d372b34081d&imgtype=0&src=http%3A%2F%2Fpic31.photophoto.cn%2F20140608%2F0021033853003391_b.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516797152293&di=f117a064690b1929cde9d80059fbe8e5&imgtype=0&src=http%3A%2F%2Fimg05.tooopen.com%2Fimages%2F20140701%2Fsy_64140121774.jpg");
                Goods goods = buildGoods(category2, i, "第二套测试商品" + i, "测试商品的描述信息" + i, pictureUrls);
                transaction.insertObject(new InsertCommand(MicroOss.buildEntity(goods)));
            }
            transaction.commit();
        }
    }

    private static Goods buildGoods(GoodsCategory category, int reorder, String name, String desc, List<String> pictureUrls) throws Exception {
        Goods goods = new Goods();
        goods.setId(UUID.randomUUID().toString());
        goods.setName(name);
        goods.setDesc(desc);
        goods.setReorder(reorder);
        goods.setCategoryId(category.getId());
        goods.setCategoryName(category.getName());
        goods.setPictureUrls(JSON.toJSONString(pictureUrls));
        goods.setPrice(99800);
        goods.setPriceLevel1(89800);
        goods.setPriceLevel2(79800);
        goods.setPriceLevel3(69800);
        goods.setStock(10000);
        goods.setCreaterId("");
        goods.setCreaterLoginName("");
        goods.setDtCreate(System.currentTimeMillis());
        return goods;
    }

    private static void addRechargeCards() throws Exception {
        List<Integer> amounts = Arrays.asList(50, 100, 200, 500, 1000);
        try (Transaction transaction = MicroOss.beginTransaction()) {
            for (int i = 1; i < 50; i++) {
                transaction.insertObject(new InsertCommand(MicroOss.buildEntity(buildRechargeCard(UUID.randomUUID().toString() + "@" + amounts.get(new Random().nextInt(amounts.size()))))));
            }
            transaction.commit();
        }
    }

    private static RechargeCard buildRechargeCard(String rawPasswordSeriesCode) throws Exception {
        RechargeCard rechargeCard = new RechargeCard();
        int lastIndexOfSeparator = rawPasswordSeriesCode.lastIndexOf("@");
        if (lastIndexOfSeparator == -1) {
            throw new MicroApiPassthroughException(ErrorCodeEnum.invalid_recharge_card);
        }
        String amountString = rawPasswordSeriesCode.substring(lastIndexOfSeparator + 1);
        rechargeCard.setId(rawPasswordSeriesCode);
        rechargeCard.setRawPasswordSeriesCode(rawPasswordSeriesCode);
        rechargeCard.setAmount(Integer.valueOf(amountString));
        AdminUser adminUser = Fn.queryAdminUserByLoginName("admin");
        rechargeCard.setCreaterId(adminUser.getId());
        rechargeCard.setCreaterLoginName(adminUser.getLoginName());
        rechargeCard.setDtCreate(System.currentTimeMillis());
        return rechargeCard;
    }
}
