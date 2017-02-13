package eu.rampsoftware.er.domain.usecases;

import com.fernandocejas.arrow.optional.Optional;

import java.util.Date;

import eu.rampsoftware.er.data.PreferencesData;
import eu.rampsoftware.er.domain.NoArgQueryUseCase;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Returns the last date of currencies rates that were requested via {@link GetCurrenciesRatesDate}.
 * This allows to achieve the functionality of persisting the last setting of the application.
 */
public class GetCurrenciesRatesDate extends NoArgQueryUseCase<Optional<Date>> {
    private final PreferencesData mPreferencesData;

    public GetCurrenciesRatesDate(final Scheduler workScheduler,
                                  final Scheduler observeScheduler,
                                  final PreferencesData preferencesData) {
        super(workScheduler, observeScheduler);
        mPreferencesData = preferencesData;
    }

    @Override
    protected Observable<Optional<Date>> buildUseCaseObservable() {
        return Observable.just(mPreferencesData.getCurrenciesExchangeDate());

    }
}
