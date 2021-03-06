package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.model.Field;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "系统 - 退出", type = "read", version = "v0.0.20")
public class System_Logout_Api {

    private static void core(Req req, Response resp) throws Exception {
        AdminUser adminUser = Fn.queryAdminUserByToken(req.getToken());
        if (adminUser == null) {
            throw new MicroApiPassthroughException(ErrorCodeEnum.invalid_token);
        }
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("token", UUID.randomUUID().toString()));
        MicroOss.updateObject(AdminUser.class, fields, Condition.build("token=", req.getToken()));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
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
    }
}
