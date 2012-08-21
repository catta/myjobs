
package org.jobs;

import java.util.Properties;

import org.jobs.session.DerbyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Base class for all TestNG unit test with persistence. Start emebedded OpenEJB container.
 * 
 * @author catalin.croitoru
 * 
 */
public abstract class AbstractPUTest
{

	private static final Logger	LOG	= LoggerFactory.getLogger( AbstractPUTest.class );

	private DerbyTest			derbyTest;

	protected Properties		p;

	@BeforeSuite
	public void setupBase() throws Exception
	{

		LOG.info( "SETUP BASE TEST" );
		derbyTest = new DerbyTest();

		derbyTest.setup();

		derbyTest.mytest();

		LOG.info( "############### DERBY DB STARTED ##################" );
		LOG.info( "############### DERBY DB STARTED ##################" );
		LOG.info( "############### DERBY DB STARTED ##################" );

		p = new Properties();
		p.put( DerbyTest.DB_NAME, "new://Resource?type=DataSource" );
		p.put( DerbyTest.DB_NAME + ".JdbcDriver", DerbyTest.DRIVER );
		p.put( DerbyTest.DB_NAME + ".JdbcUrl", DerbyTest.CONNECTION_URL );
		p.put( "jobs.hibernate.dialect", DerbyTest.DIALECT );

	}

	@AfterSuite
	public void teardown() throws Exception
	{
		derbyTest.teardown();
	}

}
