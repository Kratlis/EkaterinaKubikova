package com.epam.tc.hw4;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import java.io.InputStream;
import java.util.List;

public class AttachmentDemo {
    public static void attachFromInput(String name, InputStream inputStream) {
        Allure.addAttachment(name, inputStream);
    }

    @Attachment
    public static String makeStringAttachment(List<String> stringList) {
        return String.valueOf(stringList);
    }

    @Attachment(type = "image/png", value = "Try to use param {name}")
    public static byte[] makeScreenshotAttachment(String name, byte[] source) {
        return source;
    }

}
