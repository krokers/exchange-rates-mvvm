package eu.rampsoftware.er.domain.usecases;

import java.util.Currency;
import java.util.Date;
import java.util.List;

import eu.rampsoftware.er.data.CurrencyData;
import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.domain.QueryUseCase;
import eu.rampsoftware.er.domain.pojo.CurrencyHome;
import eu.rampsoftware.er.domain.pojo.mapper.CurrencyMapper;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Ramps on 2017-02-06.
 */

public class GetCurrenciesUseCase extends QueryUseCase<CurrencyData, GetCurrenciesUseCase.CurrenciesParam> {

    private final CurrencyRepository mRepository;

    public GetCurrenciesUseCase(final Scheduler workScheduler,
                                final Scheduler observeScheduler,
                                CurrencyRepository repository) {
        super(workScheduler, observeScheduler);
        mRepository = repository;
    }

    @Override
    protected Observable<CurrencyData> buildUseCaseObservable(final CurrenciesParam params) {
        return mRepository.getCurrencies(params.date);
    }

    public static class CurrenciesParam {
        public CurrenciesParam(final Date date) {
            this.date = date;
        }

        public Date date;
    }
}
