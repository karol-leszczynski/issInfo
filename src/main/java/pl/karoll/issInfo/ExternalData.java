package pl.karoll.issInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalData {
    private Long timestamp;
    private Map<String,Double> iss_position;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Double> getIss_position() {
        return iss_position;
    }

    public void setIss_position(Map<String, Double> iss_position) {
        this.iss_position = iss_position;
    }

    @Override
    public String toString() {
        return "ExternalData{" +
                "timestamp=" + timestamp +
                ", iss_position=" + iss_position +
                '}';
    }
}
