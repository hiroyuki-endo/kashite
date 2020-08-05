package com.example.kashite.domain.book;

import com.example.kashite.domain.book.exception.AlreadyDeregisteredException;
import com.example.kashite.domain.book.exception.NoLendingException;
import com.example.kashite.domain.book.exception.NowLendingException;

public enum BookStatus {
    READY,
    LENDING,
    DEREGISTERED;

    void canLend() {
        switch (this) {
            case LENDING:
                throw new NowLendingException();
            case DEREGISTERED:
                throw new AlreadyDeregisteredException();
        }
    }

    void canReturn() {
        switch (this) {
            case READY:
                throw new NoLendingException();
            case DEREGISTERED:
                throw new AlreadyDeregisteredException();
        }
    }

    void canDeregister() {
        switch (this) {
            case LENDING:
                throw new NowLendingException();
            case DEREGISTERED:
                throw new AlreadyDeregisteredException();
        }
    }
}
