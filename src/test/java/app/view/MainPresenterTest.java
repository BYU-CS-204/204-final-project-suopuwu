package app.view;

import app.model.Book;
import app.model.requestresponse.Request;
import app.model.requestresponse.Response;
import app.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainPresenterTest {
    private MainPresenter presenter;

    @BeforeEach
    void setUp(){
        I_MainView view = Mockito.mock(I_MainView.class);
        Service service = Mockito.mock(Service.class);
        Response exampleResponse = new Response(new Book[]{new Book("title", "author", new String[]{"topic1", "topic2"})});

        try {
            Mockito.when(service.run(new Request("exception"))).thenThrow(IOException.class);
            Mockito.when(service.run(new Request("valid"))).thenReturn(exampleResponse);
        } catch (IOException ignored) {

        }

        presenter = new MainPresenter(view, service, service, service);

    }

    @Test
    void titleSearchShowsResults_whenValidSearch() throws IOException {
        String input = "valid";

        presenter.titleSearch(input);
        Mockito.verify(presenter.getTitleService(), Mockito.times(1)).run(new Request(input));
        Mockito.verify(presenter.getView(), Mockito.times(1)).displayTitleSearchResults(Mockito.any());

    }

    @Test
    void titleSearchPrintsError_whenServiceFails() throws IOException {
        String input = "exception";

        presenter.titleSearch(input);
        Mockito.verify(presenter.getTitleService(), Mockito.times(1)).run(new Request(input));
        Mockito.verify(presenter.getView(), Mockito.times(1)).displayErrorMessage(Mockito.anyString());
    }

    @Test
    void authorSearchShowsResults_whenValidSearch() throws IOException {
        String input = "valid";

        presenter.authorSearch(input);
        Mockito.verify(presenter.getTitleService(), Mockito.times(1)).run(new Request(input));
        Mockito.verify(presenter.getView(), Mockito.times(1)).displayAuthorSearchResults(Mockito.any());

    }

    @Test
    void authorSearchPrintsError_whenServiceFails() throws IOException {
        String input = "exception";

        presenter.authorSearch(input);
        Mockito.verify(presenter.getAuthorService(), Mockito.times(1)).run(new Request(input));
        Mockito.verify(presenter.getView(), Mockito.times(1)).displayErrorMessage(Mockito.anyString());
    }

    @Test
    void topicSearchShowsResults_whenValidSearch() throws IOException {
        String input = "valid";

        presenter.topicSearch(input);
        Mockito.verify(presenter.getTopicService(), Mockito.times(1)).run(new Request(input));
        Mockito.verify(presenter.getView(), Mockito.times(1)).displayTopicSearchResults(Mockito.any());

    }

    @Test
    void topicSearchPrintsError_whenServiceFails() throws IOException {
        String input = "exception";

        presenter.topicSearch(input);
        Mockito.verify(presenter.getTitleService(), Mockito.times(1)).run(new Request(input));
        Mockito.verify(presenter.getView(), Mockito.times(1)).displayErrorMessage(Mockito.anyString());
    }


}