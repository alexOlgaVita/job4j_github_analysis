package ru.job4j.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomObjectMapperConfig {

//    @Bean
//    public ObjectMapper customObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
//        return objectMapper;
//    }
//
///////
////@Bean
////public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
////    ObjectMapper objectMapper = builder.build();
////    SimpleModule module = new SimpleModule();
////    module.addDeserializer(CommitAuthor.class, new MyCustomTypeDeserializer());
////    objectMapper.registerModule(module);
////    return objectMapper;
////}
///////

}
