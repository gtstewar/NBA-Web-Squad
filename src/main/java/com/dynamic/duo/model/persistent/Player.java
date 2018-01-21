package com.dynamic.duo.model.persistent;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players", schema = "nba")
public class Player extends DomainObject<Player> implements Serializable {

    @NotEmpty
    @Column(name = "player_name")
    private String player_name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    private Long player_id;

    public Long getId() {
        return Long.valueOf(player_id);
    }

    public void setId(Long id) {
        this.player_id = id;
    }

    public String getName() {
        return player_name;
    }

    public void setName(String name) {
        this.player_name = name;
    }
}
