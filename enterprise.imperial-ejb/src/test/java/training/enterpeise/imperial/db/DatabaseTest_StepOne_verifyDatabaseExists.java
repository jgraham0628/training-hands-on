package training.enterpeise.imperial.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test to validate that the first step of the development process is completed correctly. 
 * 
 * The test case will execute against the database specified in the jdbcConnection variable. It will check 
 * to ensure that a table named FACILITY has been properly created.
 * @author Jonathan Graham
 */
class DatabaseTest_StepOne_verifyDatabaseExists {
	private Connection jdbcConnection;

	/**
	 * Setup the driver required to access the database.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		try {
			//Loads the class driver needed to make the connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//Establishes the connection to the database using the credentials provided
			jdbcConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/empire", "root", "JediMaster2100!");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close open connections after each test case.
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		jdbcConnection.close();
	}

	/**
	 * Test that the FACILITY table is correctly created. Before starting on the first tasking this test case should fail. Once 
	 * SQL is written and executed against the database this test case should pass.
	 */
	@Test
	void test_tableFacilityExists() {
		try {
			ResultSet results = jdbcConnection.createStatement()
					.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'FACILITY'");
			Assertions.assertTrue(results.next());
			Assertions.assertEquals("FACILITY", results.getString("TABLE_NAME").toUpperCase());
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
