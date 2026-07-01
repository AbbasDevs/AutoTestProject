package api.tests.authentication;

import api.endpoints.ApiBase;
import api.endpoints.ApiRts;
import api.tests.ApiTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC17_CrtAcc_h extends ApiTestBase {
    @Test
    public void test() {
        // 1. Construct API Request (Headers, Params) and Send HTTP Request
        Response res = given().spec(ApiBase.getFrmReq()).formParam("name", usrPl.getName()).formParam("email", usrPl.getEmail()).formParam("password", usrPl.getPassword()).formParam("title", usrPl.getTitle()).formParam("birth_date", usrPl.getBirth_date()).formParam("birth_month", usrPl.getBirth_month()).formParam("birth_year", usrPl.getBirth_year()).formParam("firstname", usrPl.getFirstname()).formParam("lastname", usrPl.getLastname()).formParam("company", usrPl.getCompany()).formParam("address1", usrPl.getAddress1()).formParam("address2", usrPl.getAddress2()).formParam("country", usrPl.getCountry()).formParam("zipcode", usrPl.getZipcode()).formParam("state", usrPl.getState()).formParam("city", usrPl.getCity()).formParam("mobile_number", usrPl.getMobile_number()).when().post(ApiRts.crtAcc_url);

        // 2. Validate Response Status Code matches expected criteria
        res.then().spec(ApiBase.get200Res());

        // 3. Verify Response Body Content or Exact Status Value
        Assert.assertEquals(res.jsonPath().getString("responseCode"), "201");

    }
}
