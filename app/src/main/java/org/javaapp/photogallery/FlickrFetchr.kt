package org.javaapp.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.javaapp.photogallery.api.FlickrApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FlickrFetchr"

// PhotoGallery 앱의 네트워크 관련 코드, 기본적인 Repository 역할
class FlickrFetchr {
    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://flickr.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }


    // 네트워크 요청을 큐에 넣고 응답 결과를 LiveData로 반환
    fun fetchContents(): LiveData<String> { // non-mutable 반환
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val flickrRequest: Call<String> = flickrApi.fetchContents()

        flickrRequest.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body() // 클로저는 함수가 정의된 범위(scope)에 있는 변수를 기억하고, 함수가 실행될 때 해당 변수에 접근할 수 있게 해주는 기능
            }

        })

        return responseLiveData
    }
}