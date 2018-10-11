package accounts;

import java.util.HashMap;
import java.util.Map;
import dbService.DBException;
import dbService.DBService;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    
    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile, DBService dbService) throws DBException {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        dbService.addUser(userProfile);
    }

    public UserProfile getUserByLogin(String login) { return loginToProfile.get(login); }

    public void addSession (String sessionId, UserProfile userProfile) { sessionIdToProfile.put(sessionId, userProfile); }

    public UserProfile getUserBySessionId(String sessionId) { return sessionIdToProfile.get(sessionId); }

    public void deleteSession(String sessionId) { sessionIdToProfile.remove(sessionId); }
}