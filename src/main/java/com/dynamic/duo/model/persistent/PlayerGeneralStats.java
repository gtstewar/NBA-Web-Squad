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

    public static List<PlayerGeneralStats> getTopPlayers(String col, int max){
        ArrayList<PlayerGeneralStats> stats = new ArrayList<PlayerGeneralStats>();
        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //whole query
            String query = "FROM PlayerGeneralStats ORDER BY " + col + " DESC";
            //create HQL query in session after retrieving from sessionFactory
            stats = (ArrayList<PlayerGeneralStats>)session.createQuery(query).setMaxResults(max).list();

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
    private int gp_rank;
    private int w_rank;
    private int l_rank;
    private int w_pct_rank;
    private int min_rank;
    private int fgm_rank;
    private int fga_rank;
    private int fg_pct_rank;
    private int fg3m_rank;
    private int fg3a_rank;
    private int fg3_pct_rank;
    private int ftm_rank;
    private int fta_rank;
    private int ft_pct_rank;
    private int oreb_rank;
    private int dreb_rank;
    private int reb_rank;
    private int ast_rank;
    private int tov_rank;
    private int stl_rank;
    private int blk_rank;
    private int blka_rank;
    private int pf_rank;
    private int pfd_rank;
    private int pts_rank;
    private int plus_minus_rank;
    private int nba_fantasy_pts_rank;
    private int dd2_rank;
    private int td3_rank;

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

    public int getGp_rank() {
        return gp_rank;
    }

    public void setGp_rank(int gp_rank) {
        this.gp_rank = gp_rank;
    }

    public int getW_rank() {
        return w_rank;
    }

    public void setW_rank(int w_rank) {
        this.w_rank = w_rank;
    }

    public int getL_rank() {
        return l_rank;
    }

    public void setL_rank(int l_rank) {
        this.l_rank = l_rank;
    }

    public int getW_pct_rank() {
        return w_pct_rank;
    }

    public void setW_pct_rank(int w_pct_rank) {
        this.w_pct_rank = w_pct_rank;
    }

    public int getMin_rank() {
        return min_rank;
    }

    public void setMin_rank(int min_rank) {
        this.min_rank = min_rank;
    }

    public int getFgm_rank() {
        return fgm_rank;
    }

    public void setFgm_rank(int fgm_rank) {
        this.fgm_rank = fgm_rank;
    }

    public int getFga_rank() {
        return fga_rank;
    }

    public void setFga_rank(int fga_rank) {
        this.fga_rank = fga_rank;
    }

    public int getFg_pct_rank() {
        return fg_pct_rank;
    }

    public void setFg_pct_rank(int fg_pct_rank) {
        this.fg_pct_rank = fg_pct_rank;
    }

    public int getFg3m_rank() {
        return fg3m_rank;
    }

    public void setFg3m_rank(int fg3m_rank) {
        this.fg3m_rank = fg3m_rank;
    }

    public int getFg3a_rank() {
        return fg3a_rank;
    }

    public void setFg3a_rank(int fg3a_rank) {
        this.fg3a_rank = fg3a_rank;
    }

    public int getFg3_pct_rank() {
        return fg3_pct_rank;
    }

    public void setFg3_pct_rank(int fg3_pct_rank) {
        this.fg3_pct_rank = fg3_pct_rank;
    }

    public int getFtm_rank() {
        return ftm_rank;
    }

    public void setFtm_rank(int ftm_rank) {
        this.ftm_rank = ftm_rank;
    }

    public int getFta_rank() {
        return fta_rank;
    }

    public void setFta_rank(int fta_rank) {
        this.fta_rank = fta_rank;
    }

    public int getFt_pct_rank() {
        return ft_pct_rank;
    }

    public void setFt_pct_rank(int ft_pct_rank) {
        this.ft_pct_rank = ft_pct_rank;
    }

    public int getOreb_rank() {
        return oreb_rank;
    }

    public void setOreb_rank(int oreb_rank) {
        this.oreb_rank = oreb_rank;
    }

    public int getDreb_rank() {
        return dreb_rank;
    }

    public void setDreb_rank(int dreb_rank) {
        this.dreb_rank = dreb_rank;
    }

    public int getReb_rank() {
        return reb_rank;
    }

    public void setReb_rank(int reb_rank) {
        this.reb_rank = reb_rank;
    }

    public int getAst_rank() {
        return ast_rank;
    }

    public void setAst_rank(int ast_rank) {
        this.ast_rank = ast_rank;
    }

    public int getTov_rank() {
        return tov_rank;
    }

    public void setTov_rank(int tov_rank) {
        this.tov_rank = tov_rank;
    }

    public int getStl_rank() {
        return stl_rank;
    }

    public void setStl_rank(int stl_rank) {
        this.stl_rank = stl_rank;
    }

    public int getBlk_rank() {
        return blk_rank;
    }

    public void setBlk_rank(int blk_rank) {
        this.blk_rank = blk_rank;
    }

    public int getBlka_rank() {
        return blka_rank;
    }

    public void setBlka_rank(int blka_rank) {
        this.blka_rank = blka_rank;
    }

    public int getPf_rank() {
        return pf_rank;
    }

    public void setPf_rank(int pf_rank) {
        this.pf_rank = pf_rank;
    }

    public int getPfd_rank() {
        return pfd_rank;
    }

    public void setPfd_rank(int pfd_rank) {
        this.pfd_rank = pfd_rank;
    }

    public int getPts_rank() {
        return pts_rank;
    }

    public void setPts_rank(int pts_rank) {
        this.pts_rank = pts_rank;
    }

    public int getPlus_minus_rank() {
        return plus_minus_rank;
    }

    public void setPlus_minus_rank(int plus_minus_rank) {
        this.plus_minus_rank = plus_minus_rank;
    }

    public int getNba_fantasy_pts_rank() {
        return nba_fantasy_pts_rank;
    }

    public void setNba_fantasy_pts_rank(int nba_fantasy_pts_rank) {
        this.nba_fantasy_pts_rank = nba_fantasy_pts_rank;
    }

    public int getDd2_rank() {
        return dd2_rank;
    }

    public void setDd2_rank(int dd2_rank) {
        this.dd2_rank = dd2_rank;
    }

    public int getTd3_rank() {
        return td3_rank;
    }

    public void setTd3_rank(int td3_rank) {
        this.td3_rank = td3_rank;
    }

}
