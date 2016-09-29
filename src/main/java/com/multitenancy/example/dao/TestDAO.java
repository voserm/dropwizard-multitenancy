package com.multitenancy.example.dao;

import com.google.inject.Inject;
import com.multitenancy.example.core.Test;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TestDAO extends AbstractDAO<Test> {

    @Inject
    public TestDAO(SessionFactory factory) {
        super(factory);
    }

    public long create(Test test){
        return persist(test).getId();
    }

    public List<Test> findAll() {
        return currentSession().
                createCriteria(Test.class).
                list();
    }

    public Test findById(Long runId) {
        return (Test) currentSession().
                createCriteria(Test.class).
                add(Restrictions.eq("id", runId)).
                uniqueResult();
    }
}

