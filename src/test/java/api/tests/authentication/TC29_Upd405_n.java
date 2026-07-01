package api.tests.authentication;

import api.endpoints.ApiBase;
import api.endpoints.ApiRts;
import api.tests.ApiTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC29_Upd405_n extends ApiTestBase {
    @Test
    public void test() {
        // 1. Construct API Request (Headers, Params) and Send HTTP Request
        Assert.assertTrue(given().spec(ApiBase.getFrmReq()).when().get(ApiRts.updAcc_url).statusCode() > 0);

    }
}
