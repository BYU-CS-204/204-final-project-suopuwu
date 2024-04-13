package app.service.lambda;

import org.springframework.stereotype.Component;

@Component("authorService")
public class LambdaAuthorService extends LambdaService {
  @Override
  protected String getPathPart() {
    return "/author";
  }
}
