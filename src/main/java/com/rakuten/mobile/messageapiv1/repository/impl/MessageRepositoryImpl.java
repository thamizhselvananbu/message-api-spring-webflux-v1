//package com.rakuten.mobile.messageapiv1.repository.impl;
//
//import com.mongodb.reactivestreams.client.MongoCollection;
//import com.rakuten.mobile.messageapiv1.config.ReactiveMongoConfig;
//import com.rakuten.mobile.messageapiv1.model.Messages;
//import com.rakuten.mobile.messageapiv1.repository.MessageCustomRepository;
//import org.bson.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//public class MessageRepositoryImpl implements MessageCustomRepository {
//
//    private final Logger logger = LoggerFactory.getLogger(MessageRepositoryImpl.class);
//
//    @Autowired
//    private final ReactiveMongoConfig mongoConfig;
//
//    public MessageRepositoryImpl(ReactiveMongoConfig mongoConfig) {
//        this.mongoConfig = mongoConfig;
//    }
//
//    @Override
//    public Flux<Messages> findAllMessages() {
//        mongoConfig.mongoTemplate().collectionExists("msg")
//                .doOnSuccess(value -> System.out.println("Value: " + value)).doFinally(value -> logger.info("Value is: " + value));
////        if (Boolean.TRUE.equals(isExist.block())) {
////            logger.info("COLLECTION EXISTS");
////        }
//        MongoCollection<Document> collection = mongoConfig.mongoDatabase().getCollection("msg");
////       Publisher<Document> publisher = collection.find();
//        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
////        logger.info(publisher.toString());
//        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//        return Flux.merge(collection.find(Messages.class)).doOnNext(Messages -> {
//            String topic = Messages.getTopic();
//            logger.info("TOPIC " + topic);
//        });
//    }
//}
