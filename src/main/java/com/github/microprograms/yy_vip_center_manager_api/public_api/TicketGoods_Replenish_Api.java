package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
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
import com.github.microprograms.micro_oss_core.Transaction;
import com.github.microprograms.micro_oss_core.model.dml.InsertCommand;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "卡密商品 - 补货", type = "read", version = "v0.0.20")
public class TicketGoods_Replenish_Api {

    private static Operator<?> getOperator(Req req) throws Exception {
        return Fn.buildOperator(TicketGoods_Replenish_Api.class, req.getToken());
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        try (Transaction transaction = MicroOss.beginTransaction()) {
            String[] tickets = req.getTickets().split("\n");
            for (int i = 0; i < tickets.length; i++) {
                if (StringUtils.isBlank(tickets[i])) {
                    continue;
                }
                Ticket ticket = new Ticket();
                ticket.setId(UUID.randomUUID().toString());
                ticket.setTicket(tickets[i]);
                ticket.setTicketGoodsId(req.getTicketGoodsId());
                ticket.setDtCreate(System.currentTimeMillis());
                transaction.insertObject(new InsertCommand(MicroOss.buildEntity(ticket)));
            }
            transaction.commit();
        }
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getTicketGoodsId(), "ticketGoodsId");
        MicroApiUtils.throwExceptionIfBlank(req.getTickets(), "tickets");
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

        @Comment(value = "卡密商品ID")
        @Required(value = true)
        private String ticketGoodsId;

        public String getTicketGoodsId() {
            return ticketGoodsId;
        }

        public void setTicketGoodsId(String ticketGoodsId) {
            this.ticketGoodsId = ticketGoodsId;
        }

        @Comment(value = "卡密列表(换行符分隔)")
        @Required(value = true)
        private String tickets;

        public String getTickets() {
            return tickets;
        }

        public void setTickets(String tickets) {
            this.tickets = tickets;
        }
    }
}
