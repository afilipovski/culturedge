package com.example.heritagemicroservice.filters;

/**
 * The Filter interface represents a handler in the Chain of Responsibility pattern.
 * It defines a common method for executing the filter logic.
 *
 * @param <I> Type of the input data.
 * @param <O> Type of the output data.
 */
public interface Filter<I, O> {
    /**
     * Executes the filter logic on the input data.
     *
     * @param input The input data to be filtered.
     * @return The filtered output data.
     */
    O execute(I input);

}