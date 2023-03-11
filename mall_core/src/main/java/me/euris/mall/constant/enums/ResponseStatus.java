package me.euris.mall.constant.enums;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-09-2023 20:31:00
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /**
     * 自定义状态:
     */
    SUCCESS(200, "success"),

    FAIL(500, "failed"),
    /**
     * 标准HTTP状态
     */

    HTTP_STATUS_200(200, "成功"),

    HTTP_STATUS_400(400, "请求参数错误"),

    HTTP_STATUS_401(401, "未认证,请先登录"),

    HTTP_STATUS_403(403, "无权限"),

    HTTP_STATUS_404(404, "请求资源不存在"),

    HTTP_STATUS_405(405, "请求方法错误"),

    HTTP_STATUS_500(500, "服务器异常");

    public static final List<ResponseStatus> HTTP_STATUS = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_404, HTTP_STATUS_405, HTTP_STATUS_500)
    );



    /**
     * 自定义异常: 客户端自定义异常从1000开始排列, 服务器端自定义异常从2000开始排列
     */




    private Integer status;
    private String message;
}
