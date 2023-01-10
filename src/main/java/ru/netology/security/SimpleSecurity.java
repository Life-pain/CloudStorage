package ru.netology.security;

import ru.netology.entities.User;
import ru.netology.exception.AuthorizationException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleSecurity {
    private static final String EXCEPTION_MESSAGE = "Токен не прошел проверку";
    private static final ConcurrentHashMap<String, User> usersAuthorized = new ConcurrentHashMap<>();

    public static Map<String, User> getUsersAuthorized() {
        return usersAuthorized;
    }

    public static String getOwnerByToken(String authToken) {
        String owner;
        try {
            owner = usersAuthorized.get(authToken.substring(7)).getLogin();
        } catch (NullPointerException e) {
            throw new AuthorizationException(EXCEPTION_MESSAGE);
        } catch (IndexOutOfBoundsException exp) {
            throw new AuthorizationException(EXCEPTION_MESSAGE);
        }
        return owner;
    }
}
