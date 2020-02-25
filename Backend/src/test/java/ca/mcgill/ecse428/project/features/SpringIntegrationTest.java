package ca.mcgill.ecse428.project.features;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ca.mcgill.ecse428.project.McFantasyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = McFantasyApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class SpringIntegrationTest {
 
    protected RestTemplate restTemplate = new RestTemplate();
 
    protected final String DEFAULT_URL = "http://localhost:8080/";
    
    protected HttpHeaders headers = new HttpHeaders();
}

