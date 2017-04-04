# email-validator
Email validator

This is a small library which can be used to validate email addresses.

Rather than use convoluted regular expressions, or maintain a list of valid TLDs, etc, to validate the domain part of the email address, it performs a DNS lookup.
If it has an MX, A, or AAAA record, the domain part is considered valid.


Usage:

```
if (EmailValidator.isValidEmailAddress("some.thing+other@gmail.com")) {
   // Do a happy dance
} else {
   // Sad face.
}
```
