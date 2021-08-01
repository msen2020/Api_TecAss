package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;


public class AlphabeticalOrder {

    /*
    [
  {
    "id" : 1,
    "isim" : "Adidas VS SET Erkek Spor Ayakkabı F34370",
    "kategori" : "Erkek"
  },
  {
    "id" : 2,
    "isim" : "Dockers by Gerli 224942 Gri Erkek Sneaker Ayakkabı",
    "kategori" : "Erkek"
  },
  {
    "id" : 3,
    "isim" : "Hakiki Deri Siyah Erkek Günlük Ayakkabı",
    "kategori" : "Erkek"
  },
  {
    "id" : 4,
    "isim" : "Lumberjack CONNECT Beyaz Erkek Koşu Ayakkabısı",
    "kategori" : "Erkek"
  },
  {
    "id" : 5,
    "isim" : "Puma Erkek Ayakkabı Interflex Modern 19280501",
    "kategori" : "Erkek"
  }
]
     */
// get methoduyla verileri almak
    @Given("user verifies that the given products are in order.")
    public void user_verifies_that_the_given_products_are_in_order() {
       baseURI = "https://basicapitest.herokuapp.com/api/urun_listesi";
        Response response = given().accept(ContentType.JSON).when().get();
        response.prettyPrint();

        Assert.assertEquals(200, response.statusCode());

        List<String> products = response.path("isim" ); //response.path ile verileri test et
        System.out.println(products);

        //Expected product in Alphabetical order
        List<String> expectedProductsInOrder = Arrays.asList("Adidas VS SET Erkek Spor Ayakkabı F34370", "Dockers by Gerli 224942 Gri Erkek Sneaker Ayakkabı", "Hakiki Deri Siyah Erkek Günlük Ayakkabı", "Lumberjack CONNECT Beyaz Erkek Koşu Ayakkabısı", "Puma Erkek Ayakkabı Interflex Modern 19280501");
        Assert.assertEquals(expectedProductsInOrder, products);

    }

}
