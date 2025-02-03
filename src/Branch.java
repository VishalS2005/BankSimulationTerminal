public enum Branch {
    ;
    private final String zip;
    private final String branchCode;
    private final String county;

    Branch(String zip, String branchCode, String county) {
        this.zip = zip;
        this.branchCode = branchCode;
        this.county = county;
    }
}
