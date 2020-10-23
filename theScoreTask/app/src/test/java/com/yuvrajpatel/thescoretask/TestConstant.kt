package com.yuvrajpatel.thescoretask

import com.yuvrajpatel.thescoretask.model.Player
import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.utilities.Utility

val dummyCorrectResponse = "[{\n" +
        "  \"id\": 1,\n" +
        "  \"full_name\": \"Boston Celtics\",\n" +
        "  \"wins\": 45,\n" +
        "  \"losses\": 20,\n" +
        "  \"players\": [\n" +
        "    {\n" +
        "      \"id\": 37729,\n" +
        "      \"first_name\": \"Kadeem\",\n" +
        "      \"last_name\": \"Allen\",\n" +
        "      \"position\": \"SG\",\n" +
        "      \"number\": 45\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 37729,\n" +
        "      \"first_name\": \"Kadeem\",\n" +
        "      \"last_name\": \"Allen\",\n" +
        "      \"position\": \"SG\",\n" +
        "      \"number\": 45\n" +
        "    }"+
        "  ]\n" +
        "}," +
        "{\n" +
        "  \"id\": 1,\n" +
        "  \"full_name\": \"Boston Celtics\",\n" +
        "  \"wins\": 45,\n" +
        "  \"losses\": 20,\n" +
        "  \"players\": [\n" +
        "    {\n" +
        "      \"id\": 37729,\n" +
        "      \"first_name\": \"Kadeem\",\n" +
        "      \"last_name\": \"Allen\",\n" +
        "      \"position\": \"SG\",\n" +
        "      \"number\": 45\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 37729,\n" +
        "      \"first_name\": \"Kadeem\",\n" +
        "      \"last_name\": \"Allen\",\n" +
        "      \"position\": \"SG\",\n" +
        "      \"number\": 45\n" +
        "    }"+
        "  ]\n" +
        "}]"

val dummyIncorrectResponse = "[{\n" +
        "  \"id\": 1,\n" +
        "  \"full_name\": \"Boston Celtics\",\n" +
        "  \"wins\": 45,\n" +
        "  \"losses\": 20,\n" +
        "  \"players\": [\n"

fun getTeamsListForTest(type: Utility.SORT?) : List<Team>{
    return when(type){
        Utility.SORT.ALPHABETICALLY -> getTeamsAlphabetically()
        Utility.SORT.WINS -> getTeamsSortByWins()
        Utility.SORT.LOSSES -> getTeamsSortByLosses()
        else -> getTeams()
    }
}

fun getTeams() : List<Team> {
    val teamList = ArrayList<Team>()
    teamList.plus(Team(30, 5, "Indiana Pacer", 1, getPlayerList()))
    teamList.plus(Team(25, 15, "New York Knicks", 1, getPlayerList()))
    teamList.plus(Team(45, 0, "Toronto Raptors", 1, getPlayerList()))
    teamList.plus(Team(5, 25, "Boston Celtics", 1, getPlayerList()))
    teamList.plus(Team(10, 20, "Atlanta Hawks", 1, getPlayerList()))
    return teamList.toList()
}

fun getTeamsAlphabetically() : List<Team> {
    val teamList = ArrayList<Team>()
    teamList.plus(Team(10, 20, "Atlanta Hawks", 1, getPlayerList()))
    teamList.plus(Team(5, 25, "Boston Celtics", 1, getPlayerList()))
    teamList.plus(Team(30, 5, "Indiana Pacer", 1, getPlayerList()))
    teamList.plus(Team(25, 15, "New York Knicks", 1, getPlayerList()))
    teamList.plus(Team(45, 0, "Toronto Raptors", 1, getPlayerList()))
    return teamList.toList()
}

fun getTeamsSortByWins() : List<Team> {
    val teamList = ArrayList<Team>()
    teamList.plus(Team(45, 0, "Toronto Raptors", 1, getPlayerList()))
    teamList.plus(Team(30, 5, "Indiana Pacer", 1, getPlayerList()))
    teamList.plus(Team(25, 15, "New York Knicks", 1, getPlayerList()))
    teamList.plus(Team(10, 20, "Atlanta Hawks", 1, getPlayerList()))
    teamList.plus(Team(5, 25, "Boston Celtics", 1, getPlayerList()))
    return teamList.toList()
}

fun getTeamsSortByLosses() : List<Team> {
    val teamList = ArrayList<Team>()
    teamList.plus(Team(5, 25, "Boston Celtics", 1, getPlayerList()))
    teamList.plus(Team(10, 20, "Atlanta Hawks", 1, getPlayerList()))
    teamList.plus(Team(25, 15, "New York Knicks", 1, getPlayerList()))
    teamList.plus(Team(30, 5, "Indiana Pacer", 1, getPlayerList()))
    teamList.plus(Team(45, 0, "Toronto Raptors", 1, getPlayerList()))
    return teamList.toList()
}

fun getPlayerList() : List<Player>{
    val playerList : List<Player> = ArrayList<Player>()
    playerList.plus(Player(1, "Alex", "Dark", "F", 1))
    playerList.plus(Player(1, "Alex", "Dark", "F", 1))
    playerList.plus(Player(1, "Alex", "Dark", "F", 1))
    playerList.plus(Player(1, "Alex", "Dark", "F", 1))
    playerList.plus(Player(1, "Alex", "Dark", "F", 1))
    return playerList
}