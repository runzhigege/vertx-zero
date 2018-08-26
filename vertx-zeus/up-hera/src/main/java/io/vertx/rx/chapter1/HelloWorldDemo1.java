package io.vertx.rx.chapter1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class HelloWorldDemo1 {

    public static void main(final String[] args) {
        // Another version of Hello World
        Observable.just("Hello World")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(final String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete()");
                    }
                });
        // Another version of Hello World
        Observable.just("Hello World")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(final String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete()");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(final Disposable disposable) throws Exception {
                        System.out.println("subscribe");
                    }
                });
        // Observer instead
        Observable.just("Hello World")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(final Disposable disposable) {
                        System.out.println("subscribe");
                    }

                    @Override
                    public void onNext(final String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete()");
                    }
                });
    }
}
