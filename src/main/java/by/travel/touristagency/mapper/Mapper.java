package by.travel.touristagency.mapper;

public interface Mapper<F, T> {
    T map(F object);
}
