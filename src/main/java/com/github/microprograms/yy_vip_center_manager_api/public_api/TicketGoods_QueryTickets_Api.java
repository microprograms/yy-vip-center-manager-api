package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_core.model.dml.Where;

@MicroApi(comment = "卡密商品 - 查询剩余卡密列表", type = "read", version = "v0.0.20")
public class TicketGoods_QueryTickets_Api {

    private static Condition buildFinalCondition(Req req) {
        return Where.and(Condition.build("ticketGoodsId=", req.getTicketGoodsId()), Condition.build("isSoldOut=", 0));
    }

    private static void core(Req req, Resp resp) throws Exception {
        Condition finalCondition = buildFinalCondition(req);
        resp.setData(MicroOss.queryAll(Ticket.class, finalCondition));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getTicketGoodsId(), "ticketGoodsId");
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

        @Comment(value = "卡密商品ID")
        @Required(value = true)
        private String ticketGoodsId;

        public String getTicketGoodsId() {
            return ticketGoodsId;
        }

        public void setTicketGoodsId(String ticketGoodsId) {
            this.ticketGoodsId = ticketGoodsId;
        }
    }

    public static class Resp extends Response {

        @Comment(value = "卡密列表")
        @Required(value = true)
        private java.util.List<Ticket> data;

        public java.util.List<Ticket> getData() {
            return data;
        }

        public void setData(java.util.List<Ticket> data) {
            this.data = data;
        }
    }
}
