package com.example.heritagemicroservice.pipe;


import com.example.heritagemicroservice.filters.Filter;

// Class representing a pipeline of filters to process data.
public class Pipe<I, O> {

    private final Filter<I, O> current;

    public Pipe(Filter<I, O> current) {
        this.current = current;
    }

    public <P> Pipe<I, P> chain(Filter<O, P> current) {
        return new Pipe<>(new FilterImpl<>(this.current, current));
    }

    public O process(I input) {
        return current.execute(input);
    }
}