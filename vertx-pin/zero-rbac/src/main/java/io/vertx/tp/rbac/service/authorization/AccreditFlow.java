package io.vertx.tp.rbac.service.authorization;

import cn.vertxup.domain.tables.pojos.SAction;
import cn.vertxup.domain.tables.pojos.SResource;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error.*;
import io.vertx.tp.rbac.atom.ScRequest;
import io.vertx.tp.rbac.refine.Sc;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._500InternalServerException;

import java.util.Objects;

/*
 * Tool for authorization workflow
 */
class AccreditFlow {

    /*
     * 1. Whether action is existing
     * If action missing, throw 403 exception
     */
    static Future<SAction> inspectAction(
            final Class<?> clazz, final SAction action, final ScRequest request) {
        if (Objects.isNull(action)) {
            final String requestUri = request.getMethod() + " " + request.getNormalizedUri();
            final WebException error = new _403ActionMissingException(clazz, requestUri);
            return Future.failedFuture(error);
        } else {
            return Future.succeededFuture(action);
        }
    }

    /*
     * 2. Whether resource is existing
     * If resource missing, throw 404 exception
     */
    static Future<SResource> inspectResource(
            final Class<?> clazz, final SResource resource, final ScRequest request, final SAction action) {
        if (Objects.isNull(resource)) {
            final String requestUri = request.getMethod() + " " + request.getNormalizedUri();
            final WebException error = new _404ResourceMissingException(clazz, action.getResourceId(), requestUri);
            return Future.failedFuture(error);
        } else {
            return Future.succeededFuture(resource);
        }
    }

    /*
     * 3. Action Level / Resource Level comparing.
     */
    static Future<SResource> inspectLevel(
            final Class<?> clazz, final SResource resource, final SAction action
    ) {
        final Integer required = resource.getLevel();
        final Integer actual = action.getLevel();
        if (actual < required) {
            final WebException error = new _403ActionDinnedException(clazz, required, actual);
            return Future.failedFuture(error);
        } else {
            return Future.succeededFuture(resource);
        }
    }

    /*
     * 4. Extract profile information from Cached
     */
    static Future<JsonArray> inspectPermission(
            final Class<?> clazz, final SResource resource, final ScRequest request
    ) {
        final String profileKey = Sc.generateProfileKey(resource);
        return request.asyncProfile().compose(data -> {
            /* Profile Key */
            final JsonObject profile = data.getJsonObject("profile");
            final JsonArray permissions = profile.getJsonArray(profileKey);
            if (Objects.isNull(permissions) || permissions.isEmpty()) {
                final WebException error = new _403NoPermissionException(clazz, request.getUser(), profileKey);
                return Future.failedFuture(error);
            } else {
                return Future.succeededFuture(permissions);
            }
        });
    }

    /*
     * 5. Permission / Action comparing
     */
    static Future<Boolean> inspectAuthorized(
            final Class<?> clazz, final SAction action, final JsonArray permission
    ) {
        final String permissionId = action.getPermissionId();
        if (Objects.nonNull(permissionId)) {
            if (permission.contains(permissionId)) {
                return Future.succeededFuture(Boolean.TRUE);
            } else {
                final WebException error = new _403PermissionLimitException(clazz, action.getCode());
                return Future.failedFuture(error);
            }
        } else {
            final WebException error = new _500InternalServerException(clazz, "Permission Id Null");
            return Future.failedFuture(error);
        }
    }
}
