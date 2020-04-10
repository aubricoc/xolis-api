package cat.aubricoc.xolis.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    private static final String SALT = "X0l1s41r3sPr0t3g1ts";

    private PasswordUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static String encode(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(SALT.getBytes());
            byte[] encodedBytes = md5.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte encodedByte : encodedBytes) {
                sb.append(Integer.toString((encodedByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Error encoding password", e);
        }
    }
}
