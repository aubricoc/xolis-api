package cat.aubricoc.xolis.security;

public class Role {

    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    private Role() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }
}
