package app;

import app.service.Service;
import app.service.local.LocalAuthorService;
import app.service.local.LocalTitleService;
import app.service.local.LocalTopicService;
import app.view.I_MainView;
import app.view.MainPresenter;
import app.view.TerminalWindowView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LocalContext {
  @Bean
  I_MainView mainView() {
    return new TerminalWindowView();
  }

  @Bean
  Service titleService() {
    return new LocalTitleService();
  }

  @Bean
  Service authorService() {
    return new LocalAuthorService();
  }

  @Bean
  Service topicService() {
    return new LocalTopicService();
  }

  @Bean
  MainPresenter mainPresenter() {
    I_MainView view = mainView();
    MainPresenter pres = new MainPresenter(
        view,
        titleService(),
        authorService(),
        topicService());
    view.setPresenter(pres);

    return pres;
  }

}
