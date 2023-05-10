package com.example.demo.handle;

import com.alibaba.fastjson.JSON;
import com.example.demo.enumeration.ReturnCode;
import com.example.demo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Set;

@RestControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = BusinessException.class)
    public Result businessException(BusinessException e) {
        logger.warn("【业务异常】\n msg:{} \n data:{}", e.getMsg(), e.getData());
        return Result.fail(e.getCode(), e.getMsg(), e.getData());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.warn("【参数错误】" + e.getMessage());
        return Result.fail(ReturnCode.DATA_NOT_NULL.getCode(), ReturnCode.DATA_NOT_NULL.getMsg(), JSON.toJSONString(e));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException e){
        logger.warn("【参数错误】" + e.getMessage());
        return Result.fail(ReturnCode.PARAMETER_ERROR.getCode(), ReturnCode.PARAMETER_ERROR.getMsg(), JSON.toJSONString(e));
    }

    /**
     * 捕获 @Valid 参数校验抛出的异常
     * import javax.validation.Valid;
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        logger.warn("【参数错误】" + objectError.getDefaultMessage());
        // 然后提取错误提示信息进行返回
        return Result.fail(ReturnCode.PARAMETER_ERROR.getCode(), objectError.getDefaultMessage(), objectError.getDefaultMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return Result.fail(ReturnCode.REQUEST_METHOD_ERROR.getCode(), ReturnCode.REQUEST_METHOD_ERROR.getMsg(), JSON.toJSONString(e));
    }


    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        logger.warn("【系统异常】", e);
        return Result.error(JSON.toJSONString(e));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationExceptionHandle(ConstraintViolationException e) {
        logger.warn("【系统异常】", e);
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = set.iterator();
        String messageTemplate = "";
        while (iterator.hasNext()) {
            ConstraintViolation<?> violation = iterator.next();
            messageTemplate = violation.getMessageTemplate();
        }
        return Result.fail(ReturnCode.PARAMETER_ERROR.getCode(), messageTemplate, JSON.toJSONString(e));
    }
    @ExceptionHandler(value = BindException.class)
    public Result bindExceptionHandle(BindException e) {
        logger.warn("【参数异常】", e);
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return Result.fail(ReturnCode.PARAMETER_ERROR.getCode(), objectError.getDefaultMessage(), objectError.getDefaultMessage());
    }
}
