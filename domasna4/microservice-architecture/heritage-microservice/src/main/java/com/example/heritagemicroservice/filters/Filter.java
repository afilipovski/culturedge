package com.example.heritagemicroservice.filters;

public interface Filter<I, O> {

    O execute(I input);

}