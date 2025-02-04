package com.rrtyui.repository;

import com.rrtyui.entity.Player;
import jakarta.persistence.EntityManager;

public class PlayerRepository extends CrudRepository<Long, Player> {
    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }


}
