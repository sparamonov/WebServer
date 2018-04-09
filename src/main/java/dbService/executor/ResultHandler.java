package dbService.executor;

import practice.CreatedBy;

import java.sql.ResultSet;
import java.sql.SQLException;

@CreatedBy(author = "Seggas", date = "01.04.18")

public interface ResultHandler<T> {
    T handle (ResultSet resultSet) throws SQLException;
}
