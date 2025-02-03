public enum AccountType {
    CHECKING("01"),
    REGULAR_SAVINGS("02"),
    MONEY_MARKET_SAVINGS("03");

    private final String code;

    AccountType(String code) {
        this.code = code;
    }


}
