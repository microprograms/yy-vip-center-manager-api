package com.github.microprograms.yy_vip_center_manager_api.public_api;

import java.util.UUID;
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
import com.github.microprograms.yy_vip_center_manager_api.utils.Fn;

@MicroApi(comment = "商品类别 - 新增商品类别", type = "read", version = "v0.0.9")
public class GoodsCategory_Add_Api {

    private static Operator<?> getOperator(Req req) throws Exception {
        return Fn.buildOperator(GoodsCategory_Add_Api.class, req.getToken());
    }

    private static GoodsCategory buildGoodsCategory(Req req) throws MicroOssException {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(UUID.randomUUID().toString());
        goodsCategory.setName(req.getName());
        goodsCategory.setDesc(req.getDesc());
        goodsCategory.setReorder(req.getReorder());
        AdminUser adminUser = Fn.queryAdminUserByToken(req.getToken());
        goodsCategory.setCreaterId(adminUser.getId());
        goodsCategory.setCreaterLoginName(adminUser.getLoginName());
        goodsCategory.setDtCreate(System.currentTimeMillis());
        return goodsCategory;
    }

    private static void core(Req req, Response resp) throws Exception {
        Operator<?> operator = getOperator(req);
        if (operator == null)
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.unknown_operator_exception);
        if (operator.isPermissionDenied())
            throw new MicroApiPassthroughException(MicroApiReserveResponseCodeEnum.permission_denied_exception);
        MicroOss.insertObject(buildGoodsCategory(req));
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getName(), "name");
        MicroApiUtils.throwExceptionIfBlank(req.getDesc(), "desc");
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
