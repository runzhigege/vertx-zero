package io.vertx.up.atom.query;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Strings;

import java.util.HashSet;
import java.util.Set;

class IrInquiry implements Inquiry {

    private transient Pager pager;
    private transient Sorter sorter;
    private transient Set<String> projection;
    private transient Criteria criteria;

    IrInquiry(final JsonObject input) {
        this.ensure(input);
        // Building
        this.init(input);
    }

    @SuppressWarnings("unchecked")
    private void init(final JsonObject input) {
        Fn.safeSemi(input.containsKey(KEY_PAGER), null,
                () -> this.pager = Pager.create(input.getJsonObject(KEY_PAGER)));
        Fn.safeSemi(input.containsKey(KEY_SORTER), null,
                () -> this.sorter = this.getSorter(input.getJsonArray(KEY_SORTER)));
        Fn.safeSemi(input.containsKey(KEY_PROJECTION), null,
                () -> this.projection = new HashSet<String>(input.getJsonArray(KEY_PROJECTION).getList()));
        Fn.safeSemi(input.containsKey(KEY_CRITERIA), null,
                () -> this.criteria = Criteria.create(input.getJsonObject(KEY_CRITERIA)));
    }

    private void ensure(final JsonObject input) {
        // Sorter checking
        Inquiry.ensureType(input, KEY_SORTER, JsonArray.class,
                Types::isJArray, this.getClass());
        // Projection checking
        Inquiry.ensureType(input, KEY_PROJECTION, JsonArray.class,
                Types::isJArray, this.getClass());
        // Pager checking
        Inquiry.ensureType(input, KEY_PAGER, JsonObject.class,
                Types::isJObject, this.getClass());
        // Criteria
        Inquiry.ensureType(input, KEY_CRITERIA, JsonObject.class,
                Types::isJObject, this.getClass());
    }

    private Sorter getSorter(final JsonArray sorter) {
        // Sorter Parsing
        final Sorter target = Sorter.create();
        Fn.itJArray(sorter, String.class, (field, index) -> {
            if (field.contains(Strings.COMMA)) {
                final String sortField = field.split(Strings.COMMA)[0];
                final boolean asc = field.split(Strings.COMMA)[1].equalsIgnoreCase("asc");
                target.add(sortField, asc);
            } else {
                target.add(field, true);
            }
        });
        return target;
    }

    @Override
    public Set<String> getProjection() {
        return this.projection;
    }

    @Override
    public Pager getPager() {
        return this.pager;
    }

    @Override
    public Sorter getSorter() {
        return this.sorter;
    }

    @Override
    public Criteria getCriteria() {
        return this.criteria;
    }
}
