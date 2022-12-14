package cinema.dao.impl;

import java.util.Optional;
import java.util.Set;
import cinema.dao.AbstractTest;
import cinema.dao.RoleDao;
import cinema.dao.UserDao;
import cinema.model.Role;
import cinema.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDaoImplTest extends AbstractTest {
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "1234";
    private UserDao userDao;
    private RoleDao roleDao;
    private User user;
    private Role role;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{User.class, Role.class};
    }

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl(getSessionFactory());
        roleDao = new RoleDaoImpl(getSessionFactory());
        role = new Role(Role.RoleName.USER);
        user = new User();
        user.setEmail(EMAIL);
        user.setPassword("1234");
        user.setRoles(Set.of(role));
        roleDao.add(role);
    }

    @Test
    public void save_Ok() {
        User actual = userDao.add(user);
        Assertions.assertNotNull(actual);
        user.setId(1L);
        Assertions.assertEquals(user, actual);
    }

    @Test
    public void findById_Ok() {
        userDao.add(user);
        Optional<User> actual = userDao.findById(1L);
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(EMAIL, actual.get().getEmail());
    }

    @Test
    public void findByEmail_Ok() {
        userDao.add(user);
        Optional<User> actual = userDao.findByEmail(EMAIL);
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(1L, actual.get().getId());
        Assertions.assertEquals(PASSWORD, actual.get().getPassword());
    }

    @Test
    public void findByEmail_NotOk() {
        Optional<User> actual = userDao.findByEmail("email");
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void findById_NotOk() {
        Optional<User> actual = userDao.findById(3L);
        Assertions.assertTrue(actual.isEmpty());
    }
}
