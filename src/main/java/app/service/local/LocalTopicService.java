package app.service.local;

import app.model.Book;
import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import app.service.Service;
import java.io.IOException;

public class LocalTopicService implements Service {
  @Override
  public Response run(Request request) throws IOException {
    Book book1 = new Book("My Favorite Recipes",
        "George R. R. Martin",
        new String[]{"Cooking"});
    Book book2 = new Book("My Least Favorite Recipes",
        "Abraham Lincoln",
        new String[]{"Cooking", "Desserts"});

    return new Response(new Book[]{book1, book2});
  }
}
