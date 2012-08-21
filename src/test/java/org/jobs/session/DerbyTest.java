
package org.jobs.session;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.DerbyConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.jobs.entity.User;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DerbyTest
{

	// ## DEFINE VARIABLES SECTION ##
	// define the driver to use
	public static String	DRIVER			= "org.apache.derby.jdbc.EmbeddedDriver";

	// the database name
	public static String	DB_NAME			= "jobsDbTest";

	// define the Derby connection URL to use
	public static String	CONNECTION_URL	= "jdbc:derby:memory:" + DB_NAME + ";create=true";

	public static String	DIALECT			= "org.hibernate.dialect.DerbyDialect";

	@BeforeSuite
	public void setup() throws Exception
	{

		// pornesc derby embedded in memory
		final Connection conn = startDerby();

		// pornesc liquibase si actualizez derby pornit embedded in memory
		updateDB( conn );

		conn.close();

	}

	private void updateDB( Connection conn ) throws LiquibaseException
	{

		final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
			new DerbyConnection( conn ) );
		Liquibase liquibase = new Liquibase( "db/changelog-master.xml", new ClassLoaderResourceAccessor(), database );

		liquibase.update( null );

		System.out.println( "data base updated succesfull with liquibase" );
	}

	private Connection startDerby() throws Exception
	{

		// http://stackoverflow.com/questions/1004327/getting-rid-of-derby-log
		// windows
		System.setProperty( "derby.stream.error.file", "\\Device\\Null" );
		// linux
		// System.setProperty( "derby.stream.error.file", "/dev/null" );

		Class.forName( DRIVER );

		final Connection conn = DriverManager.getConnection( CONNECTION_URL );
		System.out.println( "database created, connected to database " + DB_NAME );

		return conn;

	}

	@AfterSuite
	public void teardown() throws Exception
	{
		try {
			DriverManager.getConnection( "jdbc:derby:;shutdown=true" );
		}
		catch( SQLException e ) {
			// expected exception on shutdown
			System.out.println( "derby engine shutdown" );
		}

	}

	@Test
	public void mytest() throws Exception
	{
		System.out.println( " ======== MY TEST running ok" );

		final Connection conn = DriverManager.getConnection( CONNECTION_URL );
		System.out.println( "connected to database " + DB_NAME );

		final Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery( "select current_timestamp from sysibm.sysdummy1" );

		if( rs.next() ) {
			final Date currentTime = rs.getDate( 1 );
			System.out.println( "current time: " + currentTime );
		}
		else {
			System.out.println( "NICI un rezultat" );
		}

		rs = st.executeQuery( "select * from JOBS.USERS" );
		while( rs.next() ) {
			final int id = rs.getInt( 1 );
			final String name = rs.getString( 2 );
			final String username = rs.getString( 3 );
			final String password = rs.getString( 4 );
			User user = new User( id, name, username, password );
			System.out.println( " next user: " + user );
		}

		st.close();

		conn.close();

	}
}
