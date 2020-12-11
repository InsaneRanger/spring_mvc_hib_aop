package com.spring.mvc_hibernate_aop.dao;

import com.spring.mvc_hibernate_aop.entity.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeDAOimpl.class);
    /**
     * In order to be able to connect to a session, the date
     * must have access to the session factory.
     * Sessionfactory we prescribed in applicationContext.xml
     * bean id sessionFactory.
     * SessionFactory bean depends on the source date where
     * we specify dependencies. Bean datasource contains all
     * parameters for connecting to the database
     *
     * We are implementing a dependency on sessionfactory
     * Bean with field of class and @Autowired.
     *
     * Add @Transactional to method to manage the opening
     * and closing of a transaction
     *
     * */

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        Session session =sessionFactory.getCurrentSession();
        List<Employee> allEmployees =session.createQuery("" +
                "from Employee",Employee.class).getResultList();

//        Query<Employee> query =
//                session.createQuery("" +
//                        "from Employee",Employee.class);
//        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);


        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("delete from Employee  " +
                "where id =:employeeId");

        query.setParameter("employeeId",id);
        query.executeUpdate(); // for delete we use execute + update
    }
}





































