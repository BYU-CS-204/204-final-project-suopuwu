package app.service.local;

import app.model.Book;
import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import app.service.Service;

public class LocalTitleService implements Service {
  @Override
  public Response run(Request request) {
    Book book1 = new Book(
        "Intro to Java vol. 1",
        "Garrett Price",
        new String[]{"Computer Science"});
    Book book2 = new Book("Intro to Java vol. 2",
        "Garrett Price",
        new String[]{"Computer Science"});

    return new Response(new Book[]{book1, book2});
  }
}
