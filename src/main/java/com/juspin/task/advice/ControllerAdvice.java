package com.juspin.task.advice;


import com.juspin.task.entity.ResponseResult;
import com.juspin.task.entity.ReturnCode;
import com.juspin.task.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * Description: 全局统一返回结果封装及异常处理
 *
 * @author juspin
 * @since 2025/1/26
 */
@Slf4j
@RestControllerAdvice("com.juspin.task.controller")
public class ControllerAdvice implements ResponseBodyAdvice<Object> {

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public ControllerAdvice(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //拦截被@ResponseBody注解标记的controller
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) || returnType.hasMethodAnnotation(ResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return ResponseResult.ok(body);
    }

    //不支持的请求类型
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    private Object httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        printLog4Exception(exception);
        return ResponseResult.nOk(ReturnCode.NOT_FOUND);
    }


    //缺少参数
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    private Object missingServletRequestParameter(MissingServletRequestParameterException exception) {
        printLog4Exception(exception);
        return ResponseResult.nOk(ReturnCode.INVALID_PARAM);
    }

    //参数类型不匹配
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    private Object methodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        printLog4Exception(exception);
        return ResponseResult.nOk(ReturnCode.INVALID_PARAM);
    }

    //参数未通过@Valid验证异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private Object methodArgumentNotValid(MethodArgumentNotValidException exception) {
        printLog4Exception(exception);
        return ResponseResult.nOk(ReturnCode.INVALID_PARAM);
    }

    //业务异常
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    private Object businessException(ServiceException exception) {
        printLog4Exception(exception);
        return ResponseResult.nOk(ReturnCode.INVALID_PARAM.getCode(), null, exception.getMessage());
    }

    //其他异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Object commonExceptionHandler(Exception exception) {
        printLog4Exception(exception);
        return ResponseResult.nOk(ReturnCode.INTERNAL_ERROR);
    }

    //异常日志记录
    private void printLog4Exception(Exception exception) {
        // for test!
        System.out.println("caught exception——" + exception.getClass());
        log.error("API URL:{}", httpServletRequest.getRequestURI());
        log.error("Error massage:{}", exception.getMessage());
        log.error("Error occurred.", exception);
    }
}
