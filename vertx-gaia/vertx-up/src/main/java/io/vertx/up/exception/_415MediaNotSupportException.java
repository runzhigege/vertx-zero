package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.up.epic.Ut;

import javax.ws.rs.core.MediaType;
import java.util.Set;

public class _415MediaNotSupportException extends WebException {

    public _415MediaNotSupportException(final Class<?> clazz,
                                        final MediaType media,
                                        final Set<MediaType> expected) {
        super(clazz, media.toString(), Ut.fromJoin(expected.toArray(new MediaType[]{})));
    }

    @Override
    public int getCode() {
        return -60006;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;
    }
}
