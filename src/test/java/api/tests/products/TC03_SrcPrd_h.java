package api.tests.products;

import api.endpoints.ApiBase;
import api.endpoints.ApiRts;
import api.tests.ApiTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC03_SrcPrd_h extends ApiTestBase {
    @Test
    public void test() {
        // 1. Construct API Request (Headers, Params) and Send HTTP Request
        Response res = given().spec(ApiBase.getFrmReq()).formParam("search_product", "top").when().post(ApiRts.srchPrd_url);

        // 2. Validate Response Status Code matches expected criteria
        res.then().spec(ApiBase.get200Res());

        // 3. Verify Response Body Content or Exact Status Value
        Assert.assertTrue(res.getBody().asString().contains("products"));

    }
}
