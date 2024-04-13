package app.service.lambda;

import org.springframework.stereotype.Component;

@Component("topicService")
public class LambdaTopicService extends LambdaService {
  @Override
  protected String getPathPart() {
    return "/topic";
  }
}
