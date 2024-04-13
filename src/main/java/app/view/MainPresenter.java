package app.view;

import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import app.service.Service;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MainPresenter {

  private final I_MainView view;

  private Service titleService;

  private Service authorService;

  private Service topicService;

  public MainPresenter(I_MainView mainView,
                       Service titleService,
                       Service authorService,
                       Service topicService) {
    this.view = mainView;
    this.titleService = titleService;
    this.authorService = authorService;
    this.topicService = topicService;
  }

  public void start() {
    view.displayWelcome();
  }


  public void titleSearch(String input) {

    if (!validateInput(input)) {
      view.displayErrorMessage("Invalid input.  Please enter a valid title.");
      return;
    }
    Response response = null;
    try {
      response = titleService.run(new Request(input));
    } catch (IOException | RuntimeException e) {
      view.displayErrorMessage("An exception occurred: " + e.getMessage());
    }
    if (response != null) {
      view.displayTitleSearchResults(response.getResults());
    }
  }

  public void authorSearch(String input) {
    if (!validateInput(input)) {
      view.displayErrorMessage("Invalid input.  Please enter a valid author name.");
      return;
    }
    Response response = null;
    try {
      response = authorService.run(new Request(input));
    } catch (IOException | RuntimeException e) {
      view.displayErrorMessage("An exception occurred: " + e.getMessage());
    }
    if (response != null) {
      view.displayAuthorSearchResults(response.getResults());
    }
  }

  public void topicSearch(String input) {

    if (!validateInput(input)) {
      view.displayErrorMessage("Invalid input.  Please enter a valid topic.");
      return;
    }
    Response response = null;
    try {
      response = topicService.run(new Request(input));
    } catch (IOException | RuntimeException e) {
      view.displayErrorMessage("An exception occurred: " + e.getMessage());
    }
    if (response != null) {
      view.displayTopicSearchResults(response.getResults());
    }
  }

  private boolean validateInput(String input) {
    return !input.equals("");
  }

  public I_MainView getView() {
    return view;
  }

  public Service getTitleService() {
    return titleService;
  }

  public void setTitleService(Service titleService) {
    this.titleService = titleService;
  }

  public Service getAuthorService() {
    return authorService;
  }

  public void setAuthorService(Service authorService) {
    this.authorService = authorService;
  }

  public Service getTopicService() {
    return topicService;
  }

  public void setTopicService(Service topicService) {
    this.topicService = topicService;
  }
}
