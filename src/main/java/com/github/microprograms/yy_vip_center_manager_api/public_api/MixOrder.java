package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.MicroEntityAnnotation;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@MicroEntityAnnotation()
public class MixOrder {

    @Comment(value = "订单号")
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

    @Comment(value = "订单总金额(元)")
    @Required(value = false)
    private Integer orderAmount;

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Comment(value = "商品 - ID")
    @Required(value = false)
    private String goodsId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Comment(value = "商品 - 名称")
    @Required(value = true)
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Comment(value = "商品 - 明细(JsonObject)")
    @Required(value = false)
    private String goodsDetail;

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    @Comment(value = "订单备注(JsonObject)")
    @Required(value = false)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Comment(value = "提交时间")
    @Required(value = true)
    private Long dtCreate;

    public Long getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Comment(value = "是否已处理(0否1是)")
    @Required(value = true)
    private Integer isDispose;

    public Integer getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(Integer isDispose) {
        this.isDispose = isDispose;
    }

    @Comment(value = "处理时间")
    @Required(value = true)
    private Long dtDispose;

    public Long getDtDispose() {
        return dtDispose;
    }

    public void setDtDispose(Long dtDispose) {
        this.dtDispose = dtDispose;
    }

    @Comment(value = "处理人ID")
    @Required(value = true)
    private String disposerId;

    public String getDisposerId() {
        return disposerId;
    }

    public void setDisposerId(String disposerId) {
        this.disposerId = disposerId;
    }

    @Comment(value = "处理人登录名")
    @Required(value = true)
    private String disposerLoginName;

    public String getDisposerLoginName() {
        return disposerLoginName;
    }

    public void setDisposerLoginName(String disposerLoginName) {
        this.disposerLoginName = disposerLoginName;
    }

    @Comment(value = "退货申请 - 备注")
    @Required(value = false)
    private String refundRequestComment;

    public String getRefundRequestComment() {
        return refundRequestComment;
    }

    public void setRefundRequestComment(String refundRequestComment) {
        this.refundRequestComment = refundRequestComment;
    }

    @Comment(value = "退货申请 - 申请时间")
    @Required(value = true)
    private Long dtRefundRequest;

    public Long getDtRefundRequest() {
        return dtRefundRequest;
    }

    public void setDtRefundRequest(Long dtRefundRequest) {
        this.dtRefundRequest = dtRefundRequest;
    }

    @Comment(value = "退货申请 - 审核时间")
    @Required(value = true)
    private Long dtAuditRefundRequest;

    public Long getDtAuditRefundRequest() {
        return dtAuditRefundRequest;
    }

    public void setDtAuditRefundRequest(Long dtAuditRefundRequest) {
        this.dtAuditRefundRequest = dtAuditRefundRequest;
    }

    @Comment(value = "退货申请 - 审核人ID")
    @Required(value = true)
    private String refundRequestAuditorId;

    public String getRefundRequestAuditorId() {
        return refundRequestAuditorId;
    }

    public void setRefundRequestAuditorId(String refundRequestAuditorId) {
        this.refundRequestAuditorId = refundRequestAuditorId;
    }

    @Comment(value = "退货申请 - 审核人登录名")
    @Required(value = true)
    private String refundRequestAuditorLoginName;

    public String getRefundRequestAuditorLoginName() {
        return refundRequestAuditorLoginName;
    }

    public void setRefundRequestAuditorLoginName(String refundRequestAuditorLoginName) {
        this.refundRequestAuditorLoginName = refundRequestAuditorLoginName;
    }

    @Comment(value = "退货申请 - 状态(0未审核1已同意2已拒绝)")
    @Required(value = true)
    private Integer refundRequestStatus;

    public Integer getRefundRequestStatus() {
        return refundRequestStatus;
    }

    public void setRefundRequestStatus(Integer refundRequestStatus) {
        this.refundRequestStatus = refundRequestStatus;
    }

    @Comment(value = "退货申请 - 拒绝原因")
    @Required(value = true)
    private String refundRequestRejectReason;

    public String getRefundRequestRejectReason() {
        return refundRequestRejectReason;
    }

    public void setRefundRequestRejectReason(String refundRequestRejectReason) {
        this.refundRequestRejectReason = refundRequestRejectReason;
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