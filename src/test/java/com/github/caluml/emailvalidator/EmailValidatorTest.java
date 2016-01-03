package com.github.caluml.emailvalidator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailValidatorTest {

    @Test
    public void Valid_domains_pass() {
        assertTrue("foo@gmail.com should pass", EmailValidator.isValidEmailAddress("foo@gmail.com"));
    }

    @Test
    public void Valid_domains_with_invalid_users_fail() {
        assertFalse("@gmail.com should fail", EmailValidator.isValidEmailAddress("@gmail.com"));
    }

    @Test
    public void Invalid_domains_fail() {
        assertFalse("An invalid domain should fail", EmailValidator.isValidEmailAddress("foo@2093jd0jdjwioedjo"));
    }

    @Test
    public void Wikipedia_valid_local_part_examples() {
        String[] emailAddresses = new String[]{
                "prettyandsimple",
                "very.common",
                "disposable.style.email.with+symbol",
                "other.email-with-dash",
                "\"much.more unusual\"",
                "\"very.unusual.@.unusual.com\"",
                "\"very.(),:;<>[]\\\".VERY.\\\"very@\\\\ \\\"very\\\".unusual\"",
                "#!$%&'*+-/=?^_`{}|~",
                "\"()<>[]:,;@\\\\\\\"!#$%&'*+-/=?^_`{}| ~.a\"",
                "\" \"",
                "üñîçøðé"
        };
        for (String emailAddress : emailAddresses) {
            assertTrue(EmailValidator.isValidEmailAddress(emailAddress + "@gmail.com"));
        }
    }
}
