package com.wangjie.bill.base.runtime;

import org.apache.commons.lang.StringUtils;

import java.net.URL;

public class ResourceUtil {

    protected static final ClassLoader CLASS_LOADER = ResourceUtil.class.getClassLoader();
    protected static final String CLASSPATH;
    protected static final String DEPLOY_PATH;

    static {
        CLASSPATH = toPath();
        DEPLOY_PATH = toPath(true);
    }

    public static String getDeployPath() {
        return DEPLOY_PATH;
    }

    public static String getPath() {
        return CLASSPATH;
    }

    public static String getPath(String dir) {
        return toPath(dir);
    }

    protected static String toPath() {
        return toPath(false);
    }

    protected static String toPath(boolean deployPath) {
        return toPath("", deployPath);
    }

    protected static String toPath(String dir) {
        return toPath(dir, false);
    }

    protected static String toPath(String dir, boolean deployPath) {
        String result;
        if (null != dir) {
            URL url = CLASS_LOADER.getResource(dir);
            if (null != url) {
                String path = url.getPath();
                // 删除最后的"/"
                if (StringUtils.isNotEmpty(path)) {
                    if (path.substring(path.length() - 1).equals("/")) {
                        path = path.substring(0, path.length() - 1);
                    }
                    if (deployPath) {
                        // 如果是web删除web-inf
                        if (StringUtils.isWhitespace(dir)) {
                            int web = path.indexOf("/WEB-INF/classes");
                            if (web > 0) {
                                path = path.substring(0, web);
                            }
                        }
                    }
                }
                result = path;
            } else {
                result = null;
            }
        } else {
            result = null;
        }
        return result;
    }
}
