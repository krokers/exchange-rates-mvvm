package eu.rampsoftware.er.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Ramps on 2017-02-12.
 */
@Scope
@Retention(value= RetentionPolicy.RUNTIME)
public @interface CurrenciesActivityScope {
}
