package eu.rampsoftware.er.domain.usecases;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.security.InvalidParameterException;
import java.util.Date;

import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.SingleValue;
import eu.rampsoftware.er.domain.TestDisposableObserver;
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase.CurrencySeriesParam;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetCurrencySeriesUseCaseTest {

    private static final long MILLIS_22_01_2016__10_00 = 1453453200000L;
    private static final long MILLIS_24_01_2016__10_00 = 1453593600000L;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    CurrencyRepository mRepositoryMock;
    private GetCurrencySeriesUseCase mUseCase;
    private TestDisposableObserver<SingleValue> mTestObserver;

    @Before
    public void setUp() {
        initMocks(this);
        this.mTestObserver = new TestDisposableObserver<>();
        mUseCase = new GetCurrencySeriesUseCase(new TestScheduler(), new TestScheduler(), mRepositoryMock);
    }

    @Test()
    public void thatPreconditionFailsIfDateRangeEmpty() {
        exception.expect(InvalidParameterException.class);
        new CurrencySeriesParam(
                new Date(MILLIS_24_01_2016__10_00),
                new Date(MILLIS_22_01_2016__10_00),
                "PLN");
    }

    @Test()
    public void thatPreconditionFailsIfDateFromIsNull() {
        exception.expect(NullPointerException.class);
        new CurrencySeriesParam(
                null,
                new Date(MILLIS_22_01_2016__10_00),
                "PLN");

    }

    @Test()
    public void thatPreconditionFailsIfDateToIsNull() {
        exception.expect(NullPointerException.class);
        new CurrencySeriesParam(
                new Date(MILLIS_22_01_2016__10_00),
                null,
                "PLN");

    }

    @Test()
    public void thatPreconditionFailsIfCurrencyCodeIsNull() {
        exception.expect(NullPointerException.class);
        new CurrencySeriesParam(
                new Date(MILLIS_22_01_2016__10_00),
                new Date(MILLIS_24_01_2016__10_00),
                null);

    }

    @Test()
    public void thatPreconditionFailsIfCurrencyCodeIsEmpty() {
        exception.expect(InvalidParameterException.class);
        new CurrencySeriesParam(
                new Date(MILLIS_22_01_2016__10_00),
                new Date(MILLIS_24_01_2016__10_00),
                "");
    }

    @Test()
    public void thatRepositoryRequestPerformed() {
        when(mRepositoryMock.getSeries(any(Date.class), any(Date.class), anyString()))
                .thenReturn(Observable.empty());
        final Date from = new Date(MILLIS_22_01_2016__10_00);
        final Date to = new Date(MILLIS_24_01_2016__10_00);
        CurrencySeriesParam params = new CurrencySeriesParam(from, to, "PLN");

        mUseCase.run(params);

        verify(mRepositoryMock).getSeries(from, to, "PLN");
    }
}
