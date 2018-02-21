package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import java.util.List;
import com.github.microprograms.micro_oss_core.model.dml.Sort;
import java.util.Arrays;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;

@MicroApi(comment = "商品限购 - 查询全部", type = "read", version = "v0.0.18")
public class GoodsBuyLimit_QueryAll_Api {

    private static Condition buildFinalCondition(Req req) {
        return Condition.build("goodsId=", req.getGoodsId());
    }

    private static List<Sort> buildSort(Req req) {
        return Arrays.asList(Sort.desc("dtCreate"));
    }

    private static void core(Req req, Resp resp) throws Exception {
        Condition finalCondition = buildFinalCondition(req);
        List<Sort> sorts = buildSort(req);
        resp.setData(MicroOss.queryAll(GoodsBuyLimit.class, finalCondition, sorts));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getGoodsId(), "goodsId");
        Resp resp = new Resp();
        core(req, resp);
        return resp;
    }

    public static class Req extends Request {

        @Comment(value = "商品ID") @Required(value = true) private String goodsId;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }
    }

    public static class Resp extends Response {

        @Comment(value = "商品限购列表(全部)") @Required(value = true) private java.util.List<GoodsBuyLimit> data;

        public java.util.List<GoodsBuyLimit> getData() {
            return data;
        }

        public void setData(java.util.List<GoodsBuyLimit> data) {
            this.data = data;
        }
    }
}
