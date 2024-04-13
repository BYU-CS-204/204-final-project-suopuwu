package monitor;

import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;
import java.io.IOException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PrometheusAspect {

  Summary titleTimer = Summary.build()
      .namespace("java")
      .name("title_search_timer")
      .help("Measures the time it takes to run a title search")
      .register();


  @Pointcut("execution(* *.displayWelcome(..))")
  public void presenterStart() {
  }

  @Pointcut("execution(* *.titleSearch(String))")
  public void titleSearch() {
  }

  @Pointcut("execution(* *.authorSearch(String))")
  public void authorSearch() {
  }

  @Pointcut("execution(* *.topicSearch(String))")
  public void topicSearch() {
  }

  //TODO: Create advices to start the Prometheus exporter and the three timers

}
