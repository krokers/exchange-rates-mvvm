package eu.rampsoftware.er.viewmodel;

/**
 * Interface with a contract that needs to be fulfilled by view model classes.
 */
public interface BaseViewModel {
    /**
     * Invoked when view is initialized and is ready to display the data exposed by view model.
     */
    void onLoad();
}
