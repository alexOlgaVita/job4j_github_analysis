package ru.job4j.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.job4j.model.Commit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommitDeserializer extends StdDeserializer<Commit> {

    public CommitDeserializer() {
        this(null);
    }

    public CommitDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Commit deserialize(JsonParser parser, DeserializationContext ctx)
            throws IOException, JacksonException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        String message = jsonNode.get("commit").get("message").asText();
        String authorName = jsonNode.get("commit").get("author").get("name").asText();
        String authorEmail = jsonNode.get("commit").get("author").get("email").asText();
        String author = authorName + ", " + authorEmail;
        String date = jsonNode.get("commit").get("committer").get("date").asText();
        return new Commit(null, message, author, LocalDateTime.parse(date, dateTimeFormatter), null);
    }
}
