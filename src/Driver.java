import controllers.AdminController;
import views.AdminView;

public class Driver {
    public static void main(String[] args) {
        AdminController adminController = new AdminController(new AdminView());
        adminController.display();
    }

}
