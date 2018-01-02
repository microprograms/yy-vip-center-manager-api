package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.micro_api_runtime.exception.MicroApiExecuteException;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "调试 - Ignite")
@MicroApiAnnotation(type = "read", version = "v0.0.1")
public class Debug_Ignite_Api {

    private static void core(Request req, Resp resp) throws Exception {
        Object doSomeThingHere;
        throw new MicroApiExecuteException(MicroApiReserveResponseCodeEnum.api_not_implemented_exception);
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
