package com.vie.hors.ke;

import com.vie.hors.ZeroRunException;

import java.text.MessageFormat;

public class EmptyStreamException extends ZeroRunException {

    public EmptyStreamException(final String filename) {
        super(MessageFormat.format(Message.NIL_MSG, filename, null));
        
    }

    public EmptyStreamException(final String filename, final Throwable ex) {
        super(MessageFormat.format(Message.NIL_MSG, filename, ex.getCause()));
    }
}
