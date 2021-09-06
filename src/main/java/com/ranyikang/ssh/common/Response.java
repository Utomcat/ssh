package com.ranyikang.ssh.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * CLASS_NAME: Response.java <br/>
 * Description: 公共请求响应类 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 09 - 03
 */
@Data
@EqualsAndHashCode
@SuppressWarnings("unused")
public class Response implements Serializable {

    private static final long serialVersionUID = -235733489483300882L;
    /**
     * 请求响应 code,如: 响应成功: 200; 页面未找到: 404;
     */
    private int code = 200;
    /**
     * 请求响应信息,如: 响应成功: success; 响应失败: fail
     */
    private String message = "success";
    /**
     * 请求响应状态,如: 响应成功 true; 响应失败: false;
     */
    private boolean status = true;
    /**
     * 返回数据,后端返回给前端的数据存放在该属性中
     */
    private Object data;
    /**
     * 响应成功常量
     */
    public static final Response SUCCESS = new Response(200, "success", true);

    /**
     * 无参构造响应对象
     */
    private Response() {
    }

    /**
     * 用 code、message、status构造一个 Response 对象
     *
     * @param code    请求响应码
     * @param message 请求响应信息
     * @param status  请求响应状态
     */
    private Response(int code, String message, boolean status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    /**
     * 用 code、message构造请求响应对象
     *
     * @param code    请求响应码
     * @param message 请求响应信息
     */
    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 全参构造响应对象
     *
     * @param code    响应code,如 响应成功 code = 200; 没找到页面 code = 404;
     * @param message 响应信息,如 响应成功 message = success;
     * @param status  响应状态,如 响应成功 true; 响应失败 false;
     * @param data    响应数据,如 List 列表;
     */
    private Response(int code, String message, boolean status, Object data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    /**
     * builder 方法,创建一个 {@link ResponseBuilder } 的内部类对象
     *
     * @return 返回创建的 ResponseBuilder 对象实例
     */
    public static Response.ResponseBuilder builder() {
        return new Response.ResponseBuilder();
    }

    /**
     * 构造一个响应对象,传入返回数据 data
     *
     * @param data 需要返回的数据data
     * @return 返回组装好的响应请求对象
     */
    public static Response valueOfObject(Object data) {
        return builder().status(200).code(200).message("success").object(data).build();
    }

    /**
     * 将 List 集合封装进响应结果中
     *
     * @param list 请求响应需要返回的 List 集合
     * @return 返回组装好的响应请求对象
     */
    @SuppressWarnings("all")
    public static Response valueOfList(List list) {
        return builder().status(200).message(SUCCESS.getMessage()).list(list).build();
    }

    public static Response valueOfCode(int code) {
        return builder().status(200).code(code).build();
    }

    public static Response valueOfStatus(Integer status) {
        return builder().status(status).build();
    }

    public static Response valueOfMsg(String msg) {
        return builder().status(200).message(msg).build();
    }

    public static <T, R> Response valueOfMap(Consumer<Map<String, Object>> action) {
        Map<String, Object> map = new HashMap<>(16);
        action.accept(map);
        return builder().status(200).message(SUCCESS.getMessage()).object(map).build();
    }

    /**
     * 内部类 ResponseBuilder
     */
    @ToString
    public static class ResponseBuilder {
        private boolean status;
        private String message;
        private Object data;
        private int code;
        private final Map<String, Object> map;

        ResponseBuilder() {
            this.status = Response.SUCCESS.status;
            this.message = Response.SUCCESS.message;
            this.code = Response.SUCCESS.code;
            this.map = new HashMap<>(20);
        }

        public Response.ResponseBuilder status(int code) {
            this.status = (code == 200);
            return this;
        }

        public Response.ResponseBuilder code(int code) {
            this.code = code;
            return this;
        }

        public Response.ResponseBuilder message(String msg) {
            this.message = msg;
            return this;
        }

        @SuppressWarnings("all")
        public Response.ResponseBuilder list(List list) {
            Map<String, Object> paged = new HashMap<>(16);
            paged.put("list", list);
            //TODO 此处需要增加对应的分页数据总量的查询
            this.data = paged;
            return this;
        }

        public Response.ResponseBuilder object(Object data) {
            this.data = data;
            return this;
        }

        public Response.ResponseBuilder map(String key, Object value) {
            this.map.put(key, value);
            return this;
        }

        public Response.ResponseBuilder map(Consumer<Map<String, Object>> action) {
            action.accept(this.map);
            this.data = this.map;
            return this;
        }

        public Response build() {
            return this.data == null && this.map.size() > 0 ? new Response(this.code, this.message, this.status, this.map) : new Response(this.code, this.message, this.status, this.data);
        }
    }

}
