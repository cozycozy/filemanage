package filemanager.marketable_skill.biz.filemanager

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.content_test.*
import filemanager.marketable_skill.biz.filemanager.R.id.countTextView
import android.graphics.drawable.Drawable
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.text.method.TextKeyListener.clear
import android.content.pm.PackageManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout


class TestActivity : AppCompatActivity(), PackageOwnViewHolder.ItemClickListener {

    var pm: PackageManager? = null
    var dataList: ArrayList<PackageData> = ArrayList<PackageData>()
    var view : View? = null

    private var mAdapter: PackageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        pm = getApplicationContext().getPackageManager()
        updatePackageList()

        Log.d("list_count",dataList.size.toString())

        recycler_view.adapter = PackageAdapter(this@TestActivity,this@TestActivity,dataList )
        recycler_view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext,"position $position was tapped", Toast.LENGTH_LONG).show()
    }


    protected fun updatePackageList() {

        dataList.clear()

        val pkgInfoList = pm!!.getInstalledPackages(0)

        Toast.makeText(this@TestActivity, "aaaaaa!!!", Toast.LENGTH_LONG).show()

        for (pkgInfo in pkgInfoList) {

            val appInfo = pkgInfo.applicationInfo

            val sourceDir = appInfo.publicSourceDir

            if (!sourceDir.startsWith("/system/")) {
                val label = appInfo.loadLabel(pm).toString()
                val drawable = appInfo.loadIcon(pm)
                dataList.add(PackageData(sourceDir, label, drawable))
                //Toast.makeText(this@TestActivity, label.toString(), Toast.LENGTH_LONG).show()
            }

        }

//        for (aaaa in dataList){
//            Toast.makeText(this@TestActivity, aaaa.label.toString(), Toast.LENGTH_LONG).show()
//        }


//        Collections.sort(dataList, MyAppInfoComparator())
//
//        countTextView.setText(dataList.size() + "")
//        adapter.notifyDataSetChanged()
    }



}
