package cinema.dao.impl;

import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't create role" + role, e);
            throw new DataProcessingException("Can't create role" + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return role;
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> roleQuery =
                    session.createQuery("FROM Role WHERE roleName = :role", Role.class);
            roleQuery.setParameter("role", Role.RoleName.valueOf(roleName));
            return roleQuery.uniqueResultOptional();
        } catch (Exception e) {
            logger.error("Can't find roleName from db by:" + roleName, e);
            throw new DataProcessingException("Can't find roleName from db by:" + roleName, e);
        }
    }
}
