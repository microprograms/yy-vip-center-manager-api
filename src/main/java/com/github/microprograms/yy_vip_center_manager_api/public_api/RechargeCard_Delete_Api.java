package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.ignite_utils.sql.dml.Condition;
import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "充值卡 - 删除")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class RechargeCard_Delete_Api {

    private static Object buildFinalCondition(Req req) {
        return Condition.build("id=", req.getRechargeCardId());
    }

    private static void core(Req req, Response resp) throws Exception {
        Object finalCondition = buildFinalCondition(req);
        IgniteUtils.deleteObject(RechargeCard.class, finalCondition);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getRechargeCardId(), "rechargeCardId");
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

        @Comment(value = "充值卡ID")
        @Required(value = true)
        private String rechargeCardId;

        public String getRechargeCardId() {
            return rechargeCardId;
        }

        public void setRechargeCardId(String rechargeCardId) {
            this.rechargeCardId = rechargeCardId;
        }
    }
}
