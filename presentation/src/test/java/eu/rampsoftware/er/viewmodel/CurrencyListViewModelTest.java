package eu.rampsoftware.er.viewmodel;


import com.fernandocejas.arrow.optional.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Date;
import java.util.HashMap;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesRatesDate;
import eu.rampsoftware.er.domain.usecases.GetCurrenciesUseCase;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;
import io.reactivex.observers.DisposableObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CurrencyListViewModelTest {
    public static final long MILLIS_22_01_2016__10_00 = 1453453200000L;
    CurrencyListViewModel viewModel;
    @Mock
    GetCurrenciesUseCase mGetCurrenciesUseCaseMock;

    @Mock
    GetCurrenciesRatesDate mGetCurrenciesRatesDateMock;

    @Captor
    ArgumentCaptor<DisposableObserver<Optional<Date>>> mDateObserverCaptor;

    @Captor
    ArgumentCaptor<DisposableObserver<CurrencyData>> mCurrenciesObserverCaptor;

    @Captor
    ArgumentCaptor<GetCurrenciesUseCase.CurrenciesParam> mCurrenciesParamCaptor;

    @Before
    public void setUp() {
        initMocks(this);
        viewModel = new CurrencyListViewModel(mGetCurrenciesUseCaseMock, mGetCurrenciesRatesDateMock);
    }

    @Test
    public void thatProgressDisplayedOnLoad() {
        Assert.assertThat(viewModel.isProgressVisible(), is(false));
        viewModel.onLoad();
        Assert.assertThat(viewModel.isProgressVisible(), is(true));
    }

    @Test
    public void thatRequestForDatePerformedOnLoad() {
        viewModel.onLoad();
        verify(mGetCurrenciesRatesDateMock).run(mDateObserverCaptor.capture());
    }

    @Test
    public void thatRequestForDataPerformedOnNextDate() {
        viewModel.onNextDay();
        verify(mGetCurrenciesUseCaseMock).run(mCurrenciesObserverCaptor.capture(),
                mCurrenciesParamCaptor.capture());
    }

    @Test
    public void thatRequestForCurrenciesPerformedWhenDateRetrieved() {
        viewModel.onLoad();
        verify(mGetCurrenciesRatesDateMock).run(mDateObserverCaptor.capture());
        final DisposableObserver<Optional<Date>> observer = mDateObserverCaptor.getValue();
        observer.onNext(Optional.of(new Date(MILLIS_22_01_2016__10_00)));
        verify(mGetCurrenciesUseCaseMock).run(mCurrenciesObserverCaptor.capture(),
                mCurrenciesParamCaptor.capture());
    }

    @Test
    public void thatDayAddedOnNextClick() {
        viewModel.onLoad();
        verify(mGetCurrenciesRatesDateMock).run(mDateObserverCaptor.capture());
        final DisposableObserver<Optional<Date>> observer = mDateObserverCaptor.getValue();
        observer.onNext(Optional.of(new Date(MILLIS_22_01_2016__10_00)));
        verify(mGetCurrenciesUseCaseMock).run(mCurrenciesObserverCaptor.capture(),
                mCurrenciesParamCaptor.capture());

        viewModel.onNextDay();

        assertThat(viewModel.getDateText(), is("2016-01-23"));
    }

    @Test
    public void thatDaySubtractedOnPreviousClick() {
        viewModel.onLoad();
        verify(mGetCurrenciesRatesDateMock).run(mDateObserverCaptor.capture());
        final DisposableObserver<Optional<Date>> observer = mDateObserverCaptor.getValue();
        observer.onNext(Optional.of(new Date(MILLIS_22_01_2016__10_00)));
        verify(mGetCurrenciesUseCaseMock).run(mCurrenciesObserverCaptor.capture(),
                mCurrenciesParamCaptor.capture());

        viewModel.onPreviousDay();

        assertThat(viewModel.getDateText(), is("2016-01-21"));
    }

    @Test
    public void thatProgressVisbleOnNextClick(){
        Assert.assertThat(viewModel.isProgressVisible(), is(false));
        viewModel.onNextDay();
        Assert.assertThat(viewModel.isProgressVisible(), is(true));
    }

    @Test
    public void thatProgressVisibleOnPreviousClick(){
        Assert.assertThat(viewModel.isProgressVisible(), is(false));
        viewModel.onNextDay();
        Assert.assertThat(viewModel.isProgressVisible(), is(true));
    }

    @Test
    public void thatProgressDismissedOnDataRetrieved() {
        viewModel.onLoad();
        verify(mGetCurrenciesRatesDateMock).run(mDateObserverCaptor.capture());
        final DisposableObserver<Optional<Date>> observer = mDateObserverCaptor.getValue();
        observer.onNext(Optional.of(new Date(MILLIS_22_01_2016__10_00)));
        verify(mGetCurrenciesUseCaseMock).run(mCurrenciesObserverCaptor.capture(),
                mCurrenciesParamCaptor.capture());
        final DisposableObserver<CurrencyData> currenciesObserver = mCurrenciesObserverCaptor.getValue();
        Assert.assertThat(viewModel.isProgressVisible(), is(true));

        currenciesObserver.onNext(new CurrencyData(new Date(), new HashMap<>(), "USD"));

        Assert.assertThat(viewModel.isProgressVisible(), is(false));
    }

    @Test
    public void thatObserversDisposedOnViewModelDispose(){
        viewModel.onLoad();
        verify(mGetCurrenciesRatesDateMock).run(mDateObserverCaptor.capture());
        final DisposableObserver<Optional<Date>> observer = mDateObserverCaptor.getValue();
        observer.onNext(Optional.of(new Date(MILLIS_22_01_2016__10_00)));

        viewModel.dispose();

        verify(mGetCurrenciesUseCaseMock).dispose();
        verify(mGetCurrenciesRatesDateMock).dispose();
    }

    @Test
    public void thatRequestPerformedOnRefresh(){
        viewModel.performRefresh();

        verify(mGetCurrenciesUseCaseMock).run(mCurrenciesObserverCaptor.capture(),
                mCurrenciesParamCaptor.capture());
    }

    @Test
    public void thatProgressVisibleOnRefresh(){
        viewModel.performRefresh();

        Assert.assertThat(viewModel.isProgressVisible(), is(true));
    }
}
