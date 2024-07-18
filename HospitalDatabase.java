import java.util.TreeMap;

public class HospitalDatabase {
    TreeMap<String, Patient> patientsByName; // Stores patients by their full names
    TreeMap<Integer, TreeMap<String, Patient>> patientsByYear; // Stores patients by their visit year, then by name within each year

    public HospitalDatabase() {
        this.patientsByName = new TreeMap<>();
        this.patientsByYear = new TreeMap<>();
    }

    /**
     * Adds a new patient to the database. If a patient with the same name already exists, the old information is overwritten.
     * @param patientName the full name of the patient
     * @param doctorName the full name of the doctor
     * @param visitDay the day of the visit
     * @param visitMonth the month of the visit
     * @param visitYear the year of the visit
     */
    public void addPatient(String patientName, String doctorName, int visitDay, int visitMonth, int visitYear) {
        // Check if the patient already exists and display an error if so
        if (patientsByName.containsKey(patientName)) {
            System.out.println("ERROR: Patient " + patientName + " overwritten");
            return;
        }

        // Split patient name into first and last name
        String[] names = patientName.split(" ");
        Patient patient = new Patient(names[0], names[1], doctorName, visitDay, visitMonth, visitYear);

        // Add the new patient to the patientsByName map
        patientsByName.put(patientName, patient);

        // Update the binary search tree for patients in the same year
        patientsByYear.putIfAbsent(visitYear, new TreeMap<>());
        patientsByYear.get(visitYear).put(patientName, patient);

        // Display info message indicating the patient has been added
        System.out.println("INFO: Patient " + patientName + " has been added");
    }

    /**
     * Removes a patient from the database. If the patient does not exist, a warning message is displayed.
     * @param patientName the full name of the patient
     */
    public void removePatient(String patientName) {
        // Remove patient from patientsByName map
        Patient patient = patientsByName.remove(patientName);
        if (patient != null) {
            // Remove patient from patientsByYear map
            patientsByYear.get(patient.visitYear).remove(patientName);
            if (patientsByYear.get(patient.visitYear).isEmpty()) {
                patientsByYear.remove(patient.visitYear);
            }
            // Display info message indicating the patient has been removed
            System.out.println("INFO: Patient " + patientName + " has been removed");
        } else {
            // Display error message if patient does not exist
            System.out.println("ERROR: Patient " + patientName + " does not exist");
        }
    }

    /**
     * Adds a medical staff member to a patient's care team. If the patient does not exist, a warning message is displayed.
     * @param patientName the full name of the patient
     * @param memberName the full name of the medical staff member
     * @param memberRole the role of the medical staff member
     */
    public void addMember(String patientName, String memberName, String memberRole) {
        // Get patient from patientsByName map
        Patient patient = patientsByName.get(patientName);
        if (patient != null) {
            // Split member name into first and last name
            String[] names = memberName.split(" ");
            MedicalStaff staff = new MedicalStaff(names[0], names[1], memberRole);
            // Add medical staff member to patient's care team
            patient.careTeam.put(memberName, staff);
            // Display info message indicating the staff member has been added
            System.out.println("INFO: " + memberName + " has been added to the patient " + patientName);
        } else {
            // Display error message if patient does not exist
            System.out.println("ERROR: Patient " + patientName + " does not exist");
        }
    }

    /**
     * Removes a medical staff member from a patient's care team. If the patient or staff member does not exist, a warning message is displayed.
     * @param patientName the full name of the patient
     * @param memberName the full name of the medical staff member
     */
    public void removeMember(String patientName, String memberName) {
        // Get patient from patientsByName map
        Patient patient = patientsByName.get(patientName);
        if (patient != null) {
            // Remove medical staff member from patient's care team
            MedicalStaff staff = patient.careTeam.remove(memberName);
            if (staff != null) {
                // Display info message indicating the staff member has been removed
                System.out.println("INFO: " + memberName + " has been removed from the patient " + patientName);
            } else {
                // Display error message if staff member does not exist in care team
                System.out.println("ERROR: Medical staff " + memberName + " does not exist in the care team of patient " + patientName);
            }
        } else {
            // Display error message if patient does not exist
            System.out.println("ERROR: Patient " + patientName + " does not exist");
        }
    }

    /**
     * Displays all patients in the database in ascending order by visit year.
     * If no patients exist, "---none---" is displayed.
     */
    public void showAllPatients() {
        if (patientsByYear.isEmpty()) {
            // Display none message if no patients exist
            System.out.println("---none---");
        } else {
            // Iterate over patients by year and display patient info
            for (TreeMap<String, Patient> patients : patientsByYear.values()) {
                for (Patient patient : patients.values()) {
                    System.out.println(patient.firstName + " " + patient.lastName + ", " + patient.visitYear + ", " + patient.doctorName);
                }
            }
        }
    }

    /**
     * Displays detailed information about a specific patient.
     * @param patientName the full name of the patient
     */
    public void showPatient(String patientName) {
        // Get patient from patientsByName map
        Patient patient = patientsByName.get(patientName);
        if (patient != null) {
            // Display detailed info about the patient
            System.out.println(patient.firstName + " " + patient.lastName);
            System.out.println(patient.visitDay + "/" + patient.visitMonth + "/" + patient.visitYear);
            System.out.println(patient.doctorName);
            for (MedicalStaff staff : patient.careTeam.values()) {
                System.out.println(staff.firstName + " " + staff.lastName + ", " + staff.role);
            }
        } else {
            // Display none message if patient does not exist
            System.out.println("---none---");
        }
    }

    /**
     * Displays all patients seen by a specific doctor in descending order by visit year.
     * @param doctorName the full name of the doctor
     */
    public void showDoctorPatients(String doctorName) {
        boolean found = false;
        // Iterate over patients by year in descending order and display patient info for the specified doctor
        for (TreeMap<String, Patient> patients : patientsByYear.descendingMap().values()) {
            for (Patient patient : patients.values()) {
                if (patient.doctorName.equals(doctorName)) {
                    if (!found) {
                        System.out.println(doctorName);
                        found = true;
                    }
                    System.out.println(patient.firstName + " " + patient.lastName + ", " + patient.visitDay + "/" + patient.visitMonth + "/" + patient.visitYear);
                }
            }
        }
        if (!found) {
            // Display none message if no patients were found for the specified doctor
            System.out.println(doctorName + "\n---none---");
        }
    }

    /**
     * Displays all patients who visited in a specific year.
     * @param visitYear the year of the visit
     */
    public void showPatients(int visitYear) {
        // Get patients from patientsByYear map for the specified year
        TreeMap<String, Patient> patients = patientsByYear.get(visitYear);
        if (patients != null && !patients.isEmpty()) {
            // Display patient info for the specified year
            System.out.println(visitYear);
            for (Patient patient : patients.values()) {
                System.out.println(patient.firstName + " " + patient.lastName + ", " + patient.visitDay + "/" + patient.visitMonth);
            }
        } else {
            // Display none message if no patients were found for the specified year
            System.out.println(visitYear + "\n---none---");
        }}}
      /*  public static void main (String[]args){
            HospitalDatabase hd = new HospitalDatabase();

            hd.showAllPatients();

            hd.addPatient("Michael Johnson", "Emma Thompson", 19, 12, 2022);
            hd.addPatient("Ethan Lee", "Olivia Sanchez", 8, 9, 2020);
            hd.addPatient("Noah Miller", "Olivia Sanchez", 27, 2, 2019);
            hd.addPatient("Liam Davis", "Isabella Martinez", 3, 4, 2022);
            hd.addPatient("Ava Taylor", "Isabella Martinez", 15, 5, 2024);
            hd.addPatient("Mason Moore", "William Anderson", 7, 6, 2021);
            hd.addPatient("Charlotte Garcia", "Lucas Lewis", 30, 10, 2023);
            hd.addPatient("Noah Miller", "Olivia Sanchez", 27, 2, 2019);

            hd.showAllPatients();
            hd.removePatient("Ava Taylor");
            hd.showAllPatients();
            hd.showPatient("Michael Johnson");
            hd.addMember("Mason Moore", "Daniel Roberts", "Nurse");
            hd.addMember("Mason Moore", "Victoria Stewart", "Radiologist");
            hd.addMember("Mason Moore", "Tyler Campbell", "Medical Assistant");
            hd.addMember("Mason Moore", "Hannah Martin", "Paramedic");
            hd.addMember("Michael Johnson", "Jack Allen", "Patient Care Technician");
            hd.addMember("Michael Johnson", "Oliver Nelson", "Anesthesiologist");
            hd.addMember("Michael Johnson", "Sophia Rivera", "Pathologist");
            hd.addMember("Michael Johnson", "Evan Hall", "Laboratory Technician");
            hd.addMember("Michael Johnson", "Megan Price", "Nurse");
            hd.addMember("Ava Taylor", "Brianna Reed", "Dietitian");
            hd.addMember("Charlotte Garcia", "Oliver Nelson", "Anesthesiologist");
            hd.addMember("Charlotte Garcia", "Trevor Jenkins", "Medical Equipment Technician");
            hd.addMember("Charlotte Garcia", "Justin Flores", "Speech-Language Pathologist");
            hd.showPatient("Mason Moore");
            hd.showPatient("Michael Johnson");
            hd.removeMember("Michael Johnson", "Evan Hall");
            hd.showPatient("Michael Johnson");
            hd.showDoctorPatients("Olivia Sanchez");
            hd.showDoctorPatients("Emma Thompson");
            hd.showPatients(2022);

            System.exit(0);
        }
    }*/
