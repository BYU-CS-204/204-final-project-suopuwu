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

    /*TODO: Write tests to assure that the Presenter and View classes interact as expected.  If the services throw an error,
    *  the view.displayErrorMessage() function should be called inside the presenter class.  You will need to use
    *  mock objects to effectively test the presenter.
    */
    private MainPresenter classUnderTest;

    @BeforeEach
    void setUp(){
        I_MainView view = Mockito.mock(I_MainView.class);
        Service service = Mockito.mock(Service.class);

        try {
            Mockito.when(service.run(new Request("Exception"))).thenThrow(IOException.class);
        } catch (IOException ignored) {

        }

        classUnderTest = new MainPresenter(view, service, service, service);

    }

    @Test
    void shouldPrintErrorMessage_whenReceiveInvalidResponse() throws IOException {
        classUnderTest.titleSearch("Exception");
        Mockito.verify(classUnderTest.getTitleService(), Mockito.times(1)).run(Mockito.any());

        Mockito.verify(classUnderTest.getView(), Mockito.times(1)).displayErrorMessage(Mockito.anyString());
    }


}