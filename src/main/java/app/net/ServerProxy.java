package app.net;

import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerProxy {

  //TODO:: ADD STUDENT URL HERE
  protected static final String API_STRING = "https://r0j8m56kb7.execute-api.us-west-2.amazonaws.com/dev";
  private static final int TIMEOUT_MILLIS = 60000;

  private static ServerProxy instance;

  private ServerProxy() {
  }

  public static ServerProxy getInstance() {
    if (instance == null) {
      instance = new ServerProxy();
    }
    return instance;
  }

  public Response doRequest(Request request, String pathPart) throws IOException {

    HttpURLConnection connection = null;

    try {
      URL url = getUrl(pathPart);
      connection = (HttpURLConnection) url.openConnection();
      connection.setReadTimeout(TIMEOUT_MILLIS);
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);

      String entityBody = JsonSerializer.serialize(request);
      DataOutputStream os = new DataOutputStream(connection.getOutputStream());
      os.writeBytes(entityBody);
      os.flush();

      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("An error occurred.  Response code = "
            + connection.getResponseCode());
      }
      String responseString = getResponse(connection.getInputStream());
      return JsonSerializer.deserialize(responseString, Response.class);

    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  private URL getUrl(String urlPath) throws MalformedURLException {
    String urlString = API_STRING + (urlPath.startsWith("/") ? "" : "/") + urlPath;
    return new URL(urlString);
  }

  private String getResponse(InputStream inputStream) throws IOException {
    if (inputStream == null) {
      return null;
    } else {
      try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }

        return response.toString();
      }
    }
  }
}
