import java.util.TreeMap;

class MedicalStaff {
    String firstName;
    String lastName;
    String role;

    MedicalStaff(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    // compareTo method will compare alphabetically
    public int compareTo(MedicalStaff other) {
        return (this.firstName + " " + this.lastName).compareTo(other.firstName + " " + other.lastName);
    }
}

class Patient {
    String firstName;
    String lastName;
    String doctorName;
    int visitDay;
    int visitMonth;
    int visitYear;
    TreeMap<String, MedicalStaff> careTeam;

    Patient(String firstName, String lastName, String doctorName, int visitDay, int visitMonth, int visitYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorName = doctorName;
        this.visitDay = visitDay;
        this.visitMonth = visitMonth;
        this.visitYear = visitYear;
        this.careTeam = new TreeMap<>();
    }

    // compareTo method will compare by year
    public int compareTo(Patient other) {
        return Integer.compare(this.visitYear, other.visitYear);
    }
}
