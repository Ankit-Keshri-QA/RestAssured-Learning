package org.example.day5;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class Football_API_Complex_Tests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.football-data.org";
        RestAssured.basePath = "/v4";

        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").addHeader("X-Auth-Token", "dbd13a5afb194441841c979a7190b52d")
                // My Token received as API key from the team on mail
                .build();
    }

    @Test
    public void groovyTestCase() {
        Response response = get("/teams");
        // Find the first team name
        String teamName = response.path("teams[1].name"); // Groovy to parse to response
        System.out.println(teamName);

        Assert.assertEquals(teamName, "TSG 1899 Hoffenheim");

        String lastTeam = response.path("teams[-1].name"); // -1 in index to fetch the last value
        System.out.println(lastTeam);

        Assert.assertEquals(lastTeam, "SV Wacker Burghausen");

        List<String> allTeamNames = response.path("teams.name");

        System.out.println(allTeamNames.size());
        System.out.print(allTeamNames);

        int count = response.path("count");
        Assert.assertEquals(count, allTeamNames.size());

    }

    @Test
    public void validateComplexData() {
        Response response = get("/teams");
        List<Map<String, ?>> teamsArray = response.path("teams");

        Map<String, ?> teamName = response.path("teams.find{it.name=='Aston Villa FC'}");
    }

    @Test
    public void validateNameOfPlayer() {
        Response response = get("/teams/66");

        // Fetch the name of the player with nationality France
        String playerName = response.path("squad.find{it.nationality=='France'}.name");
        System.out.println(playerName);

        Assert.assertEquals(playerName, "Leny Yoro");

        // Fetch all players from Argentina
        List<String> players = response.path("squad.findAll{it.nationality=='Argentina'}.name");

        System.out.println("Players from Argentina are " + players);

        // Fetch all players from England and position is Midfielder
        String newPlayer = response.path("squad.findAll{it.nationality=='England'}.find{it.position == 'Midfield'}.name");

        System.out.println("England + MidFiedler :- "+ newPlayer);

    }

}
