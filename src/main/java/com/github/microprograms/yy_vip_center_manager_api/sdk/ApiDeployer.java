package com.github.microprograms.yy_vip_center_manager_api.sdk;

import com.github.microprograms.micro_api_sdk.model.EngineDefinition;
import com.github.microprograms.micro_api_sdk.utils.ApiDeployUtils;
import com.github.microprograms.micro_api_sdk.utils.ApiEngineGeneratorUtils;

public class ApiDeployer {
    public static void main(String[] args) throws Exception {
        EngineDefinition engineDefinition = ApiEngineGeneratorUtils.buildEngineDefinition("design/public-api.json");
        ApiDeployUtils.stop(engineDefinition);
        ApiDeployUtils.deploy(engineDefinition);
        ApiDeployUtils.start(engineDefinition);
    }
}
