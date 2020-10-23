package com.yuvrajpatel.thescoretask

import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.utilities.Utility
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UtilityUnitTest {

    private lateinit var utilityResponse : Utility

    @Before
    fun setUp(){
        utilityResponse = Utility()
    }

    @Test
    fun test_sort_teamList_alphabetically_isCorrect() {

        val unSortedTeamList : List<Team> = getTeamsListForTest(null)
        val expectedSortedTeamList : List<Team> = getTeamsListForTest(Utility.SORT.ALPHABETICALLY)

        val resultSortedTeamList : List<Team> = utilityResponse.sortListAlphabetically(unSortedTeamList)

        assertEquals(expectedSortedTeamList, resultSortedTeamList)
    }

    @Test
    fun test_sort_teamList_ByWins_isCorrect() {

        val unSortedTeamList : List<Team> = getTeamsListForTest(null)
        val expectedSortedTeamList : List<Team> = getTeamsListForTest(Utility.SORT.WINS)

        val resultSortedTeamList : List<Team> = utilityResponse.sortListByWins(unSortedTeamList)

        assertEquals(expectedSortedTeamList, resultSortedTeamList)
    }

    @Test
    fun test_sort_teamList_ByLosses_isCorrect() {

        val unSortedTeamList : List<Team> = getTeamsListForTest(null)
        val expectedSortedTeamList : List<Team> = getTeamsListForTest(Utility.SORT.LOSSES)

        val resultSortedTeamList : List<Team> = utilityResponse.sortListByLosses(unSortedTeamList)

        assertEquals(expectedSortedTeamList, resultSortedTeamList)
    }


}