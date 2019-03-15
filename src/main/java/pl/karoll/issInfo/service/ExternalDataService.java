package pl.karoll.issInfo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.karoll.issInfo.ExternalData;

@Service
public class ExternalDataService {

    public ExternalData getCurrentIssData() {
        final String uri = "http://api.open-notify.org/iss-now.json";
        RestTemplate issData = new RestTemplate();
        ExternalData result = issData.getForObject(uri, ExternalData.class);
        return result;
    }
}
