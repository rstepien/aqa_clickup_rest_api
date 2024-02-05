package pl.aqaclickuprs.url;

public class ClickupUrl {

    // Declare the BASE_URL constant for the Clickup API base URL
    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    // Declare the TEAM constant for the team endpoint
    private static final String TEAM = "/team";
    // Declare the SPACE constant for the space endpoint
    private static final String SPACE = "/space";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getTeamsUrl() {
        return TEAM;
    }

    public static String getTeamUrl(String teamId) {
        return TEAM + "/" + teamId;
    }

    public static String getSpacesUrl(String teamId) {
        return getTeamUrl(teamId) + SPACE;
    }

    public static String getSpaceUrl(String spaceId) {
        return SPACE + "/" + spaceId;
    }

}
