package eu.rampsoftware.er.di;


import dagger.Subcomponent;

@Subcomponent(modules = TestCurrenciesActivityModule.class)
interface TestCurrenciesActivitySubComponent extends CurrenciesActivitySubComponent {
}
