package org.example.concurrency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SynchronizedMethods {

    private int sum = 0;

    public synchronized void calculate() {
        setSum(getSum() + 1);
    }

    // standard setters and getters
}

