package view;

import controller.PatientController;
import controller.IPatientController;
import model.Patient;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PatientView {
    private PatientController controller;
    private Scanner scanner;

    public PatientView(PatientController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("0. Exit");
            System.out.print("Escolha uma opção: ");
            option = captureInteger();

            switch (option) {
                case 1: 
                	addPatient();
                    break;
                case 2:
                    listPatients();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    private void addPatient() {
        int id = captureInteger("Número do Paciente: ");
        double weight = captureDouble("Peso do Paciente (kg): ");
        double height = captureDouble("Altura do Paciente (m): ");

        controller.addPatient(id, weight, height);
        System.out.println("Paciente adicionado com sucesso!");
    }

    private void listPatients() {
        List<Patient> patients = controller.listPatients();
        if (patients.isEmpty()) {
            System.out.println("Nenhum paciente registrado.");
        } else {
            for (Patient patient : patients) {
                patient.displayPatient();
            }
        }
    }

    private void updatePatient() {
        int id = captureInteger("Número do Paciente a ser alterado: ");
        double newWeight = captureDouble("Novo Peso (kg): ");
        double newHeight = captureDouble("Nova Altura (m): ");

        if (controller.updatePatient(id, newWeight, newHeight)) {
            System.out.println("Paciente alterado com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    private void deletePatient() {
        int id = captureInteger("Número do Paciente a ser excluído: ");
        if (controller.deletePatient(id)) {
            System.out.println("Paciente excluído com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    private int captureInteger(String message) {
        int value = 0;
        boolean valid;
        do {
            try {
                if (message != null) {
                    System.out.print(message);
                }
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                valid = false;
                scanner.nextLine(); // Clear buffer
            }
        } while (!valid);
        scanner.nextLine(); // Clear buffer
        return value;
    }

    private double captureDouble(String message) {
        double value = 0.0;
        boolean valid;
        do {
            try {
                System.out.print(message);
                value = scanner.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número decimal.");
                valid = false;
                scanner.nextLine();
            }
        } while (!valid);
        scanner.nextLine();
        return value;
    }
}
