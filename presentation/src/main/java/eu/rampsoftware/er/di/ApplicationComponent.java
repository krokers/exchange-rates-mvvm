package eu.rampsoftware.er.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Root component of dependency graph. Since dependent components are defined as {@link dagger.Subcomponent Subcomponent},
 * they need to be explicitly declared in this parent component see
 * {@link ApplicationComponent#newCurrenciesActivitySubComponent(CurrenciesActivityModule) newCurrenciesActivitySubComponent} method.
 * <br>
 * An alternative solution not used here. Dependent components could be defined
 * in <code>dependencies</code> parameter of {@link Component} annotation. In such case, this root component would need
 * to expose dependencies to the downstream components (all dependencies provided by this component that are used by downstream components)
 */
@Component(modules = {ApplicationModule.class, NetModule.class})
@Singleton
public interface ApplicationComponent {
    /**
     * Creates new instance of CurrenciesActivitySubComponent {@link dagger.Subcomponent Subcomponent}.
     *
     * @param module
     * @return
     */
    CurrenciesActivitySubComponent newCurrenciesActivitySubComponent(CurrenciesActivityModule module);
    CurrencyDetailsActivitySubComponent newCurrencyDetailsActivitySubComponent(CurrencyDetailsActivityModule module);
}
