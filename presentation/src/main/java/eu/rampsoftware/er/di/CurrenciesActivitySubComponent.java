package eu.rampsoftware.er.di;

import dagger.Subcomponent;
import eu.rampsoftware.er.view.currencies.CurrenciesActivity;

/**
 * Subcomponent of the dependency graph.
 * Subcomponents have their own life-cycle and can be garbage collected when all references to the subcomponent are gone.
 */
@CurrenciesActivityScope
@Subcomponent(modules={ CurrenciesActivityModule.class })
public interface CurrenciesActivitySubComponent {

    void inject(CurrenciesActivity currenciesActivity);
}