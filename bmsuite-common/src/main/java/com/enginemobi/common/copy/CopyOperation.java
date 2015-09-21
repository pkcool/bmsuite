package com.enginemobi.common.copy;

public interface CopyOperation<T, G extends Exception> {

    T execute(T original) throws G;

}
