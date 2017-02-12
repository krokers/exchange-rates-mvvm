package eu.rampsoftware.er.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Separated root component configuration. In general, daggers components should not be needed for
 * unit tests, as mocked dependencies could be passed by constructor. This component should be used
 * rather in integration tests.
 *
 */
@Component(modules = TestApplicationModule.class)
@Singleton
public interface TestApplicationComponent extends ApplicationComponent{

    TestCurrenciesActivitySubComponent newCurrenciesActivitySubComponent(TestCurrenciesActivityModule module);
}
