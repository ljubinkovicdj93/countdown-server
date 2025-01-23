package com.ljubinkovicdj93.countdown.repositories;

import com.ljubinkovicdj93.countdown.model.GameSession;
import com.ljubinkovicdj93.countdown.model.SecretKeyType;

public interface GameSessionRepository {
    GameSession getGameSession(String key);
    // TODO: Should be void, but need to get the secret
    String storeSecret(SecretKeyType key, GameSession gameSession);
}
