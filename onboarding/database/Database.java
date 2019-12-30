package com.payjunction.onboarding.database;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.migration.DataSourceConnectionProvider;
import org.apache.ibatis.migration.JavaMigrationLoader;
import org.apache.ibatis.migration.operations.UpOperation;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.List;

import javax.inject.Singleton;

/**
 * @author bcantello
 * @since 2019-12-17
 */
@Singleton
public class Database
{
	private final SqlSessionFactory sqlSessionFactory;

	public Database()
	{
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setUrl("jdbc:h2:mem:test");
		dataSource.setDriver("org.h2.Driver");

		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration config = new Configuration(environment);
		config.addMapper(RequestDataMapper.class);

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);

		new UpOperation().operate(
				new DataSourceConnectionProvider(dataSource),
				new JavaMigrationLoader(
						"com.payjunction.onboarding.migration"), null, null);
	}

	public void insertPostData(RequestData requestData)
	{
		SqlSession session = sqlSessionFactory.openSession();
		session.getMapper(RequestDataMapper.class).insertPostData(requestData);
		session.commit();
		session.close();
	}

	public RequestData getByRequestId(String requestId)
	{
		SqlSession session = sqlSessionFactory.openSession();
		RequestData data = session.getMapper(RequestDataMapper.class).getByRequestId(requestId);
		session.close();
		return data;
	}

	public List<RequestData> getAllRequests()
	{
		SqlSession session = sqlSessionFactory.openSession();
		List<RequestData> data = session.getMapper(RequestDataMapper.class).getAll();
		session.close();
		return data;
	}
}
