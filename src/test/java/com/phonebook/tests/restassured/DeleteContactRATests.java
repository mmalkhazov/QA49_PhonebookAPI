package com.phonebook.tests.restassured;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactRATests extends TestBase {

    String id;

    @BeforeMethod
    public void precondition() {

        ContactDto contactDto = ContactDto.builder()
                .name("Adam")
                .lastName("Jones")
                .email("adam@gmail.com")
                .phone("1234567890")
                .address("Berlin")
                .description("guitarist")
                .build();

        String message = given()
                .header(AUTHORIZATION, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract().path("message");
        // System.out.println(message);
        //Contact was added! ID: 33d9732b-27bd-46b6-88f4-46951b691a75
        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void deleteContactSuccessTest() {
        //String message =
        given()
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"));
        //  .extract().path("message");
        //System.out.println(message);
        //Contact was deleted!
    }

    @Test
    public void DeleteContactByWrongId() {
        //ErrorDto errorDto =
        given()
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete("contacts/33d9732b-27bd-46b6-88f4-46951b691a66")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", containsString("not found in your contacts"));
        //  .extract().body().as(ErrorDto.class);
        //System.out.println(errorDto.getMessage());
        //Contact with id: 33d9732b-27bd-46b6-88f4-46951b691a66 not found in your contacts!
    }
}