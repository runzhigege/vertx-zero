package io.vertx.up.web.serialization;

import io.vertx.up.epic.fn.Fn;
import io.vertx.up.exception._400FilePathMissingException;

import java.io.File;

public class FileSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String filename) {
        return Fn.getNull(() -> {
            final File file = new File(filename);
            // Throw 400 Error
            Fn.outWeb(!file.exists() || !file.canRead(), this.getLogger(),
                    _400FilePathMissingException.class, this.getClass(), filename);
            return file;
        }, paramType, filename);
    }
}
