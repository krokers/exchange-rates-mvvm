package eu.rampsoftware.er.data;


import com.fernandocejas.arrow.optional.Optional;

import java.util.Date;

/**
 * Interface for module responsible for persisting scalar user preferences.
 */
public interface PreferencesData {
    void setCurrenciesExchangeDate(Date date);
    Optional<Date> getCurrenciesExchangeDate();
}
