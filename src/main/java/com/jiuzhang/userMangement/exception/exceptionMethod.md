@ControllerAdvice 是一个专门用于全局异常处理的注解，
并且不需要在 Controller 中显式地调用。
它会自动地应用于所有的 Controller，
当某个 Controller 中抛出异常时，Spring 会自动找到相应的 @ControllerAdvice 类并执行相应的处理逻辑。
只要你的 @ControllerAdvice 配置正确，Spring 会自动处理异常并将其返回给客户端。

如何工作：
1. 全局异常处理：当你在 @ControllerAdvice 中使用 @ExceptionHandler 注解的方法时，它会自动捕获在所有控制器中抛出的异常。

2. 集成到 Controller：你不需要在每个 Controller 中显式地调用 @ControllerAdvice，Spring 会自动处理。只要你的异常类存在并被标记为 @ControllerAdvice，它就会自动生效。
```java


package com.jiuzhang.userMangement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMediaTypeNotSupportedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理415错误（Unsupported Media Type）
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handle415Error(HttpMediaTypeNotSupportedException ex, WebRequest request) {
        String errorMessage = "Unsupported Media Type. Please ensure your Content-Type is correct.";
        return new ResponseEntity<>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // 其他异常处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericError(Exception ex, WebRequest request) {
        String errorMessage = "An unexpected error occurred.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```