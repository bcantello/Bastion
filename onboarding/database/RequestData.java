package com.payjunction.onboarding.database;

import java.time.LocalDateTime;

/**
 * @author bcantello
 * @since 2019-12-17
 */
public class RequestData
{
	private final Long id;
	private final LocalDateTime timeStamp;
	private final String requestId;
	private final String requestAction;

	public RequestData(String requestId, String requestAction)
	{
		this(null, LocalDateTime.now(), requestId, requestAction);
	}

	/**
	 * This is used by myBatis when reading from the database.
	 */
	private RequestData(Long id, LocalDateTime timeStamp, String requestId, String requestAction)
	{
		this.id=id;
		this.timeStamp = timeStamp;
		this.requestId = requestId;
		this.requestAction = requestAction;
	}

	public Long getId()
	{
		return id;
	}

	public String getRequestId()
	{
		return requestId;
	}

	public LocalDateTime getTimeStamp()
	{
		return timeStamp;
	}

	public String getRequestAction()
	{
		return requestAction;
	}

	@Override
	public String toString()
	{
		return "RequestData{" + "id=" + id + ", TimeStamp=" + timeStamp + ", RequestId=" + requestId
				+ ", RequestAction=" + requestAction + "}";
	}
}
