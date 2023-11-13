package StepDefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import resources.commentOnPost;



import resources.post;
import static org.junit.Assert.assertTrue;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class apiTest {
    private static Response response;
    String zipcode;
    private static final String baseUrl = "https://jsonplaceholder.typicode.com/";
    @Given("the base API URL is {string}")
    public void the_base_api_url_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = baseUrl;
    }

    @When("a user make a post request to {string} with the following data:")
    public void a_user_make_a_post_request_to_with_the_following_data(String endpoint,DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions

            RestAssured.baseURI = baseUrl; // Replace with the actual base URL
            int userId = Integer.parseInt(dataTable.cell(1, 0));
            int id = Integer.parseInt(dataTable.cell(1, 1));
            String title = dataTable.cell(1, 2);
            String body = dataTable.cell(1, 3);
            post message = new post(userId,id,title,body);
            response = given().contentType(ContentType.JSON).body(message).post(endpoint);
            Assert.assertEquals(201,response.statusCode());
            response.then().body("userId",equalTo(5)).body("title",equalTo("New Post Title"));
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer val) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Given("a post with title {string} exists")
    public void a_post_with_title_exists(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("a user makes a POST request to {string} with the following data:")
    public void a_user_makes_a_post_request_to_with_the_following_data(String comment,DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = baseUrl; // Replace with the actual base URL
        int postId = Integer.parseInt(dataTable.cell(1, 0));
        int id = Integer.parseInt(dataTable.cell(1, 1));
        String name = dataTable.cell(1, 2);
        String email = dataTable.cell(1, 3);
        String body = dataTable.cell(1, 4);
        commentOnPost message = new commentOnPost(postId,id,name,email,body);
        response = given().contentType(ContentType.JSON).body(message).post(comment);
        Assert.assertEquals(201,response.statusCode());
        response.then().body("postId",equalTo(1)).body("email",equalTo("comment@example.com"))
                .body("body",equalTo("This is a comment"));
    }
    @When("a user makes a GET request to {string}")
    public void a_user_makes_a_get_request_to(String endpoint) {

        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = baseUrl;
        response = RestAssured.get(endpoint);
        Assert.assertEquals(200,response.statusCode());
    }
    @Then("zipcode should be numeric")
    public void zipcode_should_be_numeric() {
        // Write code here that turns the phrase above into concrete actions
        zipcode = response
                .jsonPath()
                .getString("find { it.id == 1 }.address.zipcode");
        Assert.assertNotNull(zipcode);
        assertTrue(zipcode.matches("[\\d-]+"));
        response.then().log().all();
    }
    @Then("zipcode should be > than {int}")
    public void zipcode_should_be_than(Integer val) {
        // Write code here that turns the phrase above into concrete actions
        // Convert the zipcode to a numeric value
        int numericZipcode = Integer.parseInt(zipcode.replaceAll("[^\\d]", ""));
        //Assert.assertTrue(numericZipcode > val, "Zipcode should be greater than 0");
        assertTrue(numericZipcode>val);
    }
}
