package com.github.microprograms.yy_vip_center_manager_api.public_api;

import com.github.microprograms.micro_entity_definition_runtime.annotation.Comment;
import com.github.microprograms.micro_api_runtime.annotation.MicroApiAnnotation;
import com.github.microprograms.micro_api_runtime.exception.MicroApiExecuteException;
import com.github.microprograms.micro_api_runtime.enums.MicroApiReserveResponseCodeEnum;
import com.github.microprograms.micro_api_runtime.model.Response;
import com.github.microprograms.micro_api_runtime.model.Request;
import com.github.microprograms.micro_api_runtime.utils.MicroApiUtils;
import com.github.microprograms.micro_entity_definition_runtime.annotation.Required;

@Comment(value = "商品 - 查询列表")
@MicroApiAnnotation(type = "read", version = "v0.0.1")
public class Goods_QueryList_Api {

    private static void core(Req req, Resp resp) throws Exception {
        Object doSomeThingHere;
        throw new MicroApiExecuteException(MicroApiReserveResponseCodeEnum.api_not_implemented_exception);
    }

    public static Response execute(Request request) throws Exception {
        Req req = (Req) request;
        MicroApiUtils.throwExceptionIfBlank(req.getToken(), "token");
        MicroApiUtils.throwExceptionIfBlank(req.getPageIndex(), "pageIndex");
        MicroApiUtils.throwExceptionIfBlank(req.getPageSize(), "pageSize");
        Resp resp = new Resp();
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

        @Comment(value = "页码(从0开始)")
        @Required(value = true)
        private Integer pageIndex;

        public Integer getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
        }

        @Comment(value = "页大小")
        @Required(value = true)
        private Integer pageSize;

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        @Comment(value = "搜索 - 关键字")
        @Required(value = false)
        private String searchKeyword;

        public String getSearchKeyword() {
            return searchKeyword;
        }

        public void setSearchKeyword(String searchKeyword) {
            this.searchKeyword = searchKeyword;
        }

        @Comment(value = "搜索 - 商品类别ID(不传或传空字符串表示忽略此条件)")
        @Required(value = false)
        private String searchCategoryId;

        public String getSearchCategoryId() {
            return searchCategoryId;
        }

        public void setSearchCategoryId(String searchCategoryId) {
            this.searchCategoryId = searchCategoryId;
        }

        @Comment(value = "搜索 - 最小库存")
        @Required(value = false)
        private Integer searchMinStock;

        public Integer getSearchMinStock() {
            return searchMinStock;
        }

        public void setSearchMinStock(Integer searchMinStock) {
            this.searchMinStock = searchMinStock;
        }

        @Comment(value = "搜索 - 最大库存")
        @Required(value = false)
        private Integer searchMaxStock;

        public Integer getSearchMaxStock() {
            return searchMaxStock;
        }

        public void setSearchMaxStock(Integer searchMaxStock) {
            this.searchMaxStock = searchMaxStock;
        }
    }

    public static class Resp extends Response {

        @Comment(value = "商品列表")
        @Required(value = true)
        private java.util.List<Goods> data;

        public java.util.List<Goods> getData() {
            return data;
        }

        public void setData(java.util.List<Goods> data) {
            this.data = data;
        }

        @Comment(value = "分页")
        @Required(value = true)
        private com.github.microprograms.ignite_utils.sql.dml.PagerResponse pager;

        public com.github.microprograms.ignite_utils.sql.dml.PagerResponse getPager() {
            return pager;
        }

        public void setPager(com.github.microprograms.ignite_utils.sql.dml.PagerResponse pager) {
            this.pager = pager;
        }
    }
}
