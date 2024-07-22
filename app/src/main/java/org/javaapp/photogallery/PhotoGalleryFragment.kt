package org.javaapp.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.javaapp.photogallery.api.FlickrApi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class PhotoGalleryFragment : Fragment() {
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrofit 인스턴스 생성
        val retrofit : Retrofit = Retrofit.Builder() 
            .baseUrl("https://flickr.com/") // 접속할 엔드포인트의 기본 URL
            .addConverterFactory(ScalarsConverterFactory.create()) // 스칼라 변환기 팩토리의 인스턴스를 생성해서 Retrofit 객체에 추가
            .build()

        // FlickrApi 인터페이스를 구현한 API 인스턴스 생성
        val flickrApi : FlickrApi = retrofit.create(FlickrApi::class.java) 
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)

        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}