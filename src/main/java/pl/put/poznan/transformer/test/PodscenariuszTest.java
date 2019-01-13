package pl.put.poznan.transformer.test;
import pl.put.poznan.transformer.app.Krok;
import pl.put.poznan.transformer.app.Podscenariusz;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PodscenariuszTest {
    private Podscenariusz podscenariusz;

    @org.junit.Before
    public void setUp() {
        podscenariusz = new Podscenariusz();
    }

    @org.junit.Test
    public void mockTest1() {
        Krok mock = mock(Krok.class);
        podscenariusz.getListaKrokow().add(mock);
        when(mock.ileSlowWKroku()).thenReturn(5);
        assertEquals(5, podscenariusz.ileSlowWPodscenariuszu(), 0);
    }



}