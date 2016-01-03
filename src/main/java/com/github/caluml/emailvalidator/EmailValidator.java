package com.github.caluml.emailvalidator;

import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class EmailValidator {

    public static boolean isValidEmailAddress(String email) {
        int atSymbol = email.lastIndexOf("@");
        if (atSymbol == -1) {
            return false;
        }
        String user = email.substring(0, atSymbol);
        String domain = email.substring(atSymbol + 1, email.length());

        return isValidUser(user) &&
                isValidDomain(domain);
    }

    private static boolean isValidUser(String user) {
        return user.length() > 0;
    }

    private static boolean isValidDomain(String domain) {
        return hasRecord(domain, "MX") || hasRecord(domain, "A") || hasRecord(domain, "AAAA");
    }

    private static boolean hasRecord(String domain, String type) {
        try {
            final NamingEnumeration<?> records = getAllRecords(domain, type);
            if (records == null) {
                return false;
            }

            return records.hasMore();
        } catch (NameNotFoundException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    private static NamingEnumeration<?> getAllRecords(String domain, String type) throws NamingException {
        final Hashtable<String, String> env = new Hashtable<String, String>();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        final DirContext ictx = new InitialDirContext(env);
        final Attributes attrs = ictx.getAttributes(domain, new String[]{type});
        final Attribute attr = attrs.get(type);
        if (attr == null) {
            return null;
        }
        return attr.getAll();
    }
}