package eu.rampsoftware.er;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import eu.rampsoftware.er.di.DaggerTestApplicationComponent;
import eu.rampsoftware.er.di.TestApplicationComponent;
import eu.rampsoftware.er.di.TestApplicationModule;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {


    private TestApplicationComponent component;

    @Before
    public void setUp() {
        initMocks(this);
        TestApplicationModule testApplicationModule = new TestApplicationModule();
        component = DaggerTestApplicationComponent.builder().testApplicationModule(testApplicationModule).build();
    }


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}