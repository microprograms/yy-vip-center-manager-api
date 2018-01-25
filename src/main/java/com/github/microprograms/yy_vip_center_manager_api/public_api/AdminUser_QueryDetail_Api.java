package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;

@MicroApi(comment = "管理员 - 查询详情", type = "read", version = "v0.0.11")
public class AdminUser_QueryDetail_Api {

    private static Condition buildFinalCondition(Req req) {
        return Condition.build("id=", req.getAdminUserId());
    }

    private static void core(Req req, Resp resp) throws Exception {
        Condition finalCondition = buildFinalCondition(req);
        resp.setData(MicroOss.queryObject(AdminUser.class, finalCondition));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getAdminUserId(), "adminUserId");
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

        @Comment(value = "管理员ID")
        @Required(value = true)
        private String adminUserId;

        public String getAdminUserId() {
            return adminUserId;
        }

        public void setAdminUserId(String adminUserId) {
            this.adminUserId = adminUserId;
        }
    }

    public static class Resp extends Response {

        @Comment(value = "管理员详情")
        @Required(value = true)
        private AdminUser data;

        public AdminUser getData() {
            return data;
        }

        public void setData(AdminUser data) {
            this.data = data;
        }
    }
}
