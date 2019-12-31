package org.nuc.course.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.nuc.course.core.response.Result;
import org.nuc.course.core.response.ResultCode;
import org.nuc.course.core.response.ResultGenerator;
import org.nuc.course.utils.UrlUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 *
 * <p>对于业务异常：返回头 Http 状态码一律使用500，避免浏览器缓存，在响应 Result 中指明异常的状态码 code
 *
 * @author Zoctan
 * @date 2018/06/09
 */
@RestControllerAdvice
public class ExceptionResolver {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result validatorException(final ConstraintViolationException e) {
        final String msg =
                e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(","));
        // e.toString 多了不需要用户知道的属性路径
        e.printStackTrace();
        return ResultGenerator.genFailedResult(ResultCode.VIOLATION_EXCEPTION, msg);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServiceException.class})
    public Result serviceException(final ServiceException e) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(e.getResultCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ResourcesNotFoundException.class})
    public Result resourcesException(final Throwable e) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(ResultCode.FIND_FAILED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public Result databaseException(final Throwable e) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(ResultCode.DATABASE_EXCEPTION);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public Result authException(final Throwable e) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(ResultCode.UNAUTHORIZED_EXCEPTION);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class, UsernameNotFoundException.class})
    public Result accountException(final Throwable e) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result apiNotFoundException(final Throwable e, final HttpServletRequest request) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(
                "API [" + UrlUtils.getMappingUrl(request) + "] not existed");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result globalException(final HttpServletRequest request, final Throwable e) {
        e.printStackTrace();
        return ResultGenerator.genFailedResult(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                String.format("%s => %s", UrlUtils.getMappingUrl(request), e.getMessage()));
    }
}
