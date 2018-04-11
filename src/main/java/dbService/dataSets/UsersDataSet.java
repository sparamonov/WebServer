package dbService.dataSets;

import accounts.UserProfile;
import practice.CreatedBy;

import javax.persistence.*;
import java.io.Serializable;

@CreatedBy(author = "Seggas", date = "01.04.18")

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate
    private static final long serialVersionUID = 42L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public UsersDataSet(UserProfile userProfile) {
        this.setLogin(userProfile.getLogin());
        this.setPassword(userProfile.getPass());
        this.setEmail(userProfile.getEmail());
    }

    public UsersDataSet(String login) {
        this.setId(-1);
        this.setLogin(login);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Important to Hibernate
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String name) {
        this.setId(id);
        this.setLogin(login);
    }

    @SuppressWarnings("UnusedDeclaration")
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