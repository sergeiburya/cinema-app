package cinema.lib;

import cinema.dto.request.UserRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.validation.ConstraintValidatorContext;

class FieldsValueMatchValidatorTest {
    private FieldsValueMatchValidator passwordValidator;
    private FieldsValueMatch constraintAnnotation;
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    void setUp() {
        passwordValidator = new FieldsValueMatchValidator();
        constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
        constraintAnnotation = Mockito.mock(FieldsValueMatch.class);
        Mockito.when(constraintAnnotation.field()).thenReturn("password");
        Mockito.when(constraintAnnotation.fieldMatch()).thenReturn("repeatPassword");
        passwordValidator.initialize(constraintAnnotation);
    }

    @Test
    public void isValid_Ok() {
        UserRequestDto userRegistrationDto = new UserRequestDto();
        userRegistrationDto.setPassword("1234");
        userRegistrationDto.setRepeatPassword("1234");
        Assertions.assertTrue(passwordValidator.isValid(userRegistrationDto,
                constraintValidatorContext));
    }

    @Test
    public void isValid_NotOk() {
        UserRequestDto registrationDto = new UserRequestDto();
        registrationDto.setPassword("1234");
        registrationDto.setRepeatPassword("1233");
        Assertions.assertFalse(passwordValidator.isValid(registrationDto,
                constraintValidatorContext));
    }
}