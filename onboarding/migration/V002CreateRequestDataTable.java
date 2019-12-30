package com.payjunction.onboarding.migration;

import org.apache.ibatis.migration.MigrationScript;

import java.math.BigDecimal;

/**
 * @author bcantello
 * @since 2019-12-17
 */
public class V002CreateRequestDataTable implements MigrationScript
{
	@Override
	public BigDecimal getId()
	{
		return BigDecimal.valueOf(2L);
	}

	@Override
	public String getDescription()
	{
		return "Create RequestData";
	}

	@Override
	public String getUpScript()
	{
		return "CREATE TABLE REQUESTDATA ("
				+ "id int auto_increment primary key, "
				+ "timeStamp varchar(100), "
				+ "requestId varchar(100), "
				+ "requestAction varchar(100));";
	}

	@Override
	public String getDownScript()
	{
		return null;
	}
}
