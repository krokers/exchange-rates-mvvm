package eu.rampsoftware.er.data.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.datasource.CurrencyDataSource;
import eu.rampsoftware.er.data.repository.CachingCurrencyRepository;
import io.reactivex.Observable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CachingCurrencyRepositoryTest {
    public static final long MILLIS_22_01_2016__10_00 = 1453453200000L;
    public static final long MILLIS_24_01_2016__10_00 = 1453593600000L;
    public static final long MILLIS_DAY = 24 * 3600 * 1000;
    @Mock
    CurrencyDataSource mLocalDataSource;
    @Mock
    CurrencyDataSource mRemoteDataSource;

    @Captor
    ArgumentCaptor<Date> mDateCaptor;

    @Captor
    ArgumentCaptor<CurrencyData> mCurrencyDataCaptor;

    private CachingCurrencyRepository mCurrencyRepository;

    @Before
    public void setUp() {
        initMocks(this);
        mCurrencyRepository = new CachingCurrencyRepository(mLocalDataSource, mRemoteDataSource);
    }

    @Test
    public void thatRequstToRemoteDataSourcePerfomedIfNoCacheAvailable() {
        when(mLocalDataSource.getCurrencies(any(Date.class))).thenReturn(Observable.empty());
        when(mRemoteDataSource.getCurrencies(any(Date.class)))
                .thenReturn(Observable.just(currencyData(MILLIS_22_01_2016__10_00)));

        mCurrencyRepository.getCurrencies(new Date(MILLIS_22_01_2016__10_00));

        verify(mRemoteDataSource).getCurrencies(mDateCaptor.capture());
    }

    @Test
    public void thatRequstToRemoteDataSourceNotPerfomedIfCacheAvailable() {
        when(mLocalDataSource.getCurrencies(any(Date.class)))
                .thenReturn(Observable.just(currencyData(MILLIS_22_01_2016__10_00)));

        mCurrencyRepository.getCurrencies(new Date(MILLIS_22_01_2016__10_00));

        verify(mRemoteDataSource, never()).getCurrencies(mDateCaptor.capture());
    }

    @Test
    public void thatRemoteDataCachedLocally() {
        when(mLocalDataSource.getCurrencies(any(Date.class))).thenReturn(Observable.empty());
        final CurrencyData currencyData = currencyData(MILLIS_22_01_2016__10_00);
        when(mRemoteDataSource.getCurrencies(any(Date.class)))
                .thenReturn(Observable.just(currencyData));

        mCurrencyRepository.getCurrencies(new Date(MILLIS_22_01_2016__10_00));

        verify(mLocalDataSource).storeCurrencies(currencyData);
    }

    @Test
    public void thatRemoteRequestPerformedOnNoCache() {
        when(mLocalDataSource.containsCurrencyValue(any(Date.class), anyString())).thenReturn(false);
        final Date startDate = new Date(MILLIS_22_01_2016__10_00);
        final Date endDate = new Date(MILLIS_24_01_2016__10_00);
        final CurrencyData currencyData = currencyData(MILLIS_22_01_2016__10_00);
        when(mRemoteDataSource.getCurrencies(any(Date.class)))
                .thenReturn(Observable.just(currencyData));

        mCurrencyRepository.getSeries(startDate, endDate, "PLN");

        verify(mRemoteDataSource, times(2)).getCurrencies(mDateCaptor.capture());
        final List<Date> requestedDates = mDateCaptor.getAllValues();
        assertThat(requestedDates.size(), is(2));
        assertThat(requestedDates.get(0).getTime(), is(MILLIS_22_01_2016__10_00));
        assertThat(requestedDates.get(1).getTime(), is(MILLIS_22_01_2016__10_00 + MILLIS_DAY));
    }

    @Test
    public void thatCurrencySeriesRequestCachedLocally() {
        when(mLocalDataSource.containsCurrencyValue(any(Date.class), anyString())).thenReturn(false);
        final Date startDate = new Date(MILLIS_22_01_2016__10_00);
        final Date endDate = new Date(MILLIS_24_01_2016__10_00);
        final CurrencyData currencyData = currencyData(MILLIS_22_01_2016__10_00);
        when(mRemoteDataSource.getCurrencies(any(Date.class)))
                .thenReturn(Observable.just(currencyData));

        mCurrencyRepository.getSeries(startDate, endDate, "PLN");

        verify(mLocalDataSource, times(2)).storeCurrencies(mCurrencyDataCaptor.capture());
    }


    private CurrencyData currencyData(long timestamp) {
        return new CurrencyData(new Date(timestamp), new HashMap<>(), "");
    }

}
