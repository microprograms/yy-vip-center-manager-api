package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.micro_api_runtime.model.Operator;
import com.github.microprograms.ignite_utils.IgniteUtils;
import com.github.microprograms.micro_api_runtime.exception.MicroApiExecuteException;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "商品类别 - 新增商品类别")
@MicroApiAnnotation(type = "read", version = "v0.0.2")
public class GoodsCategory_Add_Api {

    private static Operator<?> getOperator(Req req) {
        return null;
    }

    private static GoodsCategory buildGoodsCategory(Req req) {
        return null;
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiExecuteException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiExecuteException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        IgniteUtils.insertObject(buildGoodsCategory(req));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getName(), "name");
        MicroApiUtils.throwExceptionIfBlank(req.getReorder(), "reorder");
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

        @Comment(value = "商品类别名称")
        @Required(value = true)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Comment(value = "排序")
        @Required(value = true)
        private Integer reorder;

        public Integer getReorder() {
            return reorder;
        }

        public void setReorder(Integer reorder) {
            this.reorder = reorder;
        }
    }
}
