package com.dynamic.duo.model.persistent;

import com.dynamic.duo.utils.DomainObjectCache;
import com.dynamic.duo.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team_general_stats")
public class TeamGeneralStats extends DomainObject<TeamGeneralStats> implements Serializable {

    /**cache of playerGeneralStats objects*/
    static private DomainObjectCache<Long, TeamGeneralStats> cache = new DomainObjectCache<>( TeamGeneralStats.class );

    public static TeamGeneralStats getByID(Long id){
        TeamGeneralStats stats = cache.get(id);
        if(stats == null) {
            try {
                final Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                //parameterized where clause
                String whereClause = " id = :id";
                //whole query
                String query = "FROM TeamGeneralStats WHERE" + whereClause;
                //create HQL query in session after retrieving from sessionFactory
                stats = (TeamGeneralStats) session
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

    public static TeamGeneralStats getByPlayerID(Long id){
        TeamGeneralStats stats = null;
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //parameterized where clause
            String whereClause = " player_id = :id";
            //whole query
            String query = "FROM TeamGeneralStats WHERE" + whereClause;
            //create HQL query in session after retrieving from sessionFactory
            stats = (TeamGeneralStats) session
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
    @Column(name = "team_general_stats_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String team_name;
    private int gp;
    private int w;
    private int l;
    private float w_pct;
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
    private String team_abb;

    @Override
    public Long getId() {
        return id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public float getW_pct() {
        return w_pct;
    }

    public void setW_pct(float w_pct) {
        this.w_pct = w_pct;
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

    public String getTeam_abb() {
        return team_abb;
    }

    public void setTeam_abb(String team_abb) {
        this.team_abb = team_abb;
    }
}
