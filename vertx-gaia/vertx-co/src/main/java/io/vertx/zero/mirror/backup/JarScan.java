package io.vertx.zero.mirror.backup;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarScan implements Scan {

    @Override
    public Set<Class<?>> search(final String packageName, final Predicate<Class<?>> predicate) {

        final Set<Class<?>> classes = new HashSet<>();

        try {
            //通过当前线程得到类加载器从而得到URL的枚举
            final Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
            while (urlEnumeration.hasMoreElements()) {
                final URL url = urlEnumeration.nextElement();//得到的结果大概是：jar:file:/C:/Users/ibm/.m2/repository/junit/junit/4.12/junit-4.12.jar!/org/junit
                final String protocol = url.getProtocol();//大概是jar
                if ("jar".equalsIgnoreCase(protocol)) {
                    //转换为JarURLConnection
                    final JarURLConnection connection = (JarURLConnection) url.openConnection();
                    if (connection != null) {
                        final JarFile jarFile = connection.getJarFile();
                        if (jarFile != null) {
                            //得到该jar文件下面的类实体
                            final Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while (jarEntryEnumeration.hasMoreElements()) {
                            /*entry的结果大概是这样：
                                    org/
                                    org/junit/
                                    org/junit/rules/
                                    org/junit/runners/*/
                                final JarEntry entry = jarEntryEnumeration.nextElement();
                                final String jarEntryName = entry.getName();
                                //这里我们需要过滤不是class文件和不在basePack包名下的类
                                if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/", ".").startsWith(packageName)) {
                                    final String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                    final Class cls = Class.forName(className);
                                    if (predicate == null || predicate.test(cls)) {
                                        classes.add(cls);
                                    }
                                }
                            }
                        }
                    }
                } else if ("file".equalsIgnoreCase(protocol)) {
                    //从maven子项目中扫描
                    final FileScan fileScanner = new FileScan();
                    fileScanner.setDefaultClassPath(url.getPath().replace(packageName.replace(".", "/"), ""));
                    classes.addAll(fileScanner.search(packageName, predicate));
                }
            }
        } catch (final ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
