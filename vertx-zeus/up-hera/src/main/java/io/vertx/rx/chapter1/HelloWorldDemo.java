package io.vertx.rx.chapter1;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class HelloWorldDemo {

    public static void main(final String[] args) {
        /**
         * Hello World Completed
         */
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> event)
                    throws Exception {
                event.onNext("Hello World");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(final String s) throws Exception {
                System.out.println(s);
            }
        });

        /**
         * Hello World Version 2
         */
        Observable.just("Hello World").subscribe(new Consumer<String>() {
            @Override
            public void accept(final String s) throws Exception {
                System.out.println(s);
            }
        });
        /**
         * Hello World Version 3 - Lambda
         */
        Observable.just("Hello World").subscribe(System.out::println);
    }
}
