package com.rrtyui.util;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.exception.IncorrectNameException;
import com.rrtyui.exception.IncorrectUUIDException;

import java.util.UUID;

public class Validation {
    private static final String REG_EXP = "^[А-Яа-я]+(?:[ .][А-Яа-я]+)*$";
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;

    public static void validate(String... names) {
        for (String name : names) {
            nullOrBlank(name);
            checkNameLength(name);
            containsOnlyLetters(name);
        }
    }
    public static void validateUUID(String uuid, MatchScoreModel matchScoreModel) {
        if (matchScoreModel == null) {
            throw new IncorrectUUIDException("Некорректный UUID: " + uuid);
        }
        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IncorrectUUIDException("Некорректный UUID: " + uuid);
        }
    }

    private static void nullOrBlank(String playerName)  {
        if (playerName == null || playerName.isBlank()) {
            throw new IncorrectNameException("Имя игрока не может быть пустым");
        }
    }

    private static void checkNameLength (String playerName) {
        if (playerName.length() < MIN_LENGTH || playerName.length() > MAX_LENGTH) {
            throw new IncorrectNameException(
                    "Дядь, ну не гони. Длина от %s до %s"
                            .formatted(MIN_LENGTH, MAX_LENGTH));
        }
    }

    private static void containsOnlyLetters(String playerName) {
        if (!playerName.matches(REG_EXP)) {
            throw new IncorrectNameException("Имя игрока должно содержать русские буквы");
        }
    }
}
