package com.rakuten.mobile.messageapiv1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "message")
@TypeAlias("Messages")
public class Messages {
    @Id
    private String id;
    private String userId;
    private String topic;
    private String content;
    private Instant created;
}
