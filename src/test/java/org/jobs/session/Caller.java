
package org.jobs.session;

import java.util.concurrent.Callable;

public interface Caller
{

	public <V> V call( Callable<V> callable ) throws Exception;
}
