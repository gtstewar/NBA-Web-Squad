package com.dynamic.duo.model.persistent;

import com.dynamic.duo.utils.DomainObjectCache;
import com.dynamic.duo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "teams", schema = "nba")
public class Team extends DomainObject<Team> implements Serializable {

    /**cache of Team objects*/
    static private DomainObjectCache<Long, Team> cache = new DomainObjectCache<>( Team.class );

    //DAO methods for CRUD Operations

    /**
     * gets team based on id from table
     * @param id - id of team
     * @return - team with id given
     */
    public static Team getByID(long id){
        Team team = cache.get(id);
        if(team == null) {
            try {
                final Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                //parameterized where clause
                String whereClause = " team_id = :id";
                //whole query
                String query = "FROM Team WHERE" + whereClause;
                //create HQL query in session after retrieving from sessionFactory
                team = (Team) session
                        .createQuery(query).setParameter("id", id).list().get(0);
                session.getTransaction().commit();
                session.close();
                //put in cache since it wasnt in cache originally
                cache.put(id, team);
            }catch(Exception e){
                //catch all exceptions
            }
        }
        return team;
    }

    /**
     * get team's primary key from their name
     * @param name - of team
     * @return id of team or -1 if team doesn't exist
     */
    public static long getIDFromName(String name){
        Team team = null;
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //parameterized where clause
            String whereClause = " team_name = (:name)";
            //whole query
            String query = "FROM Team WHERE" + whereClause;
            //create HQL query in session after retrieving from sessionFactory
            team = (Team) session
                    .createQuery(query).setParameter("name", name).list().get(0);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            //Do nothing
        }
        if(team != null){
            return team.getId();
        }
        return -1;
    }

    /**
     * fields of team table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long team_id;

    private String city;
    private String alt_city;
    private String full_name;
    private String abb;
    private String nickname;
    private String conference;
    private String division;
    private int is_current_team;
    private int is_historic_team;
    private int nba_team_id;


    //getters and setters for columns

    public Long getId() {
        return Long.valueOf(team_id);
    }

    public void setId(Long id) {
        this.team_id = id;
    }

    public String getName() {
        return full_name;
    }

    public void setName(String name) {
        this.full_name = name;
    }

}
