package com.github.microprograms.yy_vip_center_manager_api.sdk;

import com.github.microprograms.micro_api_sdk.MicroApiSdk;
import com.github.microprograms.micro_api_sdk.model.EngineDefinition;
import com.github.microprograms.micro_api_sdk.utils.ApiDeployUtils;

public class ApiDeployer {
    public static void main(String[] args) throws Exception {
        String path = ApiDeployer.class.getClassLoader().getResource("public-api.json").getPath();
        EngineDefinition engineDefinition = MicroApiSdk.buildEngineDefinition(path);
        ApiDeployUtils.stop(engineDefinition);
        ApiDeployUtils.deploy(engineDefinition);
        ApiDeployUtils.start(engineDefinition);
        Thread.sleep(2 * 1000);
        ApiDeployUtils.showLatestServerOut(engineDefinition);
    }
}
