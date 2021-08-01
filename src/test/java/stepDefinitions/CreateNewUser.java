package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class CreateNewUser {


    @Given("User creates new a user")
    public void userCreatesNewAUser() {

    baseURI = "https://basicapitest.herokuapp.com/api/kullanici/";

        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", "memo@gmail.com");
        newUser.put("isim","Memo");
        newUser.put("sifre", "12345");

//https://basicapitest.herokuapp.com/api/kullanici/olusturma
// (post methodu ile) email, isim ve sifre bilgileri de gönderilmeli.
// body bölümü içerisinde www-url encoded seçilmeli.

    Response response = given().contentType(ContentType.JSON).when().body(newUser).post("olusturma");
    response.prettyPrint();
Assert.assertEquals(201, response.statusCode());
Assert.assertEquals("success", response.path("sonuc"));

//https://basicapitest.herokuapp.com/api/kullanici/bilgi
// (get methodu ile) params bölümüne email bilgisi eklenmeli.

Response response1 = given().accept(ContentType.JSON).param("email", "memo@gmail.com").get("bilgi");
response1.prettyPrint();
String actualEmail = response1.path("email" );
        String actualIsim = response1.path("isim" );
        String actualSifre = response1.path("sifre" );

Assert.assertEquals(newUser.get("email"),actualEmail);
Assert.assertEquals(newUser.get("isim"), actualIsim);
Assert.assertEquals(newUser.get("sifre"), actualSifre);

    }
}
