
package org.jobs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "USERS" )
public class User
implements Serializable
{

	private static final long	serialVersionUID	= 3547162450980863160L;

	@Id
	private Integer				id;

	private String				name;

	private String				username;

	private String				password;

	public User()
	{
	}

	public User( Integer id, String name, String username, String password )
	{
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		User other = (User) obj;
		if( id == null ) {
			if( other.id != null )
				return false;
		}
		else if( !id.equals( other.id ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "User [id=%s, name=%s, username=%s, password=%s]", id, name, username, password );
	}

}
