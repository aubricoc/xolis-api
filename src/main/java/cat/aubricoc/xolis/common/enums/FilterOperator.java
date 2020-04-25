package cat.aubricoc.xolis.common.enums;

public enum FilterOperator {

    EQUALS("eq"),
    NOT_EQUALS("ne"),
    GREATER_THAN("gt"),
    GREATER_OR_EQUALS("ge"),
    LESS_THAN("lt"),
    LESS_OR_EQUALS("le"),
    LIKE("like");

    private final String key;

    FilterOperator(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
