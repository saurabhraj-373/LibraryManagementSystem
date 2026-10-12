package com.library.LMS;

public interface Lendable {

    boolean lend(User user);

    void returnItem(User user);

    boolean isAvailable();
}
