package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    private static final String FILE_NAME = "patients.txt";

    public List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                patients.add(Patient.fromFileString(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading patients: " + e.getMessage());
        }
        return patients;
    }

    public void savePatients(List<Patient> patients) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient patient : patients) {
                bw.write(patient.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
        }
    }
}
