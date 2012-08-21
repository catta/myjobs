
package org.jobs.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jobs.entity.User;
import org.jobs.session.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ViewScoped
@ManagedBean
public class UsersBean
implements Serializable
{

	private static final Logger	LOG					= LoggerFactory.getLogger( UsersBean.class );

	@EJB
	private PersistenceService	service;

	private List<User>			listUsers;

	private static final long	serialVersionUID	= 7746415883349155686L;

	@PostConstruct
	public void init()
	{
		LOG.info( "init - list users" );
		this.listUsers = service.getList( User.class );

	}

	public List<User> getListUsers()
	{
		return this.listUsers;
	}
}
