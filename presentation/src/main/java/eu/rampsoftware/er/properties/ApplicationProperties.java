package eu.rampsoftware.er.properties;

public interface ApplicationProperties {
    String baseUrl();

    boolean logsEnabled();

    String getOerAppId();

    String sharedPreferencesName();
}
