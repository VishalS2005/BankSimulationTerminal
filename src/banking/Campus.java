package banking;

public enum Campus {

    _1("NEW_BRUNSWICK"),
    _2("NEWARK"),
    _3("CAMDEN");

    private final String name;

    Campus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Campus fromCode(String code) {
        for (Campus campus : Campus.values()) {
            if (campus.name().equals("_" + code)) {
                return campus;
            }
        }
        throw new IllegalArgumentException("Invalid campus code: " + code);
    }

    @Override
    public String toString() {
        return name;
    }
}
