package com.payjunction.onboarding.migration;

import org.apache.ibatis.migration.MigrationScript;

import java.math.BigDecimal;

/**
 * @author bcantello
 * @since 2019-12-17
 */
public class V001CreateChangeLog implements MigrationScript
{
	public BigDecimal getId()
	{
		return BigDecimal.valueOf(1L);
	}

	public String getDescription()
	{
		return "Create changelog";
	}

	public String getUpScript()
	{
		return "CREATE TABLE changelog ("
				+ "ID NUMERIC(20,0) NOT NULL,"
				+ "APPLIED_AT VARCHAR(25) NOT NULL,"
				+ "DESCRIPTION VARCHAR(255) NOT NULL); "

				+ "ALTER TABLE changelog "
				+ "ADD CONSTRAINT PK_changelog "
				+ "PRIMARY KEY (id);";
	}

	@Override
	public String getDownScript()
	{
		return null;
	}
}
