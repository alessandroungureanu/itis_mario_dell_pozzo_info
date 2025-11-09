import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import com.google.gson.*;

public class LocalDateSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>{

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext){
        JsonPrimitive jp = new JsonPrimitive(formatter.format(localDate));
        return jp;
    }

    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext ){
        return LocalDate.parse(jsonElement.getAsString(), LocalDateSerializer.formatter.withLocale(Locale.ENGLISH));
    }
}

