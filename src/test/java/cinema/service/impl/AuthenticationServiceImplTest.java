package cinema.service.impl;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.Set;
import cinema.exception.AuthenticationException;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.RoleService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationServiceImplTest {
    private static final String EMAIL = "alice@gmail.com";
    private static final String PASSWORD = "alice123";
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private ShoppingCartService shoppingCartService;
    private AuthenticationService authenticationService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserServiceImpl.class);
        shoppingCartService = Mockito.mock(ShoppingCartServiceImpl.class);
        roleService = Mockito.mock(RoleServiceImpl.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        authenticationService =
                new AuthenticationServiceImpl(userService, shoppingCartService,
                        roleService,
                        passwordEncoder);
        user = new User();
        user.setId(1L);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Role role = new Role(Role.RoleName.USER);
        user.setRoles(Set.of(role));
    }

    @Test
    public void register_Ok() {
        Mockito.when(roleService.getByName(any())).thenReturn(new Role(Role.RoleName.USER));
        Mockito.when(userService.add(any())).thenReturn(user);
        User actual = authenticationService.register(EMAIL, PASSWORD);
        Assertions.assertNotNull(actual);
        /* Assertions.assertEquals(user, actual); TODO */
    }

    @Test
    public void login_Ok() throws AuthenticationException {
        Mockito.when(userService.findByEmail(EMAIL)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(any(), any())).thenReturn(true);
        User actual = authenticationService.login(EMAIL, PASSWORD);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(user, actual);
    }

    @Test
    public void login_NotOk() {
        Mockito.when(userService.findByEmail(EMAIL)).thenReturn(Optional.empty());
        AuthenticationException exception = Assertions.assertThrows(AuthenticationException.class,
                () -> authenticationService.login(EMAIL, PASSWORD));
        Assertions.assertEquals("Incorrect username or password!!!", exception.getMessage());
    }
}
