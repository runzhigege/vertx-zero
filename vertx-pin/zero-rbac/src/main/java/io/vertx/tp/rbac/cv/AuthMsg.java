package io.vertx.tp.rbac.cv;

public interface AuthMsg {

    String CODE_VERIFY = "Input data when verification: client_id = {0}, code = {1}";
    String CODE_FILTER = "Authorization Code Filters: {0}";

    String ROLE_FETCH = "Fetch relations by User key: {0}";

    String LOGIN_SUCCESS = "[ Ακριβώς ] User ( username = {0} ) login successfully.";
    String LOGIN_USER = "[ Ακριβώς ] username = {0} does not exist.";
    String LOGIN_PWD = "[ Ακριβώς ] username = {0}, the password ( {1} ) you provided is wrong.";
    String LOGIN_INPUT = "Login processing execute ( username = {0} )";
}
