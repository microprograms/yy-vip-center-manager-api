package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "充值卡 - 单个添加", type = "read", version = "v0.0.8")
public class RechargeCard_Add_Api {

    private static Operator<?> getOperator(Req req) throws Exception {
        return Fn.buildOperator(RechargeCard_Add_Api.class, req.getToken());
    }

    private static RechargeCard buildRechargeCard(Req req) throws Exception {
        RechargeCard rechargeCard = new RechargeCard();
        String rawPasswordSeriesCode = req.getRawPasswordSeriesCode();
        int lastIndexOfSeparator = rawPasswordSeriesCode.lastIndexOf("@");
        if (lastIndexOfSeparator == -1) {
            throw new MicroApiPassthroughException(ErrorCodeEnum.invalid_recharge_card);
        }
        String amountString = rawPasswordSeriesCode.substring(lastIndexOfSeparator + 1);
        rechargeCard.setId(rawPasswordSeriesCode);
        rechargeCard.setRawPasswordSeriesCode(rawPasswordSeriesCode);
        rechargeCard.setAmount(Integer.valueOf(amountString));
        AdminUser adminUser = Fn.queryAdminUserByToken(req.getToken());
        rechargeCard.setCreaterId(adminUser.getId());
        rechargeCard.setCreaterLoginName(adminUser.getLoginName());
        rechargeCard.setDtCreate(System.currentTimeMillis());
        return rechargeCard;
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        MicroOss.insertObject(buildRechargeCard(req));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getRawPasswordSeriesCode(), "rawPasswordSeriesCode");
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

        @Comment(value = "原始密码序列编码串")
        @Required(value = true)
        private String rawPasswordSeriesCode;

        public String getRawPasswordSeriesCode() {
            return rawPasswordSeriesCode;
        }

        public void setRawPasswordSeriesCode(String rawPasswordSeriesCode) {
            this.rawPasswordSeriesCode = rawPasswordSeriesCode;
        }
    }
}
