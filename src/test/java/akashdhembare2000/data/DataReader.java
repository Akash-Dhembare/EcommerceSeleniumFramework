package akashdhembare2000.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {
//    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
//        // Read JSON to String
//        String jsonContent=FileUtils.readFileToString(new File(new File(System.getProperty("user.dir")) + "/src/test/java/akashdhembare2000/data/PurchaseOrder.json"));
//
//        // String to HashMap - Jackson DataBind
//        ObjectMapper mapper= new ObjectMapper();
//        List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
//        });
//
//        return data;
//
//        //{map, map}
//
//    }
}
