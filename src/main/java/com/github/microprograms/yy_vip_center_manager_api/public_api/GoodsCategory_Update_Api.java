package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.ArrayList;
import java.util.List;
import com.github.microprograms.micro_api_runtime.annotation.MicroApi;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.exception.MicroApiPassthroughException;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_nested_data_model_runtime.Comment;
import com.github.microprograms.micro_nested_data_model_runtime.Required;
import com.github.microprograms.micro_oss_core.MicroOss;
import com.github.microprograms.micro_oss_core.exception.MicroOssException;
import com.github.microprograms.micro_oss_core.model.Field;
import com.github.microprograms.micro_oss_core.model.dml.Condition;
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "商品类别 - 更新商品类别", type = "read", version = "v0.0.20")
public class GoodsCategory_Update_Api {

    private static Operator<?> getOperator(Req req) throws MicroOssException {
        return Fn.buildOperator(GoodsCategory_Update_Api.class, req.getToken());
    }

    private static Condition buildFinalCondition(Req req) {
        return Condition.build("id=", req.getCategoryId());
    }

    private static List<Field> buildFieldsToUpdate(Req req) {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("name", req.getName()));
        fields.add(new Field("desc", req.getDesc()));
        fields.add(new Field("reorder", req.getReorder()));
        return Fn.buildFieldsIgnoreBlank(fields);
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        Condition finalCondition = buildFinalCondition(req);
        List<Field> fields = buildFieldsToUpdate(req);
        MicroOss.updateObject(GoodsCategory.class, fields, finalCondition);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getCategoryId(), "categoryId");
        MicroApiUtils.throwExceptionIfBlank(req.getDesc(), "desc");
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

        @Comment(value = "商品类别ID")
        @Required(value = true)
        private String categoryId;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        @Comment(value = "商品类别名称")
        @Required(value = false)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Comment(value = "商品类别描述")
        @Required(value = true)
        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Comment(value = "排序")
        @Required(value = false)
        private Integer reorder;

        public Integer getReorder() {
            return reorder;
        }

        public void setReorder(Integer reorder) {
            this.reorder = reorder;
        }
    }
}
