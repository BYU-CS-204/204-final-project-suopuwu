package app.service.lambda;

import org.springframework.stereotype.Component;

@Component("titleService")
public class LambdaTitleService extends LambdaService {
  @Override
  protected String getPathPart() {
    return "/title";
  }
}
