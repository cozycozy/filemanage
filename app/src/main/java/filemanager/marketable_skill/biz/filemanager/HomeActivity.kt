package filemanager.marketable_skill.biz.filemanager

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var pm : PackageManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        innerStrageButton.setOnClickListener{
            val intent = Intent(this, innerItemDetailActivity::class.java)
            startActivity(intent)

//            val intent = Intent(v.context, innerItemDetailActivity::class.java).apply {
//                putExtra(innerItemDetailFragment.ARG_ITEM_ID, item.id)
//            }
//            v.context.startActivity(intent)
        }


    }

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

}
