package com.spring.mvc_hibernate_aop.dao;

import com.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
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
    @Transactional
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
}


























