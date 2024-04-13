package app.view;

import app.model.Book;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class TerminalWindowView implements I_MainView {

  private MainPresenter presenter;

  private String currentQueryString;

  private final Scanner scanner;

  public TerminalWindowView() {
    scanner = new Scanner(System.in);
  }

  @Override
  public void setPresenter(MainPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayWelcome() {
    System.out.println("Welcome to the BYU Bookstore!\nPlease select an option");
    String input = "";
    while (!input.equals("quit")) {
      displayMainOptions();
      input = scanner.nextLine();
      switch (input) {
        case "title": {
          titleSearch();
          break;
        }
        case "author": {
          authorSearch();
          break;
        }
        case "topic": {
          topicSearch();
          break;
        }
        default: {
          break;
        }
      }
    }
  }

  private void titleSearch() {
    System.out.println("Please enter the title of the book you are searching for:");

    currentQueryString = scanner.nextLine();
    presenter.titleSearch(currentQueryString);
  }

  private void authorSearch() {
    System.out.println("Please enter the author you are searching for:");

    currentQueryString = scanner.nextLine();
    presenter.authorSearch(currentQueryString);
  }

  private void topicSearch() {
    System.out.println("Please enter the topic you are searching for:");

    currentQueryString = scanner.nextLine();
    presenter.topicSearch(currentQueryString);
  }

  private void displayMainOptions() {
    System.out.println("title: Search for a book by title");
    System.out.println("author: Search for a book by author");
    System.out.println("topic: Search for all books related to a selected topic");
    System.out.println("quit: Exit the application");
  }

  @Override
  public void displayTitleSearchResults(Book[] results) {
    System.out.println("Results for books with title " + currentQueryString + ": ");
    printBookArray(results);
  }

  @Override
  public void displayAuthorSearchResults(Book[] results) {
    System.out.println("Results for books written by " + currentQueryString + ": ");
    printBookArray(results);
  }

  @Override
  public void displayTopicSearchResults(Book[] results) {
    System.out.println("Results for books with topic " + currentQueryString + ": ");
    printBookArray(results);
  }

  @Override
  public void displayErrorMessage(String message) {
    System.out.println(message);
  }

  private void printBookArray(Book[] array) {
    for (Book book : array) {
      System.out.println(book.getTitle() + ": " + book.getAuthor());
    }
  }
}
