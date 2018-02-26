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
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.micro_oss_core.model.dml.InsertCommand;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "卡密商品 - 添加新卡密商品", type = "read", version = "v0.0.20")
public class TicketGoods_Publish_Api {

    private static Operator<?> getOperator(Req req) throws Exception {
        return Fn.buildOperator(TicketGoods_Publish_Api.class, req.getToken());
    }

    private static TicketGoods buildTicketGoods(Req req) throws MicroOssException {
        TicketGoods obj = new TicketGoods();
        obj.setId(UUID.randomUUID().toString());
        obj.setReorder(req.getReorder());
        obj.setPictureUrls(req.getPictureUrls());
        obj.setName(req.getName());
        obj.setDesc(req.getDesc());
        obj.setPrice(req.getPrice());
        obj.setPriceLevel1(req.getPriceLevel1());
        obj.setPriceLevel2(req.getPriceLevel2());
        obj.setPriceLevel3(req.getPriceLevel3());
        obj.setDtCreate(System.currentTimeMillis());
        AdminUser adminUser = Fn.queryAdminUserByToken(req.getToken());
        obj.setCreaterId(adminUser.getId());
        obj.setCreaterLoginName(adminUser.getLoginName());
        return obj;
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        try (Transaction transaction = MicroOss.beginTransaction()) {
            TicketGoods ticketGoods = buildTicketGoods(req);
            transaction.insertObject(new InsertCommand(MicroOss.buildEntity(ticketGoods)));
            String[] tickets = req.getTickets().split("\n");
            for (int i = 0; i < tickets.length; i++) {
                if (StringUtils.isBlank(tickets[i])) {
                    continue;
                }
                Ticket ticket = new Ticket();
                ticket.setId(UUID.randomUUID().toString());
                ticket.setTicket(tickets[i]);
                ticket.setTicketGoodsId(ticketGoods.getId());
                ticket.setDtCreate(System.currentTimeMillis());
                transaction.insertObject(new InsertCommand(MicroOss.buildEntity(ticket)));
            }
            transaction.commit();
        }
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getReorder(), "reorder");
        MicroApiUtils.throwExceptionIfBlank(req.getPictureUrls(), "pictureUrls");
        MicroApiUtils.throwExceptionIfBlank(req.getName(), "name");
        MicroApiUtils.throwExceptionIfBlank(req.getDesc(), "desc");
        MicroApiUtils.throwExceptionIfBlank(req.getPrice(), "price");
        MicroApiUtils.throwExceptionIfBlank(req.getPriceLevel1(), "priceLevel1");
        MicroApiUtils.throwExceptionIfBlank(req.getPriceLevel2(), "priceLevel2");
        MicroApiUtils.throwExceptionIfBlank(req.getPriceLevel3(), "priceLevel3");
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

        @Comment(value = "排序号(小的在前)")
        @Required(value = true)
        private Integer reorder;

        public Integer getReorder() {
            return reorder;
        }

        public void setReorder(Integer reorder) {
            this.reorder = reorder;
        }

        @Comment(value = "图片URL列表(JsonArray)")
        @Required(value = true)
        private String pictureUrls;

        public String getPictureUrls() {
            return pictureUrls;
        }

        public void setPictureUrls(String pictureUrls) {
            this.pictureUrls = pictureUrls;
        }

        @Comment(value = "商品名称")
        @Required(value = true)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Comment(value = "商品描述/属性")
        @Required(value = true)
        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Comment(value = "商品价格(分)")
        @Required(value = true)
        private Integer price;

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @Comment(value = "商品价格(分) - 一级代理商")
        @Required(value = true)
        private Integer priceLevel1;

        public Integer getPriceLevel1() {
            return priceLevel1;
        }

        public void setPriceLevel1(Integer priceLevel1) {
            this.priceLevel1 = priceLevel1;
        }

        @Comment(value = "商品价格(分) - 二级代理商")
        @Required(value = true)
        private Integer priceLevel2;

        public Integer getPriceLevel2() {
            return priceLevel2;
        }

        public void setPriceLevel2(Integer priceLevel2) {
            this.priceLevel2 = priceLevel2;
        }

        @Comment(value = "商品价格(分) - 三级代理商")
        @Required(value = true)
        private Integer priceLevel3;

        public Integer getPriceLevel3() {
            return priceLevel3;
        }

        public void setPriceLevel3(Integer priceLevel3) {
            this.priceLevel3 = priceLevel3;
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
