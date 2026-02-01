package com.phonebook.tests.restassured;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutContactsTests extends TestBase {


    String id;

    @BeforeMethod
    public void precondition() {

        ContactDto contactDto = ContactDto.builder()
//                .id(id)
                .name("Jeff")
                .lastName("Buckley")
                .email("jbuckley@gm.com")
                .phone("1234567890")
                .address("New-York")
                .description("singer")
                .build();

        String message = given()
                .header(AUTHORIZATION, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .statusCode(200)
                .extract().path("message");
        // System.out.println(message);
        //Contact was added! ID: 33d9732b-27bd-46b6-88f4-46951b691a75
        String[] split = message.split(": ");
        id = split[1];

    }


    @Test
    public void updateContactSuccessTest() {
        ContactDto updatedContactDto = ContactDto.builder()
                .id(id)
                .name("Tom")
                .lastName("York")
                .email("jbuckley@gm.com")
                .phone("1234567890")
                .address("New-York")
                .description("singer")
                .build();

        given()
                .header(AUTHORIZATION, TOKEN)
                .body(updatedContactDto)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .statusCode(200)
                .assertThat().body("message", equalTo("Contact was updated"));

    }

    @Test
    public void updateContactWithoutAddressNegativeTest() {
        ContactDto updatedContactDto = ContactDto.builder()
                .id(id)
                .name("Tom")
                .lastName("York")
                .email("jbuckley@gm.com")
                .phone("1234567890")
                .address("")
                .description("singer")
                .build();

//   ErrorDto errorDto =
        given()
                .header(AUTHORIZATION, TOKEN)
                .body(updatedContactDto)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .statusCode(400)
                .assertThat()
                .body("message.address", equalTo("must not be blank"));

//        .body("errorDto", containsString("not found in your contacts"))
//          .extract().body().as(ErrorDto.class);
        //  .extract().body().as(ErrorDto.class);
//        System.out.println(errorDto.getMessage());


    }

    @Test
    public void updateContactWithWrongIdNegativeTest() {
        ContactDto updatedContactDto = ContactDto.builder()
                .id("777")
                .name("Tom")
                .lastName("York")
                .email("jbuckley@gm.com")
                .phone("1234567890")
                .address("LA")
                .description("singer")
                .build();


        given()
                .header(AUTHORIZATION, TOKEN)
                .body(updatedContactDto)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Contact with id: 777 not found in your contacts!"));
    }

    @Test
    public void updateContactWithWrongTokenNegativeTest() {
        ContactDto updatedContactDto = ContactDto.builder()
                .id("777")
                .name("Tom")
                .lastName("York")
                .email("jbuckley@gm.com")
                .phone("1234567890")
                .address("LA")
                .description("singer")
                .build();

//        ErrorDto errorDto =
                given()
                        .header(AUTHORIZATION, 333)
                        .body(updatedContactDto)
                        .contentType(ContentType.JSON)
                        .when()
                        .put("contacts")
                        .then()
                        .statusCode(401)
                        .assertThat()
                .body("message", equalTo("JWT strings must contain exactly 2 period characters. Found: 0"));


//                        .extract().body().as(ErrorDto.class);
//        System.out.println(errorDto.getMessage());


    }



}



