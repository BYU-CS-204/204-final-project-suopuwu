package app.model.requestresponse;

import app.model.Book;

public class Response {
  private Book[] results;

  public Response(Book[] results) {
    this.results = results;
  }

  Response() {
  }

  public Book[] getResults() {
    return results;
  }

  public void setResults(Book[] results) {
    this.results = results;
  }
}
