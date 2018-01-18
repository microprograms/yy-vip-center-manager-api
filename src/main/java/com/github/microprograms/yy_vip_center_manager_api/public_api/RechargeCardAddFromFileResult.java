package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_relational_data_model_runtime.MicroRelationalDataModel;
import com.github.microprograms.micro_relational_data_model_runtime.Comment;
import com.github.microprograms.micro_relational_data_model_runtime.Required;

@MicroRelationalDataModel(version = "v0.0.4")
public class RechargeCardAddFromFileResult {

    @Comment(value = "总数量")
    @Required(value = true)
    private Integer totalAmount;

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Comment(value = "成功数量")
    @Required(value = false)
    private Integer successAmount;

    public Integer getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(Integer successAmount) {
        this.successAmount = successAmount;
    }

    @Comment(value = "失败数量")
    @Required(value = false)
    private Integer failAmount;

    public Integer getFailAmount() {
        return failAmount;
    }

    public void setFailAmount(Integer failAmount) {
        this.failAmount = failAmount;
    }

    @Comment(value = "失败列表")
    @Required(value = false)
    private java.util.List<String> failList;

    public java.util.List<String> getFailList() {
        return failList;
    }

    public void setFailList(java.util.List<String> failList) {
        this.failList = failList;
    }
}
