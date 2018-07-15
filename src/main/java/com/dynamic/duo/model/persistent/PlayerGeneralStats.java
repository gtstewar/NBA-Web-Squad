package com.dynamic.duo.model.persistent;

import com.dynamic.duo.utils.DomainObjectCache;
import com.dynamic.duo.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player_general_stats")
public class PlayerGeneralStats extends DomainObject<PlayerGeneralStats> implements Serializable {

    /**cache of playerGeneralStats objects*/
    static private DomainObjectCache<Long, PlayerGeneralStats> cache = new DomainObjectCache<>( PlayerGeneralStats.class );

    /**
     * gets top x results sorted by a column that is passed in from a given table
     * @param table - table to be selected from
     * @param col - column to sort by
     * @param max - number of items to be returned
     * @return rows - List of Persistent Objects to be returned
     */
    public static List<? extends DomainObject> getTopItems(String col, int max, boolean desc) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query;
        if(desc) {
            query = "FROM PlayerGeneralStats WHERE gp > 40 " + " ORDER BY " + col + " DESC";
        } else {
            //whole query
            query = "FROM PlayerGeneralStats WHERE gp > 40 " + " ORDER BY " + col + " ASC";
        }
        //create HQL query in session after retrieving from sessionFactory
        List<? extends DomainObject> rows = session.createQuery(query).setMaxResults(max).list();
        session.getTransaction().commit();
        session.close();
        return rows;
    }

    public static PlayerGeneralStats getByID(Long id){
        PlayerGeneralStats stats = cache.get(id);
        if(stats == null) {
            try {
                final Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                //parameterized where clause
                String whereClause = " id = :id";
                //whole query
                String query = "FROM PlayerGeneralStats WHERE" + whereClause;
                //create HQL query in session after retrieving from sessionFactory
                stats = (PlayerGeneralStats) session
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

    public static PlayerGeneralStats getByPlayerID(Long id){
        PlayerGeneralStats stats = null;
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //parameterized where clause
            String whereClause = " player_id = :id";
            //whole query
            String query = "FROM PlayerGeneralStats WHERE" + whereClause;
            //create HQL query in session after retrieving from sessionFactory
            stats = (PlayerGeneralStats) session
                    .createQuery(query).setParameter("id", id).list().get(0);
            session.getTransaction().commit();
            session.close();
            //put in cache since it wasnt in cache originally
        } catch (Exception e) {
            //catch all exceptions
        }
        return stats;
    }

    /**
     * columns of table to be mapped
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "general_stats_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private String player_name;
    private String team_ab;
    private int age;
    private int gp;
    private int w;
    private int l;
    private float win_pct;
    private float min;
    private float fgm;
    private float fga;
    private float fg_pct;
    private float fg3m;
    private float fg3a;
    private float fg3_pct;
    private float ftm;
    private float fta;
    private float ft_pct;
    private float oreb;
    private float dreb;
    private float reb;
    private float ast;
    private float tov;
    private float stl;
    private float blk;
    private float blka;
    private float pf;
    private float pfd;
    private float pts;
    private float plus_minus;
    private float nba_fantasy_pts;
    private int dd2;
    private int td3;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public int getWins() {
        return w;
    }

    public void setWins(int wins) {
        this.w = wins;
    }

    public int getLoses() {
        return l;
    }

    public void setLoses(int loses) {
        this.l = loses;
    }

    public float getWin_pct() {
        return win_pct;
    }

    public void setWin_pct(float win_pct) {
        this.win_pct = win_pct;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getFgm() {
        return fgm;
    }

    public void setFgm(float fgm) {
        this.fgm = fgm;
    }

    public float getFga() {
        return fga;
    }

    public void setFga(float fga) {
        this.fga = fga;
    }

    public float getFg_pct() {
        return fg_pct;
    }

    public void setFg_pct(float fg_pct) {
        this.fg_pct = fg_pct;
    }

    public float getFg3m() {
        return fg3m;
    }

    public void setFg3m(float fg3m) {
        this.fg3m = fg3m;
    }

    public float getFg3a() {
        return fg3a;
    }

    public void setFg3a(float fg3a) {
        this.fg3a = fg3a;
    }

    public float getFg3_pct() {
        return fg3_pct;
    }

    public void setFg3_pct(float fg3_pct) {
        this.fg3_pct = fg3_pct;
    }

    public float getFtm() {
        return ftm;
    }

    public void setFtm(float ftm) {
        this.ftm = ftm;
    }

    public float getFta() {
        return fta;
    }

    public void setFta(float fta) {
        this.fta = fta;
    }

    public float getFt_pct() {
        return ft_pct;
    }

    public void setFt_pct(float ft_pct) {
        this.ft_pct = ft_pct;
    }

    public float getOreb() {
        return oreb;
    }

    public void setOreb(float oreb) {
        this.oreb = oreb;
    }

    public float getDreb() {
        return dreb;
    }

    public void setDreb(float dreb) {
        this.dreb = dreb;
    }

    public float getReb() {
        return reb;
    }

    public void setReb(float reb) {
        this.reb = reb;
    }

    public float getAst() {
        return ast;
    }

    public void setAst(float ast) {
        this.ast = ast;
    }

    public float getTov() {
        return tov;
    }

    public void setTov(float tov) {
        this.tov = tov;
    }

    public float getStl() {
        return stl;
    }

    public void setStl(float stl) {
        this.stl = stl;
    }

    public float getBlk() {
        return blk;
    }

    public void setBlk(float blk) {
        this.blk = blk;
    }

    public float getBlka() {
        return blka;
    }

    public void setBlka(float blka) {
        this.blka = blka;
    }

    public float getPf() {
        return pf;
    }

    public void setPf(float pf) {
        this.pf = pf;
    }

    public float getPfd() {
        return pfd;
    }

    public void setPfd(float pfd) {
        this.pfd = pfd;
    }

    public float getPts() {
        return pts;
    }

    public void setPts(float pts) {
        this.pts = pts;
    }

    public float getPlus_minus() {
        return plus_minus;
    }

    public void setPlus_minus(float plus_minus) {
        this.plus_minus = plus_minus;
    }

    public float getNba_fantasy_pts() {
        return nba_fantasy_pts;
    }

    public void setNba_fantasy_pts(float nba_fantasy_pts) {
        this.nba_fantasy_pts = nba_fantasy_pts;
    }

    public int getDd2() {
        return dd2;
    }

    public void setDd2(int dd2) {
        this.dd2 = dd2;
    }

    public int getTd3() {
        return td3;
    }

    public void setTd3(int td3) {
        this.td3 = td3;
    }

}
