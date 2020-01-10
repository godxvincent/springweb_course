package com.godxvincent.springweb.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.godxvincent.springweb.repository.LogRepository;

@Component("requestTimeInterceptor") 
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
	
	
	@Autowired
	@Qualifier("logRepository")
	LogRepository logRepository; 
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		//super.afterCompletion(request, response, handler, ex);
		long startTime = (long) request.getAttribute("startTime");
		
		String url = request.getRequestURL().toString() ;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = null;
		String details = null;
		if (auth != null && auth.isAuthenticated()) {
			username = auth.getName();
			details = auth.getDetails().toString();
		}
		logRepository.save(new com.godxvincent.springweb.entity.Log(new Date(), details, username , url ));
		LOGGER.info("Url to: '" + url + "' in: '" + (System.currentTimeMillis() - startTime)+"ms'");
	}
	
	

}
