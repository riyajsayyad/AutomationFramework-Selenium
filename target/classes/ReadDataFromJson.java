import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataFromJson {

    public List<HashMap<String, String>> getData(){

        // Read JSON file content into a string
        String jsonContent = FileUtils.readFileToString(
            new File(System.getProperty("user.dir") + "\\src\\main\\resources\\PurchaseOrder.json"), "UTF-8");

        // Convert JSON string to List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
            jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        return data;
    }
}
