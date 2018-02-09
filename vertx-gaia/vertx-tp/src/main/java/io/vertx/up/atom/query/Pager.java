package io.vertx.up.atom.query;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception._400PagerInvalidException;
import io.vertx.up.exception._500QueryMetaNullException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Types;

import java.io.Serializable;

public class Pager implements Serializable {

    private static final Annal LOGGER = Annal.get(Pager.class);
    private static final String PAGE = "page";
    private static final String SIZE = "size";
    /**
     * Start page: >= 1
     */
    private transient int page;
    /**
     * Page size
     */
    private transient int size;
    /**
     * From index: offset
     */
    private transient int start;
    /**
     * To index: limit
     */
    private transient int end;

    /**
     * Create pager by page, size
     *
     * @param page
     * @param size
     * @return
     */
    public static Pager create(final Integer page, final Integer size) {
        return new Pager(page, size);
    }

    /**
     * Another mode to create Pager
     *
     * @param pageJson
     * @return
     */
    public static Pager create(final JsonObject pageJson) {
        return new Pager(pageJson);
    }

    private void ensure(final JsonObject pageJson) {
        // Pager building checking
        Fn.flingWeb(null == pageJson, LOGGER,
                _500QueryMetaNullException.class, this.getClass());
        // Required
        Fn.flingWeb(!pageJson.containsKey(PAGE), LOGGER,
                _400PagerInvalidException.class, this.getClass(), PAGE);
        Fn.flingWeb(!pageJson.containsKey(SIZE), LOGGER,
                _400PagerInvalidException.class, this.getClass(), SIZE);
        // Types
        Inquiry.ensureType(pageJson, PAGE, Integer.class,
                Types::isInteger, this.getClass());
        Inquiry.ensureType(pageJson, SIZE, Integer.class,
                Types::isInteger, this.getClass());
    }

    private void init(final Integer page, final Integer size) {
        // Page/Size
        Fn.flingWeb(1 > page, LOGGER,
                _400PagerInvalidException.class, this.getClass(), page);
        this.page = page;
        // Default Size is 10
        this.size = 0 < size ? size : 10;
        Fn.safeNull(() -> {
            // Caculate
            this.start = (this.page - 1) * this.size;
            this.end = this.page * this.size;
        }, this.page, this.size);
    }

    private Pager(final Integer page, final Integer size) {
        this.init(page, size);
    }

    private Pager(final JsonObject pageJson) {
        this.ensure(pageJson);
        this.init(pageJson.getInteger(PAGE), pageJson.getInteger(SIZE));
    }

    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        data.put(PAGE, this.page);
        data.put(SIZE, this.size);
        return data;
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getTop() {
        return this.size;
    }
}
