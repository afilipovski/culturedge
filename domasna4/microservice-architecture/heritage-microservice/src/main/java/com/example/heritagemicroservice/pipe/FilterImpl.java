package com.example.heritagemicroservice.pipe;


import com.example.heritagemicroservice.filters.Filter;

public class FilterImpl<I, O, T> implements Filter<I, T> {

    private final Filter<I, O> current;
    private final Filter<O, T> next;

    FilterImpl(Filter<I, O> current, Filter<O, T> next) {
        this.current = current;
        this.next = next;
    }

    public T execute(I input) {
        return next.execute(current.execute(input));
    }

}

