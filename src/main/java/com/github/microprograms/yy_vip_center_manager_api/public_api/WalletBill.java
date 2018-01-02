package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.MicroEntityAnnotation;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@MicroEntityAnnotation()
public class WalletBill {

    @Comment(value = "ID")
    @Required(value = true)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Comment(value = "用户ID")
    @Required(value = true)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Comment(value = "用户登录名")
    @Required(value = true)
    private String userLoginName;

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Comment(value = "类型(1入账,2消费)")
    @Required(value = true)
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Comment(value = "创建时间")
    @Required(value = true)
    private Long dtCreate;

    public Long getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Comment(value = "金额(元)")
    @Required(value = true)
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Comment(value = "钱包 - 旧余额(元)")
    @Required(value = true)
    private Integer oldWalletAmount;

    public Integer getOldWalletAmount() {
        return oldWalletAmount;
    }

    public void setOldWalletAmount(Integer oldWalletAmount) {
        this.oldWalletAmount = oldWalletAmount;
    }

    @Comment(value = "钱包 - 新余额(元)")
    @Required(value = true)
    private Integer newWalletAmount;

    public Integer getNewWalletAmount() {
        return newWalletAmount;
    }

    public void setNewWalletAmount(Integer newWalletAmount) {
        this.newWalletAmount = newWalletAmount;
    }

    @Comment(value = "入账 - 充值卡ID")
    @Required(value = true)
    private String inRechargeCardId;

    public String getInRechargeCardId() {
        return inRechargeCardId;
    }

    public void setInRechargeCardId(String inRechargeCardId) {
        this.inRechargeCardId = inRechargeCardId;
    }

    @Comment(value = "入账 - 充值卡面额")
    @Required(value = true)
    private String inRechargeCardAmount;

    public String getInRechargeCardAmount() {
        return inRechargeCardAmount;
    }

    public void setInRechargeCardAmount(String inRechargeCardAmount) {
        this.inRechargeCardAmount = inRechargeCardAmount;
    }

    @Comment(value = "入账 - 充值卡原始密码序列编码串")
    @Required(value = true)
    private String inRechargeCardRawPasswordSeriesCode;

    public String getInRechargeCardRawPasswordSeriesCode() {
        return inRechargeCardRawPasswordSeriesCode;
    }

    public void setInRechargeCardRawPasswordSeriesCode(String inRechargeCardRawPasswordSeriesCode) {
        this.inRechargeCardRawPasswordSeriesCode = inRechargeCardRawPasswordSeriesCode;
    }

    @Comment(value = "消费 - 订单ID")
    @Required(value = true)
    private String outOrderId;

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    @Comment(value = "消费 - 订单商品ID")
    @Required(value = true)
    private String outOrderGoodsId;

    public String getOutOrderGoodsId() {
        return outOrderGoodsId;
    }

    public void setOutOrderGoodsId(String outOrderGoodsId) {
        this.outOrderGoodsId = outOrderGoodsId;
    }

    @Comment(value = "消费 - 订单商品名字")
    @Required(value = true)
    private String outOrderGoodsName;

    public String getOutOrderGoodsName() {
        return outOrderGoodsName;
    }

    public void setOutOrderGoodsName(String outOrderGoodsName) {
        this.outOrderGoodsName = outOrderGoodsName;
    }

    @Comment(value = "上次修改时间")
    @Required(value = true)
    private Long dtLastModify;

    public Long getDtLastModify() {
        return dtLastModify;
    }

    public void setDtLastModify(Long dtLastModify) {
        this.dtLastModify = dtLastModify;
    }
}
