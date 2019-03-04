package io.vertx.up.eon;

/**
 * Default order for manage standard request flow
 */
public interface Orders {
    /**
     * Cors Order
     * 1,100,000
     **/
    int CORS = 1_100_000;
    /**
     * Cookie Order
     * 1,200,000
     */
    int COOKIE = 1_200_000;
    /**
     * Body Order
     * 1,300,000
     */
    int BODY = 1_300_000;
    /**
     * Pattern
     * 1,400,000
     */
    int CONTENT = 1_400_000;
    /**
     * Acceptor
     * 1,500,000
     */
    int ACCEPTOR = 1_500_000;
    /**
     * Secure
     * 1,600,000
     */
    int SESSION = 1_600_000;
    /**
     * Session
     * 1,700,000
     */
    int USER_SESSION = 1_700_000;
    /**
     * Filter for request
     * 1,800,000
     */
    int FILTER = 1_800_000;
    /**
     * User Security
     * 1,900,000
     */
    int SECURE = 1_900_000;
    /**
     * Sinker for request ( Normalize Request )
     * 2,000,000
     */
    int NORMALIZE = 2_000_000;
    /**
     * Sign for request ( Sign Request )
     * 3,000,000
     */
    int SIGN = 3_000_000;
    /**
     * ( Default order for event )
     * 5,000,000
     */
    int EVENT = 5_000_000;
    /**
     * ( Default for dynamic routing )
     * 6,000,000
     */
    int DYNAMIC = 6_000_000;
    /**
     * Event bus sender ( The last one )
     * 6,000,000
     */
    int SENDER = 7_000_000;
}
