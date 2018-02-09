package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.model.Field;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "商品订单 - 同意退货", type = "read", version = "v0.0.15")
public class MixOrder_AgreeRefundRequest_Api {

    private static void core(Req req, Response resp) throws Exception {
        AdminUser adminUser = Fn.queryAdminUserByToken(req.getToken());
        if (adminUser == null) {
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        }
        MixOrder mixOrder = Fn.queryMixOrderById(req.getOrderId());
        if (mixOrder == null) {
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.resource_not_exists_exception);
        }
        // 订单
        List<Field> mixOrderFields = new ArrayList<>();
        // 退货申请 - 状态(0未申请,1未审核,2已同意,3已拒绝)
        mixOrderFields.add(new Field("refundRequestStatus", 2));
        mixOrderFields.add(new Field("dtAuditRefundRequest", System.currentTimeMillis()));
        mixOrderFields.add(new Field("refundRequestAuditorId", adminUser.getId()));
        mixOrderFields.add(new Field("refundRequestAuditorLoginName", adminUser.getLoginName()));
        MicroOss.updateObject(MixOrder.class, mixOrderFields, Condition.build("id=", req.getOrderId()));
        // 用户
        User user = Fn.queryUserById(mixOrder.getUserId());
        int orderAmount = mixOrder.getOrderAmount();
        int oldWalletAmount = user.getWalletAmount();
        int newWalletAmount = oldWalletAmount + orderAmount;
        List<Field> userFields = new ArrayList<>();
        userFields.add(new Field("walletAmount", newWalletAmount));
        MicroOss.updateObject(User.class, userFields, Condition.build("id=", user.getId()));
        // 钱包账单
        WalletBill walletBill = new WalletBill();
        walletBill.setId(UUID.randomUUID().toString());
        walletBill.setUserId(user.getId());
        walletBill.setUserNickname(user.getNickname());
        // 类型(1入账,2消费)
        walletBill.setType(1);
        walletBill.setDtCreate(System.currentTimeMillis());
        walletBill.setAmount(orderAmount);
        walletBill.setOldWalletAmount(oldWalletAmount);
        walletBill.setNewWalletAmount(newWalletAmount);
        MicroOss.insertObject(walletBill);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getOrderId(), "orderId");
        Response resp = new Response();
        core(req, resp);
        return resp;
    }

    public static class Req extends Request {

        @Comment(value = "Token")
        @Required(value = true)
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Comment(value = "商品订单ID")
        @Required(value = true)
        private String orderId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
