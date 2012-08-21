
package org.jobs.session;

import java.util.List;
import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.openejb.api.LocalClient;
import org.jobs.AbstractPUTest;
import org.jobs.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@LocalClient
public class JobsSessionTest
extends AbstractPUTest
{

	private static final Logger	LOG	= LoggerFactory.getLogger( JobsSessionTest.class );

	@PersistenceContext
	private EntityManager		entityManager;

	@EJB
	private StorageService		service;

	@EJB
	Caller						caller;

	@BeforeClass
	public void setupClass() throws Exception
	{

		LOG.info( "bind test {}", JobsSessionTest.class );
		new InitialContext().bind( "inject", this );
	}

	@Test
	public void sum1() throws Exception
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

		LOG.info( "=============================================== 1" );

	}

	@Test
	public void sum2() throws Exception
	{

		final List<User> listUsers = caller.call( new Callable<List<User>>()
		{

			@Override
			public List<User> call() throws Exception
			{
				return service.getList( User.class );
			}
		} );

		LOG.info( "list users {}", listUsers.size() );

		LOG.info( "=============================================== 2" );

	}

}
