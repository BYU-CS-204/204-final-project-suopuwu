package app.net.lambda;

import app.model.Book;
import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class TopicSearchHandler implements RequestHandler<Request, Response> {
  @Override
  public Response handleRequest(Request request, Context context) {
    Book[] data = FakeData.getFakeData();
    return new Response(data);
  }
}
