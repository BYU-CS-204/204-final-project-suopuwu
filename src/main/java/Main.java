import app.LocalContext;
import app.view.I_MainView;
import app.view.MainPresenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(LocalContext.class);
    MainPresenter mainApp = context.getBean(MainPresenter.class);
    I_MainView view = mainApp.getView();
    view.displayWelcome();
  }
}
