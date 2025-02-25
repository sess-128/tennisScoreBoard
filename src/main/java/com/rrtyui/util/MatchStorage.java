package com.rrtyui.util;

import com.rrtyui.dto.MatchScoreModel;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchStorage {
    private static final Map<UUID, MatchScoreModel> ongoingMatches = new ConcurrentHashMap<>();

    public static void addMatch(UUID uuid, MatchScoreModel match) {
        ongoingMatches.put(uuid, match);
    }

    public static MatchScoreModel getMatch(String uuid) {
        return ongoingMatches.get(UUID.fromString(uuid));
    }

    public static void removeMatch(String uuid) {
        ongoingMatches.remove(UUID.fromString(uuid));
    }

}
