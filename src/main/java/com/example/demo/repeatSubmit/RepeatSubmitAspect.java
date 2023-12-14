package com.example.demo.repeatSubmit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * https://blog.csdn.net/qq_63431773/article/details/133515877
 * https://baijiahao.baidu.com/s?id=1775253703810507135&wfr=spider&for=pc
 * https://blog.csdn.net/m0_37539286/article/details/127292003
 */

@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    // 这是一个环绕通知，它会围绕被 @RepeatSubmit 注解标记的方法执行,这里的 repeatSubmit 与下面的参数对应
    @Around("@annotation(repeatSubmit)")
    public Object around(ProceedingJoinPoint point, RepeatSubmit repeatSubmit) throws Throwable {

        // 获取用户的token验证,这里项目用的是 header里的Authorization 参数
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestToken = request.getHeader("Authorization");
        // 获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 获取类，方法
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        //获取超时时间
        int timeOut = repeatSubmit.timeout();

        // 组装key：用户唯一标识+操作类+方法
        String key = requestToken + "#" + className + "#" + methodName;
        log.info("key:{},超时时间:{}", key, timeOut);

        // 从缓存给中根据key获取数据
        String value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            log.info("重复提交");
            // 如果value不为空; return "请勿重复提交";
            // return new ResponseBean(Constants.CODE_SUCCESS, "重复提交，稍后重试", "");
            throw new IllegalArgumentException("重复提交,稍后重试");
        } else {
            log.info("首次提交");
            // value为空，则加入缓存，并设置过期过期时间
            redisTemplate.opsForValue().set(key, "1", timeOut, TimeUnit.MILLISECONDS);
        }

        //执行Object
        Object object = point.proceed();

        return object;
    }

}
