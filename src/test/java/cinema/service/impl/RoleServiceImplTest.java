package cinema.service.impl;

import java.util.Optional;
import cinema.dao.RoleDao;
import cinema.dao.impl.RoleDaoImpl;
import cinema.model.Role;
import cinema.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RoleServiceImplTest {
    private static final String ROLE_ADMIN = "ADMIN";
    private RoleService roleService;
    private RoleDao roleDao;
    private Role role;

    @BeforeEach
    void setUp() {
        roleDao = Mockito.mock(RoleDaoImpl.class);
        roleService = new RoleServiceImpl(roleDao);
        role = new Role(Role.RoleName.ADMIN);
        role.setId(1L);
    }

    @Test
    public void save_Ok() {
        Role newRole = new Role(Role.RoleName.ADMIN);
        Mockito.when(roleDao.add(newRole)).thenReturn(role);
        Role actual = roleService.add(newRole);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, role);
    }

    @Test
    public void getRoleByName_Ok() {
        Mockito.when(roleDao.getByName(ROLE_ADMIN)).thenReturn(Optional.ofNullable(role));
        Role actual = roleService.getByName(ROLE_ADMIN);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, role);
    }
}
