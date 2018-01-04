package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import java.util.List;
import com.github.microprograms.micro_oss_core.model.dml.Sort;
import java.util.Arrays;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "管理员 - 查询全部")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class AdminUser_QueryAll_Api {

    private static Condition buildFinalCondition(Req req) {
        return null;
    }

    private static List<Sort> buildSort(Req req) {
        return Arrays.asList(Sort.desc("dtCreate"));
    }

    private static void core(Req req, Resp resp) throws Exception {
        Condition finalCondition = buildFinalCondition(req);
        List<Sort> sorts = buildSort(req);
        resp.setData(MicroOss.queryAll(AdminUser.class, finalCondition, sorts));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
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
    }

    public static class Resp extends Response {

        @Comment(value = "管理员列表")
        @Required(value = true)
        private java.util.List<AdminUser> data;

        public java.util.List<AdminUser> getData() {
            return data;
        }

        public void setData(java.util.List<AdminUser> data) {
            this.data = data;
        }
    }
}
