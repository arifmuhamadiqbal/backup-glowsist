package lat.pam.backupglowsist.services

import lat.pam.backupglowsist.data.model.InputUserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("endpoint")
    fun sendUserData(
        @Body InputUserData: InputUserData
    ) : Call<InputUserData>

//    @GET("endpoint")
//    fun getUserData(
//        @Path("path")
//    )

}