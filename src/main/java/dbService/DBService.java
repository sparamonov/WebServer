package dbService;

import accounts.UserProfile;

public interface DBService {
    public void printConnectInfo();
    public long addUser(UserProfile userProfile) throws DBException;
}