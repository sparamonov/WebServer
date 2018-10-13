package dbService.dataSets;

import javax.persistence.*;
import accounts.UserProfile;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate
    private static final String UNUSED_DECLARATION = "unused";
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String pass;

    public UsersDataSet(UserProfile userProfile) {
        this.login = userProfile.getLogin();
        this.pass = userProfile.getPass();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @SuppressWarnings(UNUSED_DECLARATION)
    public UsersDataSet() {
    }

    @SuppressWarnings(UNUSED_DECLARATION)
    public UsersDataSet(long id, String login) {
        this.id = id;
        this.login = login;
    }

    @SuppressWarnings(UNUSED_DECLARATION)
    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "UserDataSet{"
                + "id=" + id
                + ", login='" + login + '\''
                + '}';
    }
}