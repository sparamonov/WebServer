package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import accounts.UserProfile;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public double getUserId(String login) throws HibernateException {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = criteriaBuilder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> usersDataSetRoot = criteria.from(UsersDataSet.class);
        return ((UsersDataSet)criteria.where(criteriaBuilder.equal(usersDataSetRoot.get("login"), login))).getId();
    }

    public long insertUser(UserProfile userProfile) throws HibernateException {
        return (Long) session.save(new UsersDataSet(userProfile));
    }
}