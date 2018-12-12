package accounts;

import java.util.HashMap;
import java.util.Map;
import dbService.DBException;
import dbService.DBService;

public class AccountService implements AccountServiceI {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private int usersCount;
    private int usersLimit;
    
    public AccountService(int usersLimit) {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        this.usersCount = 0;
        this.usersLimit = usersLimit;
    }
    @Override
    public void addNewUser(UserProfile userProfile, DBService dbService) throws DBException {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        dbService.addUser(userProfile);
        usersCount += 1;
    }

    @Override
    public void removeUser() {
        usersCount -= 1;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }

    public UserProfile getUserByLogin(String login) { return loginToProfile.get(login); }

    public void addSession (String sessionId, UserProfile userProfile) { sessionIdToProfile.put(sessionId, userProfile); }

    public UserProfile getUserBySessionId(String sessionId) { return sessionIdToProfile.get(sessionId); }

    public void deleteSession(String sessionId) { sessionIdToProfile.remove(sessionId); }
}