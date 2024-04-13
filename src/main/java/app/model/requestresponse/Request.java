package app.model.requestresponse;

import java.util.Objects;

public class Request {
  private String queryString;

  public Request(String queryString) {
    this.queryString = queryString;
  }

  private Request() {

  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Request request = (Request) o;
    return Objects.equals(queryString, request.queryString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queryString);
  }
}
