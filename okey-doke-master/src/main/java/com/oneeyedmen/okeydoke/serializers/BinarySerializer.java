package com.oneeyedmen.okeydoke.serializers;

import com.oneeyedmen.okeydoke.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BinarySerializer implements Serializer<byte[]> {

    @Override
    public byte[] readFrom(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        int read = 0;
        while ((read = is.read()) != -1) {
            result.write(read);
        }
        return result.toByteArray();
    }

    @Override
    public void writeTo(byte[] object, OutputStream os) throws IOException {
        os.write(object);
    }

    @Override
    public byte[] emptyThing() {
        return new byte[0];
    }
}
