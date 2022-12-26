package com.rakuten.mobile.messageapiv1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.mobile.messageapiv1.Exception.MessageApiException;
import com.rakuten.mobile.messageapiv1.model.Messages;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

@Log4j2
public class CustomDeserializer implements Deserializer<Messages> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Messages deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                throw new MessageApiException(1002, "Processing error", null);
            }
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Messages.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MessageApiException(1002, "Processing error", null);
        }
    }
}
