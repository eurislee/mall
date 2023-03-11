package me.euris.mall.exception;

import lombok.extern.slf4j.Slf4j;
import me.euris.mall.constant.enums.ResponseStatus;
import me.euris.mall.model.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 20:22:00
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * @Name badRequestException
     * @Description 参数格式异常处理
     * @Author Euris Lee
     * @param: exception
     * @CreateTime 3/10/23 8:26 PM
     * @return: me.euris.mall.core.model.vo.ResponseVO<java.lang.String>
     * @Throws
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseVO<String> badRequestException(IllegalArgumentException exception) {
        log.error("参数格式不合法：{}", exception.getMessage());
        return ResponseVO.fail(HttpStatus.BAD_REQUEST.value(), " 参数格式不符！");
    }

    @ExceptionHandler({ParamException.class})
    public ResponseVO<String> paramException(ParamException exception) {
        log.error(exception.getMessage());
        return ResponseVO.fail(ResponseStatus.HTTP_STATUS_400.getStatus(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseVO<String> handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常，{}", ex.getMessage());
        return ResponseVO.fail("空指针异常");
    }
}
