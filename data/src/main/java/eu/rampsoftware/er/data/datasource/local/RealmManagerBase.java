package eu.rampsoftware.er.data.datasource.local;

import io.realm.Realm;

public abstract class RealmManagerBase {


    public RealmManagerBase() {

    }

    protected <T> T readFromRealm(Callable<T> callable) {
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
        T execute(Realm realm);
    }

}
