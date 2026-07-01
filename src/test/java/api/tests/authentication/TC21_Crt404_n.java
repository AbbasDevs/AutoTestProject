package api.tests.authentication;

import api.endpoints.ApiBase;
import api.endpoints.ApiRts;
import api.tests.ApiTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC21_Crt404_n extends ApiTestBase {
    @Test
    public void test() {
        // 1. Construct API Request (Headers, Params) and Send HTTP Request
        Assert.assertEquals(given().spec(ApiBase.getFrmReq()).when().post(ApiRts.crtAcc_url + "X").statusCode(), 404);

    }
}
