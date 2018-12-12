package accounts;

import dbService.DBException;
import dbService.DBService;

public interface AccountServiceI {
    public void addNewUser(UserProfile userProfile, DBService dbService) throws DBException;
    
    public void removeUser();
    
    public int getUsersLimit();
    
    public void setUsersLimit(int usersLimit);
    
    public int getUsersCount();

    public UserProfile getUserBySessionId(String sessionId);

    public UserProfile getUserByLogin(String login);

    public void addSession(String sessionId, UserProfile profile);

    public void deleteSession(String sessionId);
}