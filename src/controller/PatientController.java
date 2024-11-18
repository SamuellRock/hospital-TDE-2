package controller;

import model.Patient;
import model.PatientRepository;

import java.util.ArrayList;
import java.util.List;

public class PatientController implements IPatientController {
    private List<Patient> patients;
    private PatientRepository patientRepository;

    public PatientController() {
        this.patientRepository = new PatientRepository();
        this.patients = new ArrayList<>(patientRepository.loadPatients());
    }

    @Override
    public List<Patient> listPatients() {
        return (patients);
    }

    @Override
    public void addPatient(int id, double weight, double height) {
        Patient patient = new Patient(id, weight, height);
        patients.add(patient);
        patientRepository.savePatients(patients);
    }

    @Override
    public boolean updatePatient(int id, double newWeight, double newHeight) {
        Patient patient = findPatientById(id);
        if (patient != null) {
            patient.setWeight(newWeight);
            patient.setHeight(newHeight);
            patientRepository.savePatients(patients);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePatient(int id) {
        Patient patient = findPatientById(id);
        if (patient != null) {
            patients.remove(patient);
            patientRepository.savePatients(patients);
            return true;
        }
        return false;
    }

    private Patient findPatientById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
