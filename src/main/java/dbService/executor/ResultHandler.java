package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by seggas on 22.05.17.
 */
public interface ResultHandler<T> {
    T handle (ResultSet resultSet) throws SQLException;
}
