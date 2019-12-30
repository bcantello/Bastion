package com.payjunction.onboarding.server;

import com.payjunction.onboarding.database.Database;
import com.payjunction.onboarding.database.RequestData;

import org.slf4j.MDC;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

/**
 * @author bcantello
 * @since 2019-12-17
 */
class RequestLogFilter implements ContainerRequestFilter
{
	@Inject Database database;

	@Override
	public void filter(ContainerRequestContext requestContext)
	{
		String requestId = MDC.get("req.id");
		String requestAction = requestContext.getMethod();
		RequestData requestData = new RequestData(requestId, requestAction);
		database.insertPostData(requestData);
	}
}
