package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class DemowebshopTests {
    @Test
    void addToCartAsNewUserTest() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success",is(true))
                .body("message",is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml",is("(1)"));
    }


    @Test
    void addToCartTest() {
        Integer cartSize = 0;

        ValidatableResponse response =
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=34731adb-f980-46c1-ab43-bb97f0e068b1; " +
                        "ARRAffinity=ec08e7da72e4ba97183e90792d09dc1802c14abeae18aa029b4540a2008d1887;" +
                        " __utmc=78382081; __utmz=78382081.1654003135.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); " +
                        "NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72; __atuvc=19%7C22; __atuvs=6296644fb095a8c3000;" +
                        " __utma=78382081.1712188930.1654003135.1654020852.1654023247.3; __utmt=1; __utmb=78382081.1.10.1654023247;" +
                        " ARRAffinity=ec08e7da72e4ba97183e90792d09dc1802c14abeae18aa029b4540a2008d1887;" +
                        " Nop.customer=34731adb-f980-46c1-ab43-bb97f0e068b1")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success",is(true))
                .body("message",is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"));

//        assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//                .body("updatetopcartsectionhtml",is("(19)"));
    }
}
