package net.martincharlesworth.data;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.BeforeClass;

public abstract class AbstractDBTest {

    private static PropertiesBasedJdbcDatabaseTester databaseTester;

    @BeforeClass
    public static void setUpClass() throws Exception {
        InputStream inputStream = AbstractDBTest.class.getClassLoader().getResourceAsStream("dbunit.properties");

        if (inputStream == null) {
            throw new FileNotFoundException("Property file database.properties not found in the classpath");
        }

        Properties dbProperties = new Properties();
        dbProperties.load(inputStream);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                dbProperties.getProperty("DBUNIT_DRIVER_CLASS"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                dbProperties.getProperty("DBUNIT_CONNECTION_URL"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                dbProperties.getProperty("DBUNIT_USERNAME"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                dbProperties.getProperty("DBUNIT_PASSWORD"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, dbProperties.getProperty("DBUNIT_SCHEMA"));

        databaseTester = new PropertiesBasedJdbcDatabaseTester();
        databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE);

    }

    public void setUp() throws Exception {

        FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();

        IDataSet[] dataSets = new IDataSet[3];
        dataSets[0] = dataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("dbunit/users.xml"));
        dataSets[1] = dataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("dbunit/orders.xml"));
        dataSets[2] = dataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("dbunit/items.xml"));

        CompositeDataSet compositeDataSet = new CompositeDataSet(dataSets);
        getDatabaseTester().setDataSet(compositeDataSet);
        getDatabaseTester().onSetup();
    }

    @After
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    public PropertiesBasedJdbcDatabaseTester getDatabaseTester() {
        return databaseTester;
    }

}