package com.apark.controller.exception;


import com.apark.common.response.BaseResponseCode;
import com.apark.common.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring mvc 全局异常处理
 *
 * @author flatychen
 */
@ControllerAdvice
@RestController
@Slf4j
public class DefalutExceptionHandler {



    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Object  handleException(
            Exception ex) {
        log.error("Exception", ex);
        return new ServiceResponse(BaseResponseCode.FAIL);

    }


}
