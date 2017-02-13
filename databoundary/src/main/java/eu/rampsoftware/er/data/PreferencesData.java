package eu.rampsoftware.er.data;

/**
 * Created by Ramps on 2017-02-13.
 */

import com.fernandocejas.arrow.optional.Optional;

import java.util.Date;

/**
 * Interface for module responsible for persisting scalar user preferences.
 */
public interface PreferencesData {
    void setCurrenciesExchangeDate(Date date);
    Optional<Date> getCurrenciesExchangeDate();
}
