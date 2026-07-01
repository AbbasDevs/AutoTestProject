package api.endpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiBase {

    /**
     * Reusable Request Specification for forms
     * 16-Char Name: getFormReqSpec -> getFrmReqSpc -> getFrmReq
     */
    public static RequestSpecification getFrmReq() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.URLENC) // AutomationExercise uses form-data extensively
                .build();
    }

    /**
     * Reusable Request Specification for JSON payloads
     * 16-Char Name: getJsonReqSpec -> getJsnReq
     */
    public static RequestSpecification getJsnReq() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
    
    /**
     * Reusable Response Specification for 200 OK
     * 16-Char Name: get200ResSpec -> get200Res
     */
    public static ResponseSpecification get200Res() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
