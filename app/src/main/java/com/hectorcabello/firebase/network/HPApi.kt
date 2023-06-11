package com.hectorcabello.firebase.network

import com.hectorcabello.firebase.model.StaffDetailHP
import com.hectorcabello.firebase.model.StaffHP
import com.hectorcabello.firebase.model.StudentDetailHP
import com.hectorcabello.firebase.model.StudentHP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HPApi {
    @GET
    fun getStudents(
        @Url url: String?    //getStudents("api/characters")
    ): Call<ArrayList<StudentHP>>

    @GET
    fun getStaff(
        @Url url: String?    //getStudents("api/characters/staff")
    ): Call<ArrayList<StaffHP>>

    @GET    //getStudentDetail("3763783")   /api/character/{3763783}
    fun getStudentDetail(
        @Url url: String?
    ): Call<ArrayList<StudentDetailHP>>

    @GET("/api/character/{id}")    //getStaffDetail("3763783")   /api/character/{3763783}
    fun getStaffDetail(
        @Query("id") id: String?
    ): Call<StaffDetailHP>
}