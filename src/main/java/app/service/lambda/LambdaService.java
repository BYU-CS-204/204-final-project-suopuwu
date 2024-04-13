package app.service.lambda;

import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import app.net.ServerProxy;
import app.service.Service;
import java.io.IOException;

public abstract class LambdaService implements Service {

  protected ServerProxy getServerProxy() {
    return ServerProxy.getInstance();
  }

  public Response run(Request request) throws IOException {
    //TODO:: Add Prometheus monitoring here
    return getServerProxy().doRequest(request, getPathPart());
  }

  protected abstract String getPathPart();
}
