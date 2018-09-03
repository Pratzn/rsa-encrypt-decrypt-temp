package rsa.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.java.Log;

@Log
public class Interceptor implements HandlerInterceptor {

	private static final String AUTH_HEADER = "Authorization";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("===================    Interceptor    ===================");
		String authHeader = getToken(request);

		log.info("authHeader: " + authHeader);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		handlerMethod.getMethod().getAnnotation(Log.class);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	private String getToken(HttpServletRequest request) {

		String authHeader = request.getHeader(AUTH_HEADER);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}

}
