package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Optional;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.excilys.dao.impl.ComputerDaoImpl;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class TestComputerDao extends DatabaseTestCase {

	private Connection connect;
	private FlatXmlDataSet loadedDataSet;
	private ComputerDao computerDao = new ComputerDaoImpl();;

	@Override
	protected IDatabaseConnection getConnection() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db", "root", "root");
		return new DatabaseConnection(connect);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		loadedDataSet = new FlatXmlDataSetBuilder().setCaseSensitiveTableNames(false)
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbunit.xml"));
		return loadedDataSet;
	}

	@Test
	public void test_count() {
		assertEquals(computerDao.countComputer(), 3);
	}
	
	@Test
    public void test_find_by_id_if_not_exist() {
        long id = -1;
        Optional<Computer> computer = computerDao.findById(id);
        assertFalse(computer.isPresent());
    }
	/*
	@Test
    public void test_find_by_id_if_id_exist() {
        long id = 1;
        Optional<Computer> computer = computerDao.findById(id);
        assertTrue(computer.isPresent());
    }*/
	
	@Test
    public void test_add_computer(){

        Computer computer = new Computer.Builder("computerTest")
        		.introduced(LocalDate.of(2015, 02, 10))
        		.disconstinued((LocalDate.of(2016, 02, 10)))
        		.manufacturer(new Company.Builder().name("companyTest").id(1).build())
        		.build();

        int count = (int) computerDao.countComputer();
        boolean res = computerDao.create(computer);
        assertEquals(count + 1, computerDao.countComputer());

    }
	
	


}

