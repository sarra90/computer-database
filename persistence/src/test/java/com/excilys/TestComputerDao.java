package com.excilys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.dao.ComputerDao;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class TestComputerDao extends DatabaseTestCase {
    
    private Connection connect;
    private FlatXmlDataSet loadedDataSet;
    
    @Autowired
    ComputerDao computerDao;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://172.18.0.2:3306/computer-database-db", "admincdb",
                "qwerty1234");
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
        assertEquals(3, computerDao.count());
    }

    @Test
    public void test_find_by_id_if_not_exist() {
        long id = -1;
        Assert.assertFalse(computerDao.exists(id));
    }

    @Test
    public void test_find_by_id_if_id_exist() {
        long id = 1;
        assertTrue(computerDao.exists(id));
    }
    
      @Test public void test_add_computer(){
      
      Computer computer = new Computer.Builder("computerTest")
      .introduced(LocalDate.of(2015, 02, 10))
      .disconstinued((LocalDate.of(2016, 02, 10))) .manufacturer(new
      Company.Builder().name("companyTest").id(1).build()) .build();
      
      int count = (int) computerDao.count(); 
      computerDao.save(computer);
      assertEquals(count + 1,computerDao.count());
      
      }
     

}
