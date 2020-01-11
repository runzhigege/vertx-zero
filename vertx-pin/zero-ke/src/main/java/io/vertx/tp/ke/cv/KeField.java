package io.vertx.tp.ke.cv;

public interface KeField {

    String FIELD = "field";
    String ID = "id";

    String SCOPE = "scope";
    String REALM = "realm";
    String GRANT_TYPE = "grantType";

    String ITEMS = "items";
    String CHANGES = "changes";
    String DATA_KEY = "dataKey";
    String APP_KEY = "appKey";
    String APP_ID = "appId";
    String APP = "application";
    String SIGMA = "sigma";
    String JOINED_KEY = "joinedKey";

    String ENTITY_ID = "entityId";

    String MODEL_ID = "modelId";
    String MODEL_KEY = "modelKey";
    String MODEL_CATEGORY = "modelCategory";
    String RESOURCE_ID = "resourceId";

    String USER_ID = "userId";

    String EPSILON = "epsilon";
    String DEBUG = "debug";

    String NAME = "name";
    String CODE = "code";
    String TYPE = "type";
    String STATUS = "status";
    String SERIAL = "serial";
    String NAMESPACE = "namespace";
    String PASSWORD = "password";
    String GLOBAL_ID = "globalId";

    String OUT = "out";
    String IN = "in";
    String VALUE = "value";

    String METADATA = "metadata";
    String SERVICE = "service";
    String DATA = "data";
    String ACTIVE = "active";
    String LANGUAGE = "language";
    String KEY = "key";

    String FILE_KEY = "fileKey";

    String IDENTIFIER = "identifier";
    String DYNAMIC = "dynamic";
    String VIEW = "view";
    String ACTOR = "actor";
    String QUERY = "query";

    String MAPPING = "mapping";
    String SOURCE = "source";

    String METHOD = "method";
    String URI = "uri";
    String URI_REQUEST = "requestUri";

    String USERNAME = "username";
    String EMAIL = "email";
    String MOBILE = "mobile";
    String CLIENT_ID = "clientId";

    String RESULT = "result";

    String LOGO = "logo";

    String HABITUS = "habitus";

    String HIDDEN = "hidden";
    String CLASS_NAME = "className";
    String ROW = "row";
    String INITIAL = "initial";

    String RECORD = "record";
    String RECORD_NEW = "recordNew";
    String RECORD_OLD = "recordOld";

    String NUMBERS = "numbers";

    String CREATED_AT = "createdAt";
    String UPDATED_AT = "updatedAt";
    String CREATED_BY = "createdBy";
    String UPDATED_BY = "updatedBy";

    String HEADER = "header";
    String OPTIONS = "options";

    interface App {

        String COPY_RIGHT = "copyRight";
        String ICP = "icp";
        String TITLE = "title";
        String EMAIL = "email";

        String DOMAIN = "domain";
        String APP_PORT = "appPort";
        String ROUTE = "route";

        String PATH = "path";
        String URL_ENTRY = "urlEntry";
        String URL_MAIN = "urlMain";
    }

    interface Api {
        String CONFIG_DATABASE = "configDatabase";

        String CONFIG_INTEGRATION = "configIntegration";

        String CHANNEL_CONFIG = "channelConfig";
        String SERVICE_CONFIG = "serviceConfig";
        String DICT_CONFIG = "dictConfig";
        String MAPPING_CONFIG = "mappingConfig";
    }

    interface Ui {
        String CONFIG = "config";

        String CONTAINER_CONFIG = "containerConfig";

        String COMPONENT_CONFIG = "componentConfig";

        String ASSIST = "assist";

        String GRID = "grid";

        String CONTROLS = "controls";
    }
}
