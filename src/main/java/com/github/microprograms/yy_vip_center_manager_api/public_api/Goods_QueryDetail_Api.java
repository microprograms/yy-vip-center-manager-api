package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.ignite_utils.sql.dml.Condition;
import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "商品 - 查询详情")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class Goods_QueryDetail_Api {

    private static Object buildFinalCondition(Req req) {
        return Condition.build("id=", req.getGoodsId());
    }

    private static void core(Req req, Resp resp) throws Exception {
        Object finalCondition = buildFinalCondition(req);
        resp.setData(IgniteUtils.queryObject(Goods.class, finalCondition));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getGoodsId(), "goodsId");
        Resp resp = new Resp();
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

        @Comment(value = "商品ID")
        @Required(value = true)
        private String goodsId;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }
    }

    public static class Resp extends Response {

        @Comment(value = "商品详情")
        @Required(value = true)
        private Goods data;

        public Goods getData() {
            return data;
        }

        public void setData(Goods data) {
            this.data = data;
        }
    }
}