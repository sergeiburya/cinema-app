package cinema.service.impl;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.Set;
import cinema.dao.UserDao;
import cinema.dao.impl.UserDaoImpl;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceImplTest {
    private static final String EMAIL = "test@email.com";
    private static final String PASSWORD = "1234";
    private UserService userService;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        userDao = Mockito.mock(UserDaoImpl.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserServiceImpl(passwordEncoder, userDao);
        user = new User();
        user.setId(1L);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        role = new Role(Role.RoleName.USER);
        user.setRoles(Set.of(role));
    }

    @Test
    public void save_Ok() {
        Mockito.when(passwordEncoder.encode(any())).thenReturn(PASSWORD);
        Mockito.when(userDao.add(any())).thenReturn(user);
        User actual = userService.add(user);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(user, actual);
    }

    @Test
    public void findByEmail_Ok() {
        Mockito.when(userDao.findByEmail(EMAIL)).thenReturn(Optional.ofNullable(user));
        Optional<User> actual = userService.findByEmail(EMAIL);
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(user, actual.get());
    }
}
