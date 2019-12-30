package com.payjunction.onboarding.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author bcantello
 * @since 2019-12-05
 */
public class RequestIdAndIpFilter implements Filter
{
	private final Logger log = LoggerFactory.getLogger(RequestIdAndIpFilter.class);

	@Override
	public void init(FilterConfig filterConfig)
	{
		// Intentionally empty
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException
	{
		try
		{
			MDC.put("req.ip", request.getRemoteAddr());
			MDC.put("req.id", UUID.randomUUID().toString());

			filterChain.doFilter(request,response);
			log.info("request processed");
		}
		finally
		{
			MDC.remove("req.ip");
			MDC.remove("req.id");
		}
	}

	@Override
	public void destroy()
	{
		//Intentionally Empty
	}
}
