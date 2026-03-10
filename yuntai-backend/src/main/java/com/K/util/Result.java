package com.K.util;

public class Result<T> {

    // 1. 属性私有化，保证良好的封装性，防止外部直接篡改
    private Integer code;
    private String message;
    private T data;

    // 2. 必须提供无参构造函数，防止 Jackson(JSON转换工具) 反序列化报错
    public Result() {
    }

    // 3. 保留你原有的全参构造函数
    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // ==========================================
    // 4. 【完美保留你原来的方法】兼容现有代码！
    // ==========================================
    public static <T> Result<T> of(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    // ==========================================
    // 5. 新增的规范工厂方法（方便以后写新接口用）
    // ==========================================

    public static <T> Result<T> ok() {
        return new Result<>(200, "成功", null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "成功", data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(500, "失败", null);
    }

    // ==========================================
    // 6. 支持链式编程的方法
    // ==========================================

    public Result<T> message(String message) {
        this.setMessage(message);
        return this; // 返回当前对象，方便继续 .code()
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    // ==========================================
    // 7. 标准的 Getter 和 Setter（框架强依赖）
    // ==========================================

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}