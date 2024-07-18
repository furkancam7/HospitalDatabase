/
public class Tester {
    public static void main(String[] args) {
        HospitalDatabase hd = new HospitalDatabase();

        // Initial display of all patients (should be empty)
        System.out.println();
        hd.showAllPatients();
        System.out.println();

        // Adding patients
        System.out.println("Adding patients:");
        hd.addPatient("Michael Johnson", "Emma Thompson", 19, 12, 2022);
        hd.addPatient("Ethan Lee", "Olivia Sanchez", 8, 9, 2020);
        hd.addPatient("Noah Miller", "Olivia Sanchez", 27, 2, 2019);
        hd.addPatient("Liam Davis", "Isabella Martinez", 3, 4, 2022);
        hd.addPatient("Ava Taylor", "Isabella Martinez", 15, 5, 2024);
        hd.addPatient("Mason Moore", "William Anderson", 7, 6, 2021);
        hd.addPatient("Charlotte Garcia", "Lucas Lewis", 30, 10, 2023);
        hd.addPatient("Noah Miller", "Olivia Sanchez", 27, 2, 2019);
        System.out.println();

        // Display all patients
        System.out.println();
        hd.showAllPatients();
        System.out.println();

        // Remove a patient
        System.out.println("Removing patient Ava Taylor:");
        hd.removePatient("Ava Taylor");
        System.out.println();

        // Display all patients after removal
        System.out.println();
        hd.showAllPatients();
        System.out.println();

        // Display detailed information about a specific patient
        System.out.println("Details of patient Michael Johnson:");
        hd.showPatient("Michael Johnson");
        System.out.println();

        // Adding medical staff members to a patient's care team
        System.out.println("Adding medical staff to Mason Moore:");
        hd.addMember("Mason Moore", "Daniel Roberts", "Nurse");
        hd.addMember("Mason Moore", "Victoria Stewart", "Radiologist");
        hd.addMember("Mason Moore", "Tyler Campbell", "Medical Assistant");
        hd.addMember("Mason Moore", "Hannah Martin", "Paramedic");
        System.out.println();

        // Adding medical staff members to another patient's care team
        System.out.println("Adding medical staff to Michael Johnson:");
        hd.addMember("Michael Johnson", "Jack Allen", "Patient Care Technician");
        hd.addMember("Michael Johnson", "Oliver Nelson", "Anesthesiologist");
        hd.addMember("Michael Johnson", "Sophia Rivera", "Pathologist");
        hd.addMember("Michael Johnson", "Evan Hall", "Laboratory Technician");
        hd.addMember("Michael Johnson", "Megan Price", "Nurse");
        System.out.println();

        // Display detailed information about a specific patient with care team
        System.out.println("Details of patient Mason Moore:");
        hd.showPatient("Mason Moore");
        System.out.println();

        System.out.println("Details of patient Michael Johnson:");
        hd.showPatient("Michael Johnson");
        System.out.println();

        // Remove a medical staff member from a patient's care team
        System.out.println("Removing medical staff Evan Hall from Michael Johnson:");
        hd.removeMember("Michael Johnson", "Evan Hall");
        System.out.println();

        // Display detailed information about a specific patient after removing a care team member
        System.out.println("Details of patient Michael Johnson after removing Evan Hall:");
        hd.showPatient("Michael Johnson");
        System.out.println();

        // Display all patients seen by a specific doctor
        System.out.println("Patients seen by doctor Olivia Sanchez:");
        hd.showDoctorPatients("Olivia Sanchez");
        System.out.println();

        System.out.println("Patients seen by doctor Emma Thompson:");
        hd.showDoctorPatients("Emma Thompson");
        System.out.println();

        // Display all patients who visited in a specific year
        System.out.println();
        hd.showPatients(2022);
        System.out.println();

        // Display all patients who visited in a year with no visits
        System.out.println();
        hd.showPatients(2025);
        System.out.println();
    }
}
