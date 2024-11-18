

import controller.PatientController;
import view.PatientView;

public class Main {
    public static void main(String[] args) {
        PatientController controller = new PatientController();
        PatientView view = new PatientView(controller);
        view.displayMenu();
    }
}