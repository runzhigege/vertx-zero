package io.vertx.up.tool;

import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.net.IPHost;
import org.apache.commons.net.telnet.TelnetClient;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class Net {

    private static final Annal LOGGER = Annal.get(Net.class);

    private static final ConcurrentMap<String, TelnetClient> TELNETS =
            new ConcurrentHashMap<>();

    /**
     * Check whether host:port is ok to connect
     *
     * @param host
     * @param port
     * @return
     */
    static boolean isReach(final String host, final int port) {
        final Boolean reach = Fn.getJvm(() -> {
            final String key = Codec.sha256(host + port);
            final TelnetClient telnet = Fn.pool(TELNETS, key, () -> {
                final TelnetClient instance = new TelnetClient("vt200");
                instance.setDefaultTimeout(3000);
                return instance;
            });
            LOGGER.debug(Info.INF_NET, String.valueOf(telnet.hashCode()), key, host, String.valueOf(port));
            telnet.connect(host, port);
            return telnet.isConnected();
        }, host, port);
        return null == reach ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * @return
     */
    static String getIPv4() {
        return IPHost.getInstance().getExtranetIPv4Address();
    }

    static String getHostName() {
        return Fn.getJvm(() -> (InetAddress.getLocalHost()).getHostName(), true);
    }

    /**
     * @return
     */
    static String getIPv6() {
        return IPHost.getInstance().getExtranetIPv6Address();
    }

    /**
     * @return
     */
    static String getIP() {
        return IPHost.getInstance().getExtranetIPAddress();
    }
}
