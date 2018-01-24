package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.micro_oss_core.model.dml.Where;

@MicroApi(comment = "系统 - 登录", type = "read", version = "v0.0.9")
public class System_Login_Api {

    private static void core(Req req, Resp resp) throws Exception {
        AdminUser adminUser = MicroOss.queryObject(AdminUser.class, Where.and(Condition.build("loginName=", req.getLoginName()), Condition.build("loginPassword=", req.getLoginPassword())));
        if (adminUser == null) {
            throw new MicroApiPassthroughException(ErrorCodeEnum.invalid_password);
        }
        resp.setData(adminUser);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getLoginName(), "loginName");
        MicroApiUtils.throwExceptionIfBlank(req.getLoginPassword(), "loginPassword");
        Resp resp = new Resp();
        core(req, resp);
        return resp;
    }

    public static class Req extends Request {

        @Comment(value = "登录名")
        @Required(value = true)
        private String loginName;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        @Comment(value = "登录密码")
        @Required(value = true)
        private String loginPassword;

        public String getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
            this.loginPassword = loginPassword;
        }
    }

    public static class Resp extends Response {

        @Comment(value = "个人资料详情")
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
