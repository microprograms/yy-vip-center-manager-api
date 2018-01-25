package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.model.FieldDefinition;
import com.github.microprograms.micro_oss_core.model.FieldDefinition.FieldTypeEnum;
import com.github.microprograms.micro_oss_core.model.TableDefinition;
import com.github.microprograms.micro_oss_core.model.ddl.CreateTableCommand;
import com.github.microprograms.micro_oss_core.model.ddl.DropTableCommand;
import com.github.microprograms.micro_oss_mysql.MysqlMicroOssProvider;
import com.github.microprograms.micro_relational_data_model_sdk.MicroRelationalDataModelSdk;
import com.github.microprograms.micro_relational_data_model_sdk.model.PlainEntityDefinition;
import com.github.microprograms.micro_relational_data_model_sdk.model.PlainFieldDefinition;
import com.github.microprograms.micro_relational_data_model_sdk.model.PlainModelerDefinition;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

public class MicroOssInitializer {
    private static final Logger log = LoggerFactory.getLogger(MicroOssInitializer.class);

    public static void main(String[] args) throws Exception {
        Fn.initOss();
        init();
    }

    public static void init() throws Exception {
        InputStream is = MicroOssInitializer.class.getClassLoader().getResourceAsStream("model.json");
        File tempFile = File.createTempFile("yy_vip_center_manager_api", null);
        OutputStream os = new FileOutputStream(tempFile);
        try {
            IOUtils.copy(is, os);
            init(tempFile.getPath());
        } catch (Exception e) {
            log.error("", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
            tempFile.delete();
        }
    }

    private static void init(String modelerDefinitionFilepath) throws Exception {
        MysqlMicroOssProvider oss = (MysqlMicroOssProvider) MicroOss.get();
        PlainModelerDefinition modelerDefinition = MicroRelationalDataModelSdk.buildModelerDefinition(modelerDefinitionFilepath);
        for (PlainEntityDefinition x : modelerDefinition.getEntityDefinitions()) {
            oss.dropTable(new DropTableCommand(x.getJavaClassName()));
            oss.createTable(new CreateTableCommand(buildTableDefinition(x)));
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
