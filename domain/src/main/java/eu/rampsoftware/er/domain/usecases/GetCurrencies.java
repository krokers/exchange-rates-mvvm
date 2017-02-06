package eu.rampsoftware.er.domain.usecases;

import java.util.Currency;
import java.util.List;

import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.domain.QueryUseCase;
import eu.rampsoftware.er.domain.pojo.CurrencyHome;
import eu.rampsoftware.er.domain.pojo.mapper.CurrencyMapper;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Ramps on 2017-02-06.
 */

public class GetCurrencies extends QueryUseCase<List<CurrencyHome>, GetCurrencies.CurrenciesParam> {

    private final CurrencyRepository mRepository;


    GetCurrencies(final Scheduler workScheduler, final Scheduler observeScheduler, CurrencyRepository repository) {
        super(workScheduler, observeScheduler);
        mRepository = repository;
    }

    @Override
    protected Observable<List<CurrencyHome>> buildUseCaseObservable(final CurrenciesParam params) {
        return mRepository.getCurrencies(params.currency).map(CurrencyMapper.mapper);
    }

    public static class CurrenciesParam {
        public Currency currency;
    }
}
