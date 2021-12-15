package TG;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SampleTest {

        @Test
        public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {
              given().
                        when().
                        get("http://ergast.com/api/f1/2017/circuits.json").
                        then().
                        assertThat().
                        body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));

        }

    @Test
    public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {

        given().
                auth().
                preemptive().
                basic("username", "password").
                when().
                get("http://path.to/basic/secured/api").
                then().
                assertThat().
                statusCode(400);
    }
}
