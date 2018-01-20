package com.dynamic.duo.model.persistent;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "players" )
public class Player extends DomainObject implements Serializable {
    @Id
    @Column
    private Long player_id;
    @NotEmpty
    @Column
    private String player_name;

    public Long getId() {
        return player_id;
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
