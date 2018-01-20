package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;

@MicroApi(comment = "调试 - Ignite", type = "read", version = "v0.0.5")
public class Debug_Ignite_Api {

    private static void core(Request req, Resp resp) throws Exception {
        Object doSomeThingHere;
        throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.api_not_implemented_exception);
    }

    public static Response execute(Request request) throws Exception {
        Request req = request;
        Resp resp = new Resp();
        core(req, resp);
        return resp;
    }

    public static class Resp extends Response {

        @Comment(value = "缓存名列表")
        @Required(value = true)
        private java.util.List<String> cacheNames;

        public java.util.List<String> getCacheNames() {
            return cacheNames;
        }

        public void setCacheNames(java.util.List<String> cacheNames) {
            this.cacheNames = cacheNames;
        }
    }
}
