package com.hondee.service_plan.response;

/**
 * Title: 统一响应结构
 * Description:使用REST框架实现前后端分离架构，我们需要首先确定返回的JSON响应结构是统一的，
 * 也就是说，每个REST请求将返回相同结构的JSON响应结构。不妨定义一个相对通用的JSON响应结构，
 * 其中包含两部分：元数据与返回值，其中，元数据表示操作是否成功与返回值消息等，返回值对应服务端方法所返回的数据。
 * { "meta": { "success": true, "message": "ok" }, "data": ... }
 */
public class Response {

    private static final String OK = "ok";
    private static final String CODE = "200";
    private static final String ERROR = "error";

    private Meta meta;     // 元数据
    private Object data;   // 响应内容

    public Response success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Response failure(String message, String code) {
        this.meta = new Meta(false, message, code);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    /**
     * Title: 请求元数据
     */
    public class Meta {

        private boolean success;
        private String message;
        private String code = Response.CODE;

        public Meta(boolean success) {
            this.success = success;
        }

        Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        Meta(boolean success, String message, String code) {
            this.success = success;
            this.message = message;
            this.code = code;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

}
