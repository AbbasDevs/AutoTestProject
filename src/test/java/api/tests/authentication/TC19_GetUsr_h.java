package api.tests.authentication;

import api.endpoints.ApiBase;
import api.endpoints.ApiRts;
import api.tests.ApiTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC19_GetUsr_h extends ApiTestBase {
    @Test
    public void test() {
        // 1. Construct API Request (Headers, Params) and Send HTTP Request
        Response res = given().spec(ApiBase.getFrmReq()).queryParam("email", usrPl.getEmail()).when().get(ApiRts.getUsr_url);

        // 2. Verify Response Body Content or Exact Status Value
        Assert.assertEquals(res.jsonPath().getString("responseCode"), "200");

    }
}
