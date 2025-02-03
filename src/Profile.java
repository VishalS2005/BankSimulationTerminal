public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    @Override
    public int compareTo(Profile other) {

    }

    @Override
    public String toString() {
        return fname + " " + lname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            return fname.equals(((Profile) obj).fname) && lname.equals(((Profile) obj).lname);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
