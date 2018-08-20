package rsa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.java.Log;

@Log
@WebFilter(urlPatterns="/greeting")
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String AUTH_HEADER="Authorization";
	

	@Autowired
	private AntPathMatcher pathMatcher;

	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws   ServletException, IOException {
		log.info("===================    TokenAuthenticationFilter    ===================");
		String authHeader = getToken(request);
		
		log.info("authHeader: "+authHeader);
		
//		response.setStatus(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.name());
//		
//		
//		if("".equals(""))
//			return;
	    chain.doFilter(request, response);
		
	}
	
	private String getToken( HttpServletRequest request ) {

	    String authHeader = request.getHeader(AUTH_HEADER);
	    if ( authHeader != null && authHeader.startsWith("Bearer ")){
	        return authHeader.substring(7);
	    }

	    return null;
	}
	
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		// TODO Auto-generated method stub
//		log.info("servlet context: "+request.getServletPath());
//		log.info("servlet path: "+request.getServletPath());
//		
//		return excludeUrlPatterns.stream()
//				.peek(System.out::println)
//		        .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
//	}

}
