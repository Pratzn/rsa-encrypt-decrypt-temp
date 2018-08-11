package rsa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.java.Log;

@Log
@WebFilter(filterName = "tokenAuthenticationFilter", urlPatterns = "/*")
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String AUTH_HEADER="Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		log.info("===================    TokenAuthenticationFilter    ===================");
		String authHeader = getToken(request);
		
		log.info("authHeader: "+authHeader);
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name());
		
		
		if("".equals(""))
			return;
	    chain.doFilter(request, response);
		
	}
	
	private String getToken( HttpServletRequest request ) {

	    String authHeader = request.getHeader(AUTH_HEADER);
	    if ( authHeader != null && authHeader.startsWith("Bearer ")){
	        return authHeader.substring(7);
	    }

	    return null;
	}

}
