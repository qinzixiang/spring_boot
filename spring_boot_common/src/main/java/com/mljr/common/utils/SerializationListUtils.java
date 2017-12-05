package com.mljr.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationListUtils {
    private static Logger logger = LoggerFactory
            .getLogger(SerializationListUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> List<T> deserialize(byte[] in) {
        if (in == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        ByteArrayInputStream bis = new ByteArrayInputStream(in);
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(bis);
            while (true) {
                T m = (T) is.readObject();
                if (m == null) {
                    break;
                }
                list.add(m);
            }
        } catch (IOException e) {
            logger.error("Caught IOException ", e);
        } catch (ClassNotFoundException e) {
            logger.error("Caught IOException ", e);
        } finally {
            close(is);
            close(bis);
        }
        return list;
    }

    public static <T> byte[] serialize(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Can't serialize null");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(bos);
            for (T m: list) {
                os.writeObject(m);
            }
            os.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable list", e);
        } finally {
            close(os);
            close(bos);
        }
        return bos.toByteArray();
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                logger.info("Unable to close " + closeable, e);
            }
        }
    }
}
