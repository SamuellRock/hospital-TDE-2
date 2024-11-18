package controller;

import model.Patient;
import java.util.List;

public interface IPatientController {
    List<Patient> listPatients();
    void addPatient(int id, double weight, double height);
    boolean updatePatient(int id, double newWeight, double newHeight);
    boolean deletePatient(int id);
}
