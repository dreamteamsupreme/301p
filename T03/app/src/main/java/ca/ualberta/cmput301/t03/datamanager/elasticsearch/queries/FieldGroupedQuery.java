package ca.ualberta.cmput301.t03.datamanager.elasticsearch.queries;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;

import ca.ualberta.cmput301.t03.common.Preconditions;
import ca.ualberta.cmput301.t03.datamanager.JsonFormatter;

/**
 * Created by rishi on 15-11-27.
 */
public class FieldGroupedQuery implements Query {

    @Expose
    @SerializedName("query")
    private final HashMap<String, HashMap<String, String>> filteringQuery;

    @Expose
    @SerializedName("aggs")
    private final HashMap<String, HashMap<String, Terms>> aggregationQuery;

    private final String queryIdentifier;

    public FieldGroupedQuery(final String filterField,
                             final Collection<String> possibleFilterFieldValues,
                             final String groupingField,
                             final Integer maxCount,
                             final String queryIdentifier) {

        Preconditions.checkNotNullOrWhitespace(filterField, "filterField");
        Preconditions.checkNotNullOrWhitespace(groupingField, "groupingField");
        Preconditions.checkNotNullOrEmpty(possibleFilterFieldValues, "possibleFilterFieldValues");

        this.queryIdentifier = Preconditions.checkNotNullOrWhitespace(queryIdentifier, "queryIdentifier");
        this.filteringQuery = new HashMap<>();
        this.aggregationQuery = new HashMap<>();

        String filterFieldQuery = StringUtils.join(possibleFilterFieldValues, " OR ");
        buildFilteringQuery(filterField, filterFieldQuery);

        buildAggregationQuery(groupingField, maxCount);
    }

    private void buildAggregationQuery(final String groupingField, final Integer maxCount) {
        aggregationQuery.put(AGGREGATION_KEY, new HashMap<String, Terms>() {
            {
                put("terms", new Terms(groupingField, maxCount));
            }
        });
    }

    private void buildFilteringQuery(final String filterField, final String filterFieldQuery) {
        this.filteringQuery.put("query_string", new HashMap<String, String>() {
            {
                put("default_field", filterField);
                put("query", filterFieldQuery);
            }
        });
    }

    @Override
    public String formQuery() {
        Gson gson = new JsonFormatter(true, true).getGson();
        return gson.toJson(this);
    }

    @Override
    public String getUniqueId() {
        return queryIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof FieldGroupedQuery)) {
            return false;
        }

        FieldGroupedQuery rhs = (FieldGroupedQuery)o;

        return this.filteringQuery.equals(rhs.filteringQuery) &&
                this.aggregationQuery.equals(rhs.aggregationQuery) &&
                this.queryIdentifier.equals(rhs.queryIdentifier);
    }

    @Override
    public int hashCode() {
        return this.aggregationQuery.hashCode() ^ this.filteringQuery.hashCode() ^ this.queryIdentifier.hashCode();
    }

    private class Terms {
        @Expose
        private String field;

        @Expose
        private Integer size;

        public Terms(String field, Integer size) {
            this.field = field;
            this.size = size;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if (!(o instanceof Terms)) {
                return false;
            }

            Terms rhs = (Terms)o;

            return rhs.field.equals(this.field) &&
                    rhs.size.equals(this.size);
        }

        @Override
        public int hashCode() {
            return field.hashCode() ^ size.hashCode();
        }
    }
}
