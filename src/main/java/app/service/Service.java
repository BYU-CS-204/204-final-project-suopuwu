package app.service;

import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import java.io.IOException;

public interface Service {
  Response run(Request request) throws IOException;
}
