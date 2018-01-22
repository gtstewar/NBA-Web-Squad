package com.dynamic.duo.model.persistent;

import com.dynamic.duo.utils.DomainObjectCache;
import com.dynamic.duo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "players", schema = "nba")
public class Player extends DomainObject<Player> implements Serializable {

    /**cache of player objects*/
    static private DomainObjectCache<Long, Player> cache = new DomainObjectCache<>( Player.class );

    /**
     * fields of player table
     */
    @NotEmpty
    @Column(name = "player_name")
    private String player_name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    private Long player_id;

    //getters and setters for columns

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

    //DAO methods for CRUD Operations

    /**
     * gets player based on id from table
     * @param id - id of player
     * @return - player with id given
     */
    public static Player getByID(long id){
        Player player = cache.get(id);
        if(player == null) {
            try {
                final Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                //parameterized where clause
                String whereClause = " player_id = :id";
                //whole query
                String query = "FROM Player WHERE" + whereClause;
                //create HQL query in session after retrieving from sessionFactory
                player = (Player) session
                        .createQuery(query).setParameter("id", id).list().get(0);
                session.getTransaction().commit();
                session.close();
                //put in cache since it wasnt in cache originally
                cache.put(id, player);
            }catch(Exception e){
                //catch all exceptions
            }
        }
        return player;
    }

    public static long getIDFromName(String name){
        Player player = null;
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //parameterized where clause
            String whereClause = " player_name = (:name)";
            //whole query
            String query = "FROM Player WHERE" + whereClause;
            //create HQL query in session after retrieving from sessionFactory
            player = (Player) session
                    .createQuery(query).setParameter("name", name).list().get(0);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            //Do nothing
        }
        if(player != null){
            return player.getId();
        }
        return -1;
    }

}
