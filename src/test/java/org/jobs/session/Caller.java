
package org.jobs.session;

import java.util.concurrent.Callable;

import javax.ejb.Local;

@Local
public interface Caller
{

	public <V> V call( Callable<V> callable ) throws Exception;
}
