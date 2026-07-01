package api.tests;

import api.payloads.UsrPayload;
import org.testng.annotations.BeforeSuite;

public class ApiTestBase {
    public static UsrPayload usrPl;

    @BeforeSuite
    public void setupData() {
        usrPl = new UsrPayload();
        usrPl.setName("Api Tester");
        usrPl.setEmail("api_test_" + System.currentTimeMillis() + "@gmail.com");
        usrPl.setPassword("ApiPass123");
        usrPl.setTitle("Mr");
        usrPl.setBirth_date("15");
        usrPl.setBirth_month("August");
        usrPl.setBirth_year("1995");
        usrPl.setFirstname("Api");
        usrPl.setLastname("Tester");
        usrPl.setCompany("IT");
        usrPl.setAddress1("123 API St");
        usrPl.setAddress2("Suite 200");
        usrPl.setCountry("United States");
        usrPl.setZipcode("90210");
        usrPl.setState("CA");
        usrPl.setCity("LA");
        usrPl.setMobile_number("5551234567");
    }
}
