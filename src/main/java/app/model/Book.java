package app.model;

import java.util.Arrays;

public class Book {
  private String title;
  private String author;
  private String[] topics;

  public Book(String title, String author, String[] topics) {
    this.title = title;
    this.author = author;
    this.topics = topics;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String[] getTopics() {
    return topics;
  }

  public void setTopics(String[] topics) {
    this.topics = topics;
  }

  @Override
  public String toString() {
    return "{"
        + "title='" + title + '\''
        + ", author='" + author + '\''
        + ", topics=" + Arrays.toString(topics)
        + '}';
  }
}
