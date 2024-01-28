package com.example.heritagemicroservice.filters;

// Interface defining a generic filter with input and output types.
public interface Filter<I, O> {

    O execute(I input);

}