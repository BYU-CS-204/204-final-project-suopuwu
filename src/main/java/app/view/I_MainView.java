package app.view;

import app.model.Book;

public interface I_MainView {
  void setPresenter(MainPresenter presenter);

  void displayWelcome();

  void displayAuthorSearchResults(Book[] results);

  void displayTitleSearchResults(Book[] results);

  void displayTopicSearchResults(Book[] results);

  void displayErrorMessage(String message);
}
