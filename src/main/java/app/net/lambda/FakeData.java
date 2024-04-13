package app.net.lambda;

import app.model.Book;

public class FakeData {
  public static Book[] getFakeData() {
    Book book1 = new Book("book1", "author1", new String[]{"topics", "topic"});
    Book book2 = new Book("book2", "author2", new String[]{"topics", "topic", "t"});
    Book book3 = new Book("book3", "author3", new String[]{"topics"});

    return new Book[]{book1, book2, book3};
  }
}
