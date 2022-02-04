package com.example.chatsubject.account.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserSignUpRequestDTOTest {

    private static Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("정상 요청 값을 확인한다.")
    void request_normal_input_values() {

        //given
        UserSignUpRequestDTO request = new UserSignUpRequestDTO(
                "cooper",
                "123",
                "cooper123@rsupport.com"
        );

        //when
        Set<ConstraintViolation<UserSignUpRequestDTO>> constraintValidations = validator.validate(request);

        //then
        assertThat(constraintValidations.size()).isZero();
    }

    @ParameterizedTest
    @MethodSource("provide_string_for_name_is_blank")
    @DisplayName("이름을 추가하지 않은 요청일 경우, 잘못된 요청이다.")
    void request_normal_input_except_name(String name, String password, String email) {

        //given
        UserSignUpRequestDTO request = new UserSignUpRequestDTO(name, password, email);

        //when
        Set<ConstraintViolation<UserSignUpRequestDTO>> constraintValidations = validator.validate(request);

        //then
        assertThat(constraintValidations.size()).isOne();
    }

    private static Stream<Arguments> provide_string_for_name_is_blank() {
        return Stream.of(
                Arguments.of("", "123", "sapmle@rsupport.com"),
                Arguments.of(null, "123", "sapmle@rsupport.com"),
                Arguments.of(" ", "123", "sapmle@rsupport.com")
        );
    }

    @ParameterizedTest
    @MethodSource("provide_string_for_password_is_blank")
    @DisplayName("비밀번호을 추가하지 않은 요청일 경우, 잘못된 요청이다.")
    void request_normal_input_except_password(String name, String password, String email) {

        //given
        UserSignUpRequestDTO request = new UserSignUpRequestDTO(name, password, email);

        //when
        Set<ConstraintViolation<UserSignUpRequestDTO>> constraintValidations = validator.validate(request);

        //then
        assertThat(constraintValidations.size()).isOne();
    }

    private static Stream<Arguments> provide_string_for_password_is_blank() {
        return Stream.of(
                Arguments.of("cooper", " ", "sapmle@rsupport.com"),
                Arguments.of("cooper", "", "sapmle@rsupport.com"),
                Arguments.of("cooper", null, "sapmle@rsupport.com")
        );
    }

}
