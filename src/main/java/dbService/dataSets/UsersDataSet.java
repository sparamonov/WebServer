package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by seggas on 15.05.17.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    // Important to Hibernate
    public UsersDataSet(String name) {
        this.id = 1;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UsersDataSet{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
