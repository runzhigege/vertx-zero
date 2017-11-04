package com.vie.hors.ke;

import com.vie.hors.ZeroRunException;

import java.text.MessageFormat;

public class ErrorMissingException extends ZeroRunException {

    public ErrorMissingException(final Integer code, final String clazz) {
        super(MessageFormat.format(Message.ECODE_MSG, code, clazz));
    }
}
