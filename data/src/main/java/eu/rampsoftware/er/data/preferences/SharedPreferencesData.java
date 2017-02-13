package eu.rampsoftware.er.data.preferences;

import android.content.SharedPreferences;

import com.fernandocejas.arrow.optional.Optional;

import java.util.Date;

import eu.rampsoftware.er.data.PreferencesData;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

/**
 * Implementation of {@link PreferencesData} that persists the data in {@link SharedPreferences} storage.
 */
public class SharedPreferencesData implements PreferencesData {

    private static final String KEY_CURRENCIES_EXCHANGE_DATE = "KEY_CURRENCIES_EXCHANGE_DATE";
    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesData(final SharedPreferences sharedPreferences) {
        mSharedPreferences = checkNotNull(sharedPreferences);
    }

    @Override
    public Optional<Date> getCurrenciesExchangeDate() {
        return Optional.fromNullable(
                mSharedPreferences.contains(KEY_CURRENCIES_EXCHANGE_DATE) ?
                        new Date(mSharedPreferences.getLong(KEY_CURRENCIES_EXCHANGE_DATE, 0)) : null
        );
    }

    @Override
    public void setCurrenciesExchangeDate(final Date date) {
        mSharedPreferences.edit().putLong(KEY_CURRENCIES_EXCHANGE_DATE, date.getTime()).apply();
    }
}
