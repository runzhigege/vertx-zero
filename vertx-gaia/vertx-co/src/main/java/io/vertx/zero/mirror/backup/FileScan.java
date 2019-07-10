package io.vertx.zero.mirror.backup;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

class FileScan implements Scan {
    private String defaultClassPath = FileScan.class.getResource("/").getPath();

    FileScan(final String defaultClassPath) {
        this.defaultClassPath = defaultClassPath;
    }

    FileScan() {
    }

    void setDefaultClassPath(final String defaultClassPath) {
        this.defaultClassPath = defaultClassPath;
    }

    @Override
    public Set<Class<?>> search(final String packageName, final Predicate<Class<?>> predicate) {
        //先把包名转换为路径,首先得到项目的classpath
        final String classpath = this.defaultClassPath;
        //然后把我们的包名basPack转换为路径名
        final String basePackPath = packageName.replace(".", File.separator);
        final String searchPath = classpath + basePackPath;
        return new ClassSearcher().doPath(new File(searchPath), packageName, predicate, true);
    }

    private static class ClassSearcher {
        private final Set<Class<?>> classPaths = new HashSet<>();

        private Set<Class<?>> doPath(final File file, String packageName, final Predicate<Class<?>> predicate, final boolean flag) {

            if (file.isDirectory()) {
                //文件夹我们就递归
                final File[] files = file.listFiles();
                if (!flag) {
                    packageName = packageName + "." + file.getName();
                }

                for (final File f1 : files) {
                    this.doPath(f1, packageName, predicate, false);
                }
            } else {//标准文件
                //标准文件我们就判断是否是class文件
                if (file.getName().endsWith(CLASS_SUFFIX)) {
                    //如果是class文件我们就放入我们的集合中。
                    try {
                        final Class<?> clazz = Class.forName(packageName + "." + file.getName().substring(0, file.getName().lastIndexOf(".")));
                        if (predicate == null || predicate.test(clazz)) {
                            this.classPaths.add(clazz);
                        }
                    } catch (final ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            return this.classPaths;
        }
    }
}
