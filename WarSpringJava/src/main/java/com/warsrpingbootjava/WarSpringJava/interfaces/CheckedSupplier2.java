package com.warsrpingbootjava.WarSpringJava.interfaces;

public interface CheckedSupplier2 {

    @FunctionalInterface
    public interface CheckedSupplier<T> {
        T get() throws Exception;
    }
}
