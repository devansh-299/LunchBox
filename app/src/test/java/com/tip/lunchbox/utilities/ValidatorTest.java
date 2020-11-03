package com.tip.lunchbox.utilities;

import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class ValidatorTest {
    private String emptyUsername = "";
    private String emptyPassword = "";
    private String emptyRepassword = "";
    private String username = "TestUsername";
    private String usernameLeadingSpace = "  TestUsername";
    private String usernameTrailingSpace = "TestUsername   ";
    private String passwordWithoutSpecial = "Testpassword1";
    private String passwordWithoutCaps = "testpassword@1";
    private String passwordWithoutnum = "Testpassword@";
    private String passwordwithall = "Testpassword@1";
    private String repasswordfake = "FakeTestPassword@1";
    private String repasswordtrue = "Testpassword@1";
    private String shortpassword = "T@st1";
    private Long phone = Integer.toUnsignedLong(9090909);


    @Test
    public void emptyPasswordReturnsFalse() {
        Boolean actualRes = Validator.passwordValidator(emptyPassword);
        assertThat(actualRes).isFalse();

    }

    @Test
    public void shortPasswordReturnsFalse() {
        Boolean actualRes = Validator.passwordValidator(shortpassword);
        assertThat(actualRes).isFalse();

    }

    @Test
    public void passwordWithoutSpecialReturnsFalse() {
        Boolean actualRes = Validator.passwordValidator(passwordWithoutSpecial);
        assertThat(actualRes).isFalse();

    }

    @Test
    public void passwordWithoutNumReturnsFalse() {
        Boolean actualRes = Validator.passwordValidator(passwordWithoutnum);
        assertThat(actualRes).isFalse();

    }

    @Test
    public void passwordWithoutCapReturnsFalse() {
        Boolean actualRes = Validator.passwordValidator(passwordWithoutCaps);
        assertThat(actualRes).isFalse();

    }


    @Test
    public void validPasswordReturnTrue() {
        Boolean actualRes = Validator.passwordValidator(passwordwithall);
        assertThat(actualRes).isTrue();
    }

    @Test
    public void emptyUsernameReturnFalse() {
        Boolean actualRes = Validator.usernameValidator(emptyUsername);
        assertThat(actualRes).isFalse();
    }

    @Test
    public void leadingSpacesUsernameReturnFalse() {
        Boolean actualRes = Validator.usernameValidator(usernameLeadingSpace);
        assertThat(actualRes).isFalse();
    }

    @Test
    public void trailingSpacesUsernameReturnFalse() {
        Boolean actualRes = Validator.usernameValidator(usernameTrailingSpace);
        assertThat(actualRes).isFalse();
    }

    @Test
    public void validUsernameReturnTrue() {
        Boolean actualRes = Validator.usernameValidator(username);
        assertThat(actualRes).isTrue();
    }
}