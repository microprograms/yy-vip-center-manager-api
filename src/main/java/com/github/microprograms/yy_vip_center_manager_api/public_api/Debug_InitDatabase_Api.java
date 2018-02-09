package com.github.microprograms.yy_vip_center_manager_api.public_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.yy_vip_center_manager_api.sdk.MicroOssInitializer;
import com.github.microprograms.yy_vip_center_manager_api.sdk.SampleData;

@MicroApi(comment = "调试 - 初始化数据库", type = "read", version = "v0.0.12")
public class Debug_InitDatabase_Api {

    private static final Logger log = LoggerFactory.getLogger(Debug_InitDatabase_Api.class);

    private static void core(Request req, Response resp) throws Exception {
        log.info("init database...");
        MicroOssInitializer.init();
        SampleData.init();
        log.info("init database done.");
    }

    public static Response execute(Request request) throws Exception {
        Request req = request;
        Response resp = new Response();
        core(req, resp);
        return resp;
    }
}
