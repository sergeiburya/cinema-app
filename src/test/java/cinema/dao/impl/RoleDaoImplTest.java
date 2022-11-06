package cinema.dao.impl;

import java.util.Optional;
import cinema.dao.AbstractTest;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleDaoImplTest extends AbstractTest {
    private RoleDao roleDao;
    private Role role;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{Role.class};
    }

    @BeforeEach
    void setUp() {
        roleDao = new RoleDaoImpl(getSessionFactory());
        role = new Role(Role.RoleName.USER);
    }

    @Test
    public void save_Ok() {
        Role actual = roleDao.add(role);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(role.getRoleName(), actual.getRoleName());
    }

    @Test
    public void getRoleByName_Ok() {
        roleDao.add(role);
        Optional<Role> actual = roleDao.getByName(Role.RoleName.USER.name());
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(Role.RoleName.USER, actual.get().getRoleName());
    }

    @Test
    public void getRoleByName_NotOk() {
        DataProcessingException exception = Assertions.assertThrows(DataProcessingException.class,
                () -> roleDao.getByName("MODERATOR"));
        Assertions.assertEquals("Can't find roleName from db by:MODERATOR",
                exception.getMessage());
    }
}
