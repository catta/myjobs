
package org.jobs.session;

import java.util.concurrent.Callable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute( TransactionAttributeType.REQUIRES_NEW )
public class TransactionBean
implements Caller
{

	@Override
	public <V> V call( Callable<V> callable ) throws Exception
	{
		return callable.call();
	}

}
