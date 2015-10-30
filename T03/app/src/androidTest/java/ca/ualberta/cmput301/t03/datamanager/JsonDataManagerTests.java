package ca.ualberta.cmput301.t03.datamanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import junit.framework.TestCase;

import java.lang.reflect.Type;
import java.util.HashMap;

import ca.ualberta.cmput301.t03.common.exceptions.NotImplementedException;

/**
 * Created by rishi on 15-10-29.
 */
public class JsonDataManagerTests extends TestCase {

    private JsonDataManager testDataManager;

    public void setUp() {
        testDataManager = new JsonDataManager() {
            @Override
            public <T> T getData(DataKey key, Type typeOfT) {
                throw new NotImplementedException("Not applicable for abstract classes.");
            }

            @Override
            public <T> void writeData(DataKey key, T obj, Type typeOfT) {
                throw new NotImplementedException("Not applicable for abstract classes.");
            }

            @Override
            public void delete(DataKey key) {
                throw new NotImplementedException("Not applicable for abstract classes.");
            }

            @Override
            public boolean isOperational() {
                throw new NotImplementedException("Not applicable for abstract classes.");
            }
        };
    }

    public void testSerialize() {
        HashMap<String, Integer> testObj = new HashMap<String, Integer>() {
            {
                put("One", 1);
                put("Two", 2);
            }
        };

        Type hashMapType = new TypeToken<HashMap<String, Integer>>(){}.getType();
        String json = testDataManager.serialize(testObj, hashMapType);
        Gson gson = new Gson();
        HashMap<String, Integer> deserialized = gson.fromJson(json, hashMapType);
        assertEquals(new Integer(1), deserialized.get("One"));
        assertEquals(new Integer(2), deserialized.get("Two"));
    }

    public void testDeserialize() {
        String testJson = "{'One' : 1, 'Two' : 2}";
        HashMap<String, Integer> deserialized = testDataManager.deserialize(testJson,
                new TypeToken<HashMap<String, Integer>>() {}.getType());
        assertEquals(new Integer(1), deserialized.get("One"));
        assertEquals(new Integer(2), deserialized.get("Two"));
    }
}