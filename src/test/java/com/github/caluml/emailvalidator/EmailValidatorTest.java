package com.github.caluml.emailvalidator;

import org.junit.Ignore;
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

    @Ignore
    @Test
    public void Wikipedia_invalid_examples() {
        assertFalse("No @ character", EmailValidator.isValidEmailAddress("Abc.example.com"));
        assertFalse("Only one @ allowed outside quotation marks",
                EmailValidator.isValidEmailAddress("A@b@c@example.com"));
        assertFalse("None of the special characters in this local part are allowed outside quotation marks",
                EmailValidator.isValidEmailAddress("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"));
        assertFalse("Quoted strings must be dot separated or the only element making up the local-part",
                EmailValidator.isValidEmailAddress("just\"not\"right@example.com"));
        assertFalse("Spaces, quotes, and backslashes may only exist when within quoted strings and preceded by a backslash",
                EmailValidator.isValidEmailAddress("this is\"not\\allowed@example.com"));
        assertFalse("Even if escaped (preceded by a backslash), spaces, quotes, and backslashes must still be contained by quotes",
                EmailValidator.isValidEmailAddress("this\\ still\\\"not\\\\allowed@example.com"));
        assertFalse("Double dot after @", EmailValidator.isValidEmailAddress("john.doe@example..com"));
        assertFalse("a valid address with a leading space", EmailValidator.isValidEmailAddress(" foo@gmail.com"));
        assertFalse("a valid address with a trailing space", EmailValidator.isValidEmailAddress("foo@gmail.com "));
    }
}
