
package org.jobs.session;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jobs.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class JobsSessionTest
{

	private static final Logger	LOG	= LoggerFactory.getLogger( JobsSessionTest.class );

	@PersistenceContext
	private EntityManager		entityManager;

	@EJB
	private StorageService		service;

	@EJB
	Caller						caller;

	private DerbyTest			derbyTest;

	@BeforeSuite
	public void setup() throws Exception
	{

		derbyTest = new DerbyTest();

		derbyTest.setup();

		derbyTest.mytest();

		LOG.info( "############### DERBY DB STARTED ##################" );
		LOG.info( "############### DERBY DB STARTED ##################" );
		LOG.info( "############### DERBY DB STARTED ##################" );

		final Properties p = new Properties();
		p.put( DerbyTest.DB_NAME, "new://Resource?type=DataSource" );
		p.put( DerbyTest.DB_NAME + ".JdbcDriver", DerbyTest.DRIVER );
		p.put( DerbyTest.DB_NAME + ".JdbcUrl", DerbyTest.CONNECTION_URL );
		p.put( "jobs.hibernate.dialect", DerbyTest.DIALECT );

		EJBContainer container = EJBContainer.createEJBContainer( p );

		final Context ctx = container.getContext();

		ctx.bind( "inject", this );

		LOG.info( "=========== STARTED ===========" );

	}

	@AfterSuite
	public void teardown() throws Exception
	{
		derbyTest.teardown();
	}

	@Test
	public void sum() throws Exception
	{
		final int sum = service.sum( 2, 5 );

		LOG.info( "sum result {}", sum );

		Assert.assertNotNull( entityManager );

		final List<User> listUsers = caller.call( new Callable<List<User>>()
		{

			@Override
			public List<User> call() throws Exception
			{
				return service.getList( User.class );
			}
		} );

		LOG.info( "list users {}", listUsers.size() );

		LOG.info( "executing NATIVE QUERY" );

		final Query nativeQuery = entityManager.createNativeQuery( "select user0_.id as id1_, user0_.name as name1_, "
			+ "user0_.password as password1_, user0_.username as username1_ from JOBS.Users user0_" );

		final List results = nativeQuery.getResultList();
		for( int i = 0; i < results.size(); i++ ) {
			final Object rand = results.get( i );
			LOG.info( "rand " + rand );
		}

		LOG.info( "===============================================" );

	}
}
