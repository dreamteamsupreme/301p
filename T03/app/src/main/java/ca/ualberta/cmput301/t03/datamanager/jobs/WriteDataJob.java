package ca.ualberta.cmput301.t03.datamanager.jobs;

import java.io.IOException;

import ca.ualberta.cmput301.t03.common.Preconditions;
import ca.ualberta.cmput301.t03.datamanager.DataKey;
import ca.ualberta.cmput301.t03.datamanager.ElasticSearchHelper;

/**
 * Created by rishi on 15-11-11.
 */
public class WriteDataJob extends DataManagerJob {

    private final String json;
    private final boolean useExplicitExposeAnnotation;

    public WriteDataJob(DataKey dataKey,
                        String json,
                        boolean useExplicitExposeAnnotation) {
                        //OnRequestQueuedCallback onRequestQueuedCallback) {
        super(dataKey);//, onRequestQueuedCallback);
        this.json = Preconditions.checkNotNullOrWhitespace(json, "json");
        this.useExplicitExposeAnnotation = useExplicitExposeAnnotation;
    }

//    public WriteDataJob(DataKey dataKey,
//                        Objects obj,
//                        Type typeOfT,
//                        boolean useExplicitExposeAnnotation) {
//        this(dataKey, obj, typeOfT, useExplicitExposeAnnotation, null);
//    }

    @Override
    public void onRun() throws IOException {
        new ElasticSearchHelper().writeJson(json, getRequestSuffix());
    }
}