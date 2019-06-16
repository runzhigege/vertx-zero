package io.vertx.tp.rbac.cv;

public interface AuthMsg {

    String CODE_VERIFY = "Input data when verification: client_id = {0}, code = {1}";
    String CODE_FILTER = "Authorization Code Filters: {0}";

    String RELATION_USER_ROLE = "Fetch relations ( User - Role ) by User key: {0}";
    String RELATION_GROUP = "Fetch relations ( User - Group ) by User key: {0}";
    String RELATION_GROUP_ROLE = "Fetch relation ( Group - Role ) by Group key: {0}, Mode: {1}";

    String LOGIN_SUCCESS = "[ Ακριβώς ] User ( username = {0} ) login successfully.";
    String LOGIN_USER = "[ Ακριβώς ] username = {0} does not exist.";
    String LOGIN_PWD = "[ Ακριβώς ] username = {0}, the password ( {1} ) you provided is wrong.";
    String LOGIN_INPUT = "Login processing execute ( username = {0} )";

    String TOKEN_STORE = "The system will initialize user''s principle information. user key: {0}.";
    String TOKEN_INPUT = "The system will verify token = {0}";
    String TOKEN_JWT = "Jwt token data stored: {0}.";

    String TOKEN_SIZE_NULL = "Token size invalid ( null ): {0}, user: {1}.";
    String TOKEN_SIZE_EMPTY = "Token size invalid ( empty ): {0}, user: {1}.";
    String TOKEN_SIZE_MULTI = "Token size invalid ( multi ): {0}, user: {1}.";
    String TOKEN_INVALID = "Token invalid {0}";
    String TOKEN_EXPIRED = "Token you provided {0} is expired at: {1}.";

    String CREDIT_ACTION = "1. Accredit action ( uri = `{0}`, method = `{1}` ).";
    String CREDIT_RESOURCE = "2. Accredit resource ( resource = `{0}` ).";
    String CREDIT_LEVEL = "3. Accredit level ( action = {0}, resource = {1} ).";
    String CREDIT_PERMISSION = "4. Accredit profile ( profileKey = {0} ).";
    String CREDIT_AUTHORIZED = "5. Accredit authorized ( permission = {0} ).";
    String CREDIT_BOUND = "6. Accredit bound ( bound = {0} and key = {1} )";
    String CREDIT_SUCCESS = "7. Accredit authorized cache ( key = {0} )";
}
