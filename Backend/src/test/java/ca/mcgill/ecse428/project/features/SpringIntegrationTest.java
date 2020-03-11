package ca.mcgill.ecse428.project.features;

import ca.mcgill.ecse428.project.McFantasyApplication;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = McFantasyApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class SpringIntegrationTest {
 
    protected RestTemplate restTemplate = new RestTemplate();
 
    protected final String DEFAULT_URL = "http://localhost:8080/";
    
    protected HttpHeaders headers = new HttpHeaders();
}

