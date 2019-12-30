package com.payjunction.onboarding.database;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bcantello
 * @since 2019-12-17
 */
interface RequestDataMapper
{
	@Select("SELECT * FROM RequestData")
	List<RequestData> getAll();

	@Select("SELECT * FROM RequestData WHERE requestId = #{requestId}")
	RequestData getByRequestId(String requestId);

	@Insert("INSERT INTO RequestData(TimeStamp, RequestId, RequestAction) "
			+ "VALUES(#{timeStamp}, #{requestId}, #{requestAction})")
	void insertPostData(RequestData requestData);
}
