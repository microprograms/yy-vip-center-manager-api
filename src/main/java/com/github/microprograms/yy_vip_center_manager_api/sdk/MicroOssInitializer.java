package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.util.ArrayList;
import java.util.List;

import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.model.FieldDefinition;
import com.github.microprograms.micro_oss_core.model.FieldDefinition.FieldTypeEnum;
import com.github.microprograms.micro_oss_core.model.TableDefinition;
import com.github.microprograms.micro_oss_core.model.ddl.DropTableCommand;
import com.github.microprograms.micro_oss_ignite.IgniteMicroOssProvider;
import com.github.microprograms.micro_oss_ignite.model.ddl.IgniteCreateTableCommand;
import com.github.microprograms.micro_relational_data_model_sdk.MicroRelationalDataModelSdk;
import com.github.microprograms.micro_relational_data_model_sdk.model.PlainEntityDefinition;
import com.github.microprograms.micro_relational_data_model_sdk.model.PlainFieldDefinition;
import com.github.microprograms.micro_relational_data_model_sdk.model.PlainModelerDefinition;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

public class MicroOssInitializer {

    public static void main(String[] args) throws Exception {
        Fn.initOss();
        IgniteMicroOssProvider oss = (IgniteMicroOssProvider) MicroOss.get();
        PlainModelerDefinition modelerDefinition = MicroRelationalDataModelSdk.buildModelerDefinition("design/model.json");
        for (PlainEntityDefinition x : modelerDefinition.getEntityDefinitions()) {
            String with = "atomicity=transactional";
            oss.dropTable(new DropTableCommand(x.getJavaClassName()));
            oss.createTable(new IgniteCreateTableCommand(buildTableDefinition(x), with));
        }
    }

    private static TableDefinition buildTableDefinition(PlainEntityDefinition entityDefinition) {
        List<FieldDefinition> fields = new ArrayList<>();
        for (PlainFieldDefinition x : entityDefinition.getFieldDefinitions()) {
            fields.add(new FieldDefinition(x.getName(), FieldTypeEnum.parse(x.getJavaType()), x.getDefaultValue(), x.getPrimaryKey()));
        }
        return new TableDefinition(entityDefinition.getJavaClassName(), fields);
    }
}