//package com.rakuten.mobile.messageapiv1.config;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import com.mongodb.reactivestreams.client.MongoDatabase;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
//@EnableReactiveMongoRepositories
//@Component
//public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {
//
//    @Bean
//    public MongoDatabase mongoDatabase() {
//        return mongoClient().getDatabase(getDatabaseName());
//    }
//
//    @Bean
//    public MongoClient mongoClient() {
////        final String userName = "myUserAdmin";
////        final String password = "thamizh10";
//
//        //final MongoCredential credential = MongoCredential.createCredential(userName, getDatabaseName(), password.toCharArray());
//
//        final ConnectionString connectionString = new ConnectionString("mongodb://myUserAdmin:thamizh10@localhost/messages");
//
//        final MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .applyToSocketSettings(builder ->
//                        builder.connectTimeout(120, TimeUnit.SECONDS)
//                                .readTimeout(120, TimeUnit.SECONDS))
//                .retryReads(true)
//                .retryWrites(true)
//                .build();
//
//        return MongoClients.create(settings);
//    }
//
//    public ReactiveMongoTemplate mongoTemplate() {
//
//        return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
//
//    }
//
//
//    @Override
//    protected String getDatabaseName() {
//        return "messages";
//    }
//}
