package ca.ualberta.cmput301.t03.datamanager.elasticsearch.queries;

/**
 * Created by rishi on 15-11-27.
 */
public interface Query {
    String formQuery();
    String getUniqueId();
}
