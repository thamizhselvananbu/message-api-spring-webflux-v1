package com.rakuten.mobile.messageapiv1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.mobile.messageapiv1.Exception.MessageApiException;
import com.rakuten.mobile.messageapiv1.model.Messages;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serializer;

@Log4j2
public class CustomSerializer implements Serializer<Messages> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Messages data) {
        try {
            if (data == null) {
                log.info("Serializing message is null");
                throw new MessageApiException(1002, "Processing error", null);
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new MessageApiException(1002, "Processing error", null);
        }
    }
}
