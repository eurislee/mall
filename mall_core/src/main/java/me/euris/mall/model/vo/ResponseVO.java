package me.euris.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.euris.mall.constant.enums.ResponseStatus;

import java.io.Serializable;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-09-2023 20:40:00
 */

@Data
public class ResponseVO<T> implements Serializable {

    private Integer status;

    private String message;

    private T data;

    private Long timestamp;

    private ResponseVO() {
    }

    private ResponseVO(int status) {
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    private ResponseVO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    private ResponseVO(int status, T data) {
        this.status = status;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    private ResponseVO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    public static <T> ResponseVO<T> success() {
        return new ResponseVO<>(ResponseStatus.SUCCESS.getStatus());
    }

    public static <T> ResponseVO<T> success(String message) {
        return new ResponseVO<>(ResponseStatus.SUCCESS.getStatus(), message);
    }

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(ResponseStatus.SUCCESS.getStatus(), data);
    }

    public static <T> ResponseVO<T> success(String message, T data) {
        return new ResponseVO<>(ResponseStatus.SUCCESS.getStatus(), message, data);
    }


    public static <T> ResponseVO<T> fail() {
        return new ResponseVO<>(ResponseStatus.FAIL.getStatus(), ResponseStatus.FAIL.getMessage());
    }

    public static <T> ResponseVO<T> fail(ResponseStatus responseStatus) {
        return new ResponseVO<>(responseStatus.getStatus(), responseStatus.getMessage());
    }


    public static <T> ResponseVO<T> fail(String message) {
        return new ResponseVO<>(ResponseStatus.FAIL.getStatus(), message);
    }

    public static <T> ResponseVO<T> fail(int status, String message) {
        return new ResponseVO<>(status, message);
    }

    @JsonIgnore
    public boolean successFlag() {
        return this.status.equals(ResponseStatus.SUCCESS.getStatus()) ;
    }
}
