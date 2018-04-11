package accounts;

import dbService.DBException;
import dbService.DBService;
import practice.CreatedBy;

import java.util.HashMap;
import java.util.Map;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }


    public void addNewUser (UserProfile userProfile, DBService dbService) {
        try {
            dbService.addUser(userProfile);
        } catch (DBException ex) {
            ex.printStackTrace();
        }
    }

    //public void addNewUser(UserProfile userProfile) {loginToProfile.put(userProfile.getLogin(), userProfile); }


    public UserProfile getUserByLogin(String login) { return loginToProfile.get(login); }

    public void addSession (String sessionId, UserProfile userProfile) { sessionIdToProfile.put(sessionId, userProfile); }

    public UserProfile getUserBySessionId(String sessionId) { return sessionIdToProfile.get(sessionId); }

    public void deleteSession(String sessionId) { sessionIdToProfile.remove(sessionId); }
}