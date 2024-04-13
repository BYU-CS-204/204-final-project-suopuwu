package app.service.local;

import app.model.Book;
import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import app.service.Service;
import java.io.IOException;

public class LocalAuthorService implements Service {
  @Override
  public Response run(Request request) throws IOException {
    Book book1 = new Book(
        "The Fellowship of the Ring",
        "J. R. R. Tolkien",
        new String[]{"Fantasy"});
    Book book2 = new Book("The Two Towers",
        "J. R. R. Tolkien",
        new String[]{"Fantasy"});

    return new Response(new Book[]{book1, book2});
  }
}
