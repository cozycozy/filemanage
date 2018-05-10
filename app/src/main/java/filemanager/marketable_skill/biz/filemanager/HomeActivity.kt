package filemanager.marketable_skill.biz.filemanager

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File
import java.text.DecimalFormat

class HomeActivity : AppCompatActivity() {

    var pm : PackageManager? = null
    val df = DecimalFormat("#,###MB")

    private val TAG : String = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        innerStrageButton.setOnClickListener{
            val intent = Intent(this, innerItemListActivity::class.java)
            startActivity(intent)

//            val intent = Intent(v.context, innerItemDetailActivity::class.java).apply {
//                putExtra(innerItemDetailFragment.ARG_ITEM_ID, item.id)
//            }
//            v.context.startActivity(intent)
        }

        getInternalStorageInfo()

    }

    private fun getInternalStorageInfo(){

        val internalMemPath = Environment.getDataDirectory().path
        val path = Environment.getDataDirectory().path
        Log.d(TAG,"path : " + path)

        val statFs = StatFs(path)

        val total = (statFs.blockSizeLong * statFs.blockCountLong) / (1024 * 1024)
        val free = (statFs.blockSizeLong * statFs.availableBlocksLong) / (1024 * 1024)
        val FreeSpace_i = Environment.getDataDirectory().freeSpace / (1024 * 1024)

        Log.i(TAG,"---------- SD Card Info --------");
        Log.i(TAG,"Total = " + df.format(total));
        Log.i(TAG,"Free  = " + df.format(free));
        Log.i(TAG,"--------------------------------");

        DataAmount.text = df.format(total)
        FreeAmount.text = df.format(free)

    }

    private fun getExternalStorageInfo() {

    }

    private fun getStorageInfo() {

        var externalMemPath = Environment.getExternalStorageDirectory().absolutePath

//        tViewB?.text = "Path: " + path + " / total(M): " + total.toString() + " / free(M): " + FreeSpace_i +"/" + free.toString()

        val Externalpath  = Environment.getExternalStorageDirectory().absolutePath
        Log.d(TAG, "Externalpath:" + Externalpath);
        val statExFs = StatFs(Externalpath);
        val Externaltotal = (statExFs.blockSizeLong * statExFs.blockCountLong) / (1024 * 1024)
        val Externalfree = (statExFs.blockSizeLong * statExFs.availableBlocksLong) / (1024 * 1024)
        val Freespace = Environment.getExternalStorageDirectory().freeSpace / (1024 * 1024)
        Log.i(TAG,"---------- SD Card Info --------");
        Log.i(TAG,"Total = " + df.format(Externaltotal));
        Log.i(TAG,"Free  = " + df.format(Externalfree));
        Log.i(TAG,"--------------------------------");

    }

    // 外部メモリーが使えるかを返す
    fun canUseExternalMemory() : Boolean {
        val state = Environment.getExternalStorageState()
        return state.equals(Environment.MEDIA_MOUNTED)
    }

    // 外部メモリーが使える場合に外部メモリーのマウントされているパスを取得する
    fun getExternalMemoryMoutedPath() : File? {

        if (canUseExternalMemory()){
            return Environment.getExternalStorageDirectory()
        }
        return null
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
