package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by seggas on 15.05.17.
 */
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

    public long insertUser(String name) throws HibernateException {
        return (Long)session.save(new UsersDataSet(name));
    }

    /*private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2));
        });
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String name) throws SQLException {
        executor.execUpdate("insert into users (user_name) values ('" + name + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }*/
}
