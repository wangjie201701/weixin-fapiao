package com.wangjie.bill.base.file;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static final String BASE64_SPLIT = ",";

    public static void writeBase64CodeToFile(String filename, String base64) {
        if (StringUtils.isNotBlank(base64)) {
            try {
                if (StringUtils.indexOf(base64, BASE64_SPLIT) > 0) {
                    base64 = StringUtils.substringAfterLast(base64, BASE64_SPLIT);
                }
                FileUtils.writeByteArrayToFile(new File(filename), Base64.decodeBase64(base64));
            } catch (IOException e) {
                LOGGER.error("writeBase64CodeToFile error ", e);
                throw new RuntimeException(e);
            }
        }
    }

    public static String fileBase64Code(String filename) {
        try {
            return Base64.encodeBase64String(FileUtils.readFileToByteArray(new File(filename)));
        } catch (IOException e) {
            LOGGER.error("fileBase64Code error ", e);
            throw new RuntimeException(e);
        }
    }
}
