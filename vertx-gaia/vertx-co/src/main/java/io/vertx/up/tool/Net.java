package io.vertx.up.tool;

import io.vertx.up.func.Fn;
import io.vertx.up.tool.net.IPHost;
import org.apache.commons.net.telnet.TelnetClient;

import java.net.InetAddress;

class Net {

    /**
     * Check whether host:port is ok to connect
     *
     * @param host
     * @param port
     * @return
     */
    static boolean isReach(final String host, final int port) {
        final Boolean reach = Fn.getJvm(() -> {
            final TelnetClient telnet = new TelnetClient("vt200");
            telnet.setDefaultTimeout(3000);
            telnet.connect(host, port);
            if (telnet.isAvailable() && telnet.isConnected()) {
                telnet.disconnect();
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
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
