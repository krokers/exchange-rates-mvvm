package eu.rampsoftware.er.domain.usecases;

import java.security.InvalidParameterException;
import java.util.Date;

import eu.rampsoftware.er.data.CurrencyRepository;
import eu.rampsoftware.er.data.SingleValue;
import eu.rampsoftware.er.domain.QueryUseCase;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

public class GetCurrencySeriesUseCase extends QueryUseCase<SingleValue, GetCurrencySeriesUseCase.CurrencySeriesParam> {

    private final CurrencyRepository mRepository;

    public GetCurrencySeriesUseCase(final Scheduler workScheduler,
                                    final Scheduler observeScheduler,
                                    CurrencyRepository repository) {
        super(workScheduler, observeScheduler);
        mRepository = repository;

    }

    @Override
    protected Observable<SingleValue> buildUseCaseObservable(final CurrencySeriesParam params) {
        return mRepository.getSeries(params.from, params.to, params.currencyCode);
    }

    public static class CurrencySeriesParam {
        Date from;
        Date to;
        String currencyCode;

        public CurrencySeriesParam(final Date from,
                                   final Date to,
                                   final String currencyCode) {
            this.from = checkNotNull(from);
            this.to = checkNotNull(to);
            this.currencyCode = checkNotNull(currencyCode);
            if(currencyCode.isEmpty()){
                throw new InvalidParameterException("CURRENCY CODE parameter cannot be empty");
            }
            if(from.getTime() > to.getTime()){
                throw new InvalidParameterException("START date cannot be after TO date.");
            }
        }
    }
}

