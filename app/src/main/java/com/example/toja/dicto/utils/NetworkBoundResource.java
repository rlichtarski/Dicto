package com.example.toja.dicto.utils;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

import java.util.Collection;

import io.reactivex.Completable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<Data> {

    private CompositeDisposable disposables = new CompositeDisposable();
    private ObservableEmitter<Resource<Data>> emitter;

    public NetworkBoundResource(ObservableEmitter<Resource<Data>> emitter) {
        this.emitter = emitter;
        onStart();
    }

    private void onStart() {
        emitter.onNext(Resource.loading(null));
        createCall()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Data response) {
                        storeThenPass(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        tryLoadOfflineData();
                    }
                });
    }

    private void storeThenPass(Data response) {
        disposables.add(
                saveCallResult(response)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> disposables.add(
                                loadFromDb()
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.io())
                                        .map(data -> Resource.success(data,"success fresh data from api"))
                                        .subscribe(value -> {
                                            emitter.onNext(value);
                                            onFinished();
                                        })
                        )));
    }

    private void tryLoadOfflineData() {
        loadFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(Data data) {
                        emitter.onNext(isAnEmptyCollection(data) ? Resource.error("missing data", null) : Resource.success(data, "non-fresh data from db"));
                        onFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onNext(Resource.error(e.getLocalizedMessage(), null));
                        onFinished();
                    }
                });
    }

    private boolean isAnEmptyCollection(Object obj) {
        return obj instanceof Collection && ((Collection) obj).isEmpty();
    }

    private void onFinished() {
        emitter.onComplete();
        disposables.clear();
    }

    @MainThread
    protected abstract Single<Data> createCall();

    @WorkerThread
    protected abstract Completable saveCallResult(Data response);

    @MainThread
    protected abstract Single<Data> loadFromDb();

}
