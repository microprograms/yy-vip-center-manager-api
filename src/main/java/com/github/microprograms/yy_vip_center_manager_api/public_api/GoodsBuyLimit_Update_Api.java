package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.ignite_utils.sql.dml.Condition;
import com.github.microprograms.ignite_utils.sql.dml.FieldToUpdate;
import java.util.List;
import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "商品限购 - 更新商品限购")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class GoodsBuyLimit_Update_Api {

    private static Object buildFinalCondition(Req req) {
        return Condition.build("id=", req.getBuyLimitId());
    }

    private static List<FieldToUpdate> buildFieldsToUpdate(Req req) {
        return null;
    }

    private static void core(Req req, Response resp) throws Exception {
        Object finalCondition = buildFinalCondition(req);
        List<FieldToUpdate> fields = buildFieldsToUpdate(req);
        IgniteUtils.updateFieldsForObject(GoodsBuyLimit.class, fields, finalCondition);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getBuyLimitId(), "buyLimitId");
        MicroApiUtils.throwExceptionIfBlank(req.getAmount(), "amount");
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

        @Comment(value = "商品限购ID")
        @Required(value = true)
        private String buyLimitId;

        public String getBuyLimitId() {
            return buyLimitId;
        }

        public void setBuyLimitId(String buyLimitId) {
            this.buyLimitId = buyLimitId;
        }

        @Comment(value = "限购数量")
        @Required(value = true)
        private Integer amount;

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }
}
