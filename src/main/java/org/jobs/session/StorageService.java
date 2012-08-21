
package org.jobs.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jobs.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class StorageService
{

	private static final Logger	LOG	= LoggerFactory.getLogger( StorageService.class );

	@PersistenceContext( name = "jobs" )
	private EntityManager		entityManager;

	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	public void setEntityManager( EntityManager entityManager )
	{
		this.entityManager = entityManager;
	}

	public void persist( User user )
	{
		LOG.info( "persist entity {}", user );
		entityManager.persist( user );
	}

	public List<User> getList( Class<User> clazz )
	{
		LOG.info( "get list entities {}", clazz );
		final Query query = entityManager.createQuery( "select u from User u" );

		final List<User> result = query.getResultList();
		LOG.info( "list entities {} : {}", clazz, result.size() );

		return result;
	}

	public int sum( int i, int j )
	{
		LOG.info( "sum {} + {}", i, j );
		return i + j;
	}
}
