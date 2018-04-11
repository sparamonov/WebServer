package dbService.dao;

import accounts.UserProfile;
import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import practice.CreatedBy;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import java.sql.Connection;
import java.sql.SQLException;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = criteriaBuilder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> usersDataSetRoot = criteria.from(UsersDataSet.class);
        return ((UsersDataSet)criteria.where(criteriaBuilder.equal(usersDataSetRoot.get("name"), name))).getId();
    }

    /*public long insertUser(String name) throws HibernateException {
        return (Long)session.save(new UsersDataSet(name));
    }*/

    public long insertUser(UserProfile userProfile) throws HibernateException {
        return (Long)session.save(new UsersDataSet(userProfile));
    }
}