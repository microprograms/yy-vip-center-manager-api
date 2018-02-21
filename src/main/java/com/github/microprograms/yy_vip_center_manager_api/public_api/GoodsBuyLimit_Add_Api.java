package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.UUID;
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
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "商品限购 - 新增商品限购", type = "read", version = "v0.0.18")
public class GoodsBuyLimit_Add_Api {

    private static Operator<?> getOperator(Req req) throws Exception {
        return Fn.buildOperator(GoodsBuyLimit_Add_Api.class, req.getToken());
    }

    private static GoodsBuyLimit buildGoodsBuyLimit(Req req) throws MicroApiPassthroughException, MicroOssException {
        Goods goods = Fn.queryGoodsById(req.getGoodsId());
        if (goods == null) {
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.resource_not_exists_exception);
        }
        User user = Fn.queryUserByNickname(req.getUserNickname());
        if (user == null) {
            throw new MicroApiPassthroughException(ErrorCodeEnum.invalid_nickname);
        }
        if (Fn.queryGoodsBuyLimitByGoodsIdAndUserNickname(req.getGoodsId(), req.getUserNickname()) != null) {
            throw new MicroApiPassthroughException(ErrorCodeEnum.goodsBuyLimit_already_exist);
        }
        GoodsBuyLimit goodsBuyLimit = new GoodsBuyLimit();
        goodsBuyLimit.setId(UUID.randomUUID().toString());
        goodsBuyLimit.setGoodsId(goods.getId());
        goodsBuyLimit.setGoodsName(goods.getName());
        goodsBuyLimit.setUserId(user.getId());
        goodsBuyLimit.setUserNickname(user.getNickname());
        goodsBuyLimit.setDtCreate(System.currentTimeMillis());
        goodsBuyLimit.setAmount(req.getAmount());
        return goodsBuyLimit;
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        MicroOss.insertObject(buildGoodsBuyLimit(req));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getGoodsId(), "goodsId");
        MicroApiUtils.throwExceptionIfBlank(req.getUserNickname(), "userNickname");
        MicroApiUtils.throwExceptionIfBlank(req.getAmount(), "amount");
        Response resp = new Response();
        core(req, resp);
        return resp;
    }

    public static class Req extends Request {

        @Comment(value = "Token") @Required(value = true) private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Comment(value = "商品ID") @Required(value = true) private String goodsId;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        @Comment(value = "用户昵称") @Required(value = true) private String userNickname;

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }

        @Comment(value = "限购数量") @Required(value = true) private Integer amount;

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }
}
