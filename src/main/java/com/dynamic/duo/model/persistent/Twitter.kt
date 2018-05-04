package com.dynamic.duo.model.persistent

import com.dynamic.duo.utils.DomainObjectCache
import com.dynamic.duo.utils.HibernateUtil
import org.hibernate.Session
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "twitter", schema = "nba")
data class Twitter(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "twitter_id")
    val twitter_id: Long = 0,

    @OneToOne
    val player_id: Player? = null,

    @OneToOne
    val team_id: Team? = null,

    val player_name: String? = null,

    val team_city: String? = null,

    val team_nickname: String? = null,

    @NotEmpty
    val twitter_handle: String? = null,

    @NotEmpty
    val user_type: String? = null

): DomainObject<Twitter>(), Serializable {
    companion object operations{
        @JvmStatic
        private val cache =  DomainObjectCache<Long, Twitter>(Twitter::class.java)

        /**
         * gets twitter info based on id from table
         * @param id - id of twitter
         * @return - twitter object with id given
         */
        @JvmStatic
        fun getByID(id: Long): Twitter? {
            var twitt = cache.get(id)
            if (twitt == null) {
                try {
                    val session = HibernateUtil.getSessionFactory().openSession()
                    session.beginTransaction()
                    //parameterized where clause
                    val whereClause = " twitter_id = :id"
                    //whole query
                    val query = "FROM Twitter WHERE$whereClause"
                    print(query)
                    //create HQL query in session after retrieving from sessionFactory
                    twitt = session
                            .createQuery(query).setParameter("id", id).list()[0] as Twitter
                    session.transaction.commit()
                    session.close()
                    //put in cache since it wasnt in cache originally
                    cache.put(id, twitt)
                } catch (e: Exception) {
                    //catch all exceptions
                }

            }
            return twitt
        }
    }

    override fun getId(): Long {
        return this.twitter_id
    }

}





