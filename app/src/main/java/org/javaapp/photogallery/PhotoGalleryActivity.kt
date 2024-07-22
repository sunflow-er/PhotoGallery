package org.javaapp.photogallery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PhotoGalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)

        // 프래그먼트 컨테이너에 프래그먼트가 이미 호스팅되었는지 확인
        val isFragmentContainerEmpty = (savedInstanceState == null)
        /*
        savedInstanceState가 null이면 앱이 시작될 때 최초 실행된 액티비티의 인스턴스임을 의미 (아직 프래그먼트를 호스팅하지 않았음)
        null이 아니면 장치 회전 등의 구성 변경이나 프로세스 종료로 액티비티 인스턴스가 다시 생성된 것 (이전에 호스팅된 프래그먼트가 있음)
         */

        if (isFragmentContainerEmpty) { // 호스팅 되어있지 않다면
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PhotoGalleryFragment.newInstance()) //  프래그먼트 인스턴스를 생성하여 컨테이너에 추가
                .commit()
        }
    }
}