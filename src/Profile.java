public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    @Override
    public int compareTo(Profile other) {
        String name = this.fname + this.lname;
        String otherName = other.fname + other.lname;
        return name.compareTo(otherName);
    }

    @Override
    public String toString() {
        return fname + " " + lname + dob;
    } //this.firstname this.lastname and this.dob.toString

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            return fname.equals(((Profile) obj).fname) && lname.equals(((Profile) obj).lname);
        }
        return false;
    }

}
