package filters;

public interface Filter<I, O> {
    O execute(I input);
}
