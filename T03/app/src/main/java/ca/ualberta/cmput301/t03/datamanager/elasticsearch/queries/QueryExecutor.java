package ca.ualberta.cmput301.t03.datamanager.elasticsearch.queries;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by rishi on 15-11-28.
 */
public interface QueryExecutor {
    <T> QueryResult<T> executeQuery(String suffix, Query query, Type typeOfQueryResult) throws IOException;
}
