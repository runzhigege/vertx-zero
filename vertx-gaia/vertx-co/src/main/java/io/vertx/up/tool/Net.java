package io.vertx.up.tool;

import io.vertx.up.func.Fn;
import io.vertx.up.tool.net.IPHost;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Net {

    /**
     * Check whether host:port is ok to connect
     *
     * @param host
     * @param port
     * @return
     */
    public static boolean isReach(final String host, final int port) {
        final Boolean reach = Fn.getJvm(() -> {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));
            return Boolean.TRUE;
        }, host, port);
        return null == reach ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * @return
     */
    public static String getIPv4() {
        return IPHost.getInstance().getExtranetIPv4Address();
    }

    public static String getHostName() {
        return Fn.getJvm(() -> (InetAddress.getLocalHost()).getHostName(), true);
    }

    /**
     * @return
     */
    public static String getIPv6() {
        return IPHost.getInstance().getExtranetIPv6Address();
    }

    /**
     * @return
     */
    public static String getIP() {
        return IPHost.getInstance().getExtranetIPAddress();
    }
}
