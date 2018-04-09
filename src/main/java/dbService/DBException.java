package dbService;

import practice.CreatedBy;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class DBException extends Exception {
    public DBException(Throwable throwable) {
        super(throwable);
    }
}
