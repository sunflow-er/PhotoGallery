package org.javaapp.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {
    @GET("/") // 요청 함수 애노테이션
    fun fetchContents() : Call<String> // HTTP GET 요청을 수행하고 Call 인터페이스를 구현하는 객체 반환
}