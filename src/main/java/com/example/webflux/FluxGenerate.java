package com.example.webflux;

import reactor.core.publisher.Flux;

public class FluxGenerate {

    public static void main(String[] args) {
        Flux<BookDto> bookFlux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    BookDto book = generateBook(state);
                    sink.next(book);
                    if (state >= 5) {
                        sink.complete();
                    }
                    return state + 1;
                });

        bookFlux.subscribe(System.out::println);
    }

    private static BookDto generateBook(int index) {
        return new BookDto("Title" + index, "Author" + index, 2000);
    }

}
