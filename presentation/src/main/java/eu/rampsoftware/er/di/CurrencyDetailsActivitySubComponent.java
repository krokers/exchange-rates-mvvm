package eu.rampsoftware.er.di;

import dagger.Subcomponent;
import eu.rampsoftware.er.view.currencies.CurrencyDetailsActivity;

/**
 * Subcomponent of the dependency graph.
 * Subcomponents have their own life-cycle and can be garbage collected when all references to the subcomponent are gone.
 */
@CurrencyDetailsActivityScope
@Subcomponent(modules = {CurrencyDetailsActivityModule.class})
public interface CurrencyDetailsActivitySubComponent {

    void inject(CurrencyDetailsActivity activity);
}