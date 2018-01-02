package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import java.util.List;
import com.github.microprograms.ignite_utils.sql.dml.Sort;
import java.util.Arrays;
import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "商品限购 - 查询全部")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class GoodsBuyLimit_QueryAll_Api {

    private static Object buildFinalCondition(Request req) {
        return null;
    }

    private static List<Sort> buildSort(Request req) {
        return Arrays.asList(Sort.desc("dtCreate"));
    }

    private static void core(Request req, Resp resp) throws Exception {
        Object finalCondition = buildFinalCondition(req);
        List<Sort> sorts = buildSort(req);
        resp.setData(IgniteUtils.queryAllObject(GoodsBuyLimit.class, finalCondition, sorts));
    }

    public static Response execute(Request request) throws Exception {
        Request req = request;
        Resp resp = new Resp();
        core(req, resp);
        return resp;
    }

    public static class Resp extends Response {

        @Comment(value = "商品限购列表(全部)")
        @Required(value = true)
        private java.util.List<GoodsBuyLimit> data;

        public java.util.List<GoodsBuyLimit> getData() {
            return data;
        }

        public void setData(java.util.List<GoodsBuyLimit> data) {
            this.data = data;
        }
    }
}
