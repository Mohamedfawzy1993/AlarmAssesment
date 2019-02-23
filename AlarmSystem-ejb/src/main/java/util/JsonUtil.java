package util;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class JsonUtil {

    public static String toJson(Object o) {
        JsonbConfig config = new JsonbConfig()
                .withNullValues(true).withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);

        // Use it!
        String result = jsonb.toJson(o);
        return result;

    }
}
