package eu.rampsoftware.er.data.datasource.local;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.realm.Realm;

public abstract class RealmManagerBase {


    public RealmManagerBase() {

    }

    protected <T> Observable<T> readFromRealm(Callable<T> callable) {
        return Observable.create(e -> {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            callable.execute(realm, e);
        } finally {
            if (realm != null) {
                realm.commitTransaction();
                realm.close();
            }
        }
        });
    }

    protected <T> T readFromRealmSync(SyncCallable<T> callable) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            return callable.execute(realm);
        } finally {
            if (realm != null) {
                realm.commitTransaction();
                realm.close();
            }
        }
    }


    protected void executeInTransaction(Realm.Transaction callable) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(callable);
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }


    protected interface Callable<T> {
        void execute(Realm realm, Emitter<T> emitter);
    }

    protected interface SyncCallable<T> {
        T execute(Realm realm);
    }

}
