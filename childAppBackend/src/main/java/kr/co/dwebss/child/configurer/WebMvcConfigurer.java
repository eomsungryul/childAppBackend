package kr.co.dwebss.child.configurer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultCode;
import kr.co.dwebss.child.core.ServiceException;

/**
 * 스프링 MVC 배치
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    @Value("${spring.profiles.active}")
    private String env;//현재 활성화 된 
    
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
        	.addResourceLocations("file:ext-resources/")
            .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
    
    
    //FastJson 사용 JSON MessageConverter 사용
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//빈 필드를 비운다
        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        // 요구 사항, 더 많은 정보 FastJson

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }
	

    //통합 예외 처리
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof ServiceException) {//비즈니스 실패 예외, "계정 또는 암호 오류"
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    logger.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("접속url [" + request.getRequestURI() + "] 존재하지않습니다.");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("접속url [" + request.getRequestURI() + "] 내부 오류입니다. 관리자에게 문의하십시오.");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("접속 [%s] 예외가 발생했습니다. 메서드：%s.%s，에러 요약：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    //도메인 간 문제 해결
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/**");
    }
    
    //인터셉터 추가
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        //인터페이스 서명 인증 인터셉터, 서명 인증은 비교적 간단합니다, 실제 프로젝트는 Json Web Token 또는 다른 좋은 방법으로 대체 될 수 있습니다.
        if (!"dev".equals(env)) { //개발 환경에서 서명 인증을 무시합니다.
        	
        	// SessionInterceptor 추가
//            registry.addInterceptor(new SessionInterceptor())
//                    .addPathPatterns("/*")
//                    .excludePathPatterns("/login") //로그인 쪽은 예외처리를 한다.
//                    .excludePathPatterns("/join"); //회원가입 쪽은 예외처리를 한다.
            
//            registry.addInterceptor(new HandlerInterceptorAdapter() {
//                @Override
//                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                    //밸리데이션 체크
//                    boolean pass = validateSign(request);
//                    if (pass) {
//                        return true;
//                    } else {
//                        logger.warn("서명 인증에 실패했습니다. 인터페이스 요청：{}，요청IP：{}，요청매개변수：{}",
//                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
//
//                        Result result = new Result();
//                        result.setCode(ResultCode.UNAUTHORIZED).setMessage("서명 인증에 실패했습니다.");
//                        responseResult(response, result);
//                        return false;
//                    }
//                }
//            });
        }
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 간단한 서명 인증, 규칙 :      
     * 1. ASCII 코드로 요청 매개 변수 정렬      
     * 2. a = value & b = value ... (부호 없음)와 같은 문자열 연결      
     * 3. 비밀 키 (secret)는 md5의 서명을 획득하고이를 요청의 서명과 비교하는 데 사용됩니다.
     */
    private boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getParameter("sign");//검색 요청 서명, 희망sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign");//sign삭제
        Collections.sort(keys);//이탈

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//문자열 연결
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//마지막 하나 삭제'&'

        String secret = "Potato";//키, 자체 수정
        String sign = DigestUtils.md5Hex(linkString + secret);//혼합 키 md5

        return StringUtils.equals(sign, requestSign);//비교
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 다중 레벨 프록시 인 경우 첫 번째 IP를 클라이언트 IP로 사용하십시오.
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
}
