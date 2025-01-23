package com.ljubinkovicdj93.countdown.repositories;

import com.ljubinkovicdj93.countdown.model.GameSession;
import com.ljubinkovicdj93.countdown.model.SecretKeyType;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class GameSessionRepositoryImpl implements GameSessionRepository {
    // TODO: Store in database
    private final ConcurrentMap<String, GameSession> joinKeys = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, GameSession> watchKeys = new ConcurrentHashMap<>();

    @Override
    public GameSession getGameSession(String key) {
        if (key.startsWith(SecretKeyType.JOIN_KEY.name())) {
            return joinKeys.get(key);
        } else {
            return watchKeys.get(key);
        }
    }

    @Override
    public String storeSecret(SecretKeyType key, GameSession gameSession) {
        String encrypted = key.name() + generateSecretAccessToken();

        switch (key) {
            case JOIN_KEY -> {
                joinKeys.put(encrypted, gameSession);
            }
            case WATCH_KEY -> {
                watchKeys.put(encrypted, gameSession);
            }
        }

        return encrypted;
    }

    private String generateSecretAccessToken() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            byte[] randomBytes = new byte[32];
            secureRandom.nextBytes(randomBytes);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(randomBytes);

            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating secure token", e);
        }
    }
}
