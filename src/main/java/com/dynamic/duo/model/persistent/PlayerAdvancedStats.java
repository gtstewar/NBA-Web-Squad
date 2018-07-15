package com.dynamic.duo.model.persistent;

import com.dynamic.duo.utils.DomainObjectCache;
import com.dynamic.duo.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_advanced_stats")
public class PlayerAdvancedStats extends DomainObject<PlayerAdvancedStats> implements Serializable {

    /**cache of player objects*/
    static private DomainObjectCache<Long, PlayerAdvancedStats> cache = new DomainObjectCache<>( PlayerAdvancedStats.class );

    /**
     * returns an advanced Player stats object after being provided the primary key
     * @param id - pkey
     * @return - an instance representing the current db state for requested advanced stats
     */
    public static PlayerAdvancedStats getByID(Long id){
        PlayerAdvancedStats stats = cache.get(id);
        if(stats == null) {
            try {
                final Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                //parameterized where clause
                String whereClause = " id = :id";
                //whole query
                String query = "FROM PlayerAdvancedStats WHERE" + whereClause;
                //create HQL query in session after retrieving from sessionFactory
                stats = (PlayerAdvancedStats) session
                        .createQuery(query).setParameter("id", id).list().get(0);
                session.getTransaction().commit();
                session.close();
                //put in cache since it wasnt in cache originally
                cache.put(id, stats);
            }catch(Exception e){
                //catch all exceptions
            }
        }
        return stats;
    }

    /**
     * returns a hibernate-mapped representation of a players current advanced states based on foreign key lookup
     * @param id - foreign key associated with the player to lookup
     * @return - ADvancedPlayerStats hibernate POJO
     */
    public static PlayerAdvancedStats getByPlayerID(Long id){
        PlayerAdvancedStats stats = null;
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //parameterized where clause
            String whereClause = " player_id = :id";
            //whole query
            String query = "FROM PlayerAdvancedStats WHERE" + whereClause;
            //create HQL query in session after retrieving from sessionFactory
            stats = (PlayerAdvancedStats) session
                    .createQuery(query).setParameter("id", id).list().get(0);
            session.getTransaction().commit();
            session.close();
            //put in cache since it wasnt in cache originally
        } catch (Exception e) {
            //catch all exceptions
        }
        return stats;
    }

    @Override
    public String toString(){
        return off_rating + " " + def_rating + " " + net_rating + " " + ast_pct + " " + ast_rat + " " + oreb_pct;
    }

    /**
     * primary key of table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "advanced_player_stats_id")
    private Long id;

    /**
     * foreign key mapping to player associated with stats
     */
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    /**
     * stats columns
     */
    private String player_name;
    private String team_ab;
    private float off_rating;
    private float def_rating;
    private float net_rating;
    private float ast_pct;
    private float ast_to;
    private float ast_rat;
    private float oreb_pct;
    private float dreb_pct;
    private float reb_pct;
    private float tm_tov_pct;
    private float efg_pct;
    private float ts_pct;
    private float usg_pct;
    private float pace;
    private float pie;

    /**
     * Getters and setters
     */

    @Override
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static DomainObjectCache<Long, PlayerAdvancedStats> getCache() {
        return cache;
    }

    public static void setCache(DomainObjectCache<Long, PlayerAdvancedStats> cache) {
        PlayerAdvancedStats.cache = cache;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getTeam_ab() {
        return team_ab;
    }

    public void setTeam_ab(String team_ab) {
        this.team_ab = team_ab;
    }

    public float getOff_rating() {
        return off_rating;
    }

    public void setOff_rating(float off_rating) {
        this.off_rating = off_rating;
    }

    public float getDef_rating() {
        return def_rating;
    }

    public void setDef_rating(float def_rating) {
        this.def_rating = def_rating;
    }

    public float getNet_rating() {
        return net_rating;
    }

    public void setNet_rating(float net_rating) {
        this.net_rating = net_rating;
    }

    public float getAst_pct() {
        return ast_pct;
    }

    public void setAst_pct(float ast_pct) {
        this.ast_pct = ast_pct;
    }

    public float getAst_to() {
        return ast_to;
    }

    public void setAst_to(float ast_to) {
        this.ast_to = ast_to;
    }

    public float getAst_rat() {
        return ast_rat;
    }

    public void setAst_rat(float ast_rat) {
        this.ast_rat = ast_rat;
    }

    public float getOreb_pct() {
        return oreb_pct;
    }

    public void setOreb_pct(float oreb_pct) {
        this.oreb_pct = oreb_pct;
    }

    public float getDreb_pct() {
        return dreb_pct;
    }

    public void setDreb_pct(float dreb_pct) {
        this.dreb_pct = dreb_pct;
    }

    public float getReb_pct() {
        return reb_pct;
    }

    public void setReb_pct(float reb_pct) {
        this.reb_pct = reb_pct;
    }

    public float getTm_tov_pct() {
        return tm_tov_pct;
    }

    public void setTm_tov_pct(float tm_tov_pct) {
        this.tm_tov_pct = tm_tov_pct;
    }

    public float getEfg_pct() {
        return efg_pct;
    }

    public void setEfg_pct(float efg_pct) {
        this.efg_pct = efg_pct;
    }

    public float getTs_pct() {
        return ts_pct;
    }

    public void setTs_pct(float ts_pct) {
        this.ts_pct = ts_pct;
    }

    public float getUsg_pct() {
        return usg_pct;
    }

    public void setUsg_pct(float usg_pct) {
        this.usg_pct = usg_pct;
    }

    public float getPace() {
        return pace;
    }

    public void setPace(float pace) {
        this.pace = pace;
    }

    public float getPie() {
        return pie;
    }

    public void setPie(float pie) {
        this.pie = pie;
    }

}
