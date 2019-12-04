package com.insanexs.mess.fastjson.codec;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.insanexs.mess.fastjson.AuditStatus;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Author: xieshang
 * @Description: customer serializer and deserializer
 * @Date: Create at 2019-12-03
 */
public class AuditStatusCodec implements ObjectSerializer, ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object value = parser.parse();
        return value == null ? (T) value : (T) AuditStatus.convert(TypeUtils.castToInt(value));
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        serializer.write(((AuditStatus)object).getCode());
    }
}
