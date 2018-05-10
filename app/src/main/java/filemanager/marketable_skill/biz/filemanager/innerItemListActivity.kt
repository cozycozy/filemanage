package filemanager.marketable_skill.biz.filemanager

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import filemanager.marketable_skill.biz.filemanager.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_inneritem_list.*
import kotlinx.android.synthetic.main.inneritem_list.*
import kotlinx.android.synthetic.main.inneritem_list_content.view.*
import java.io.File
import android.widget.Toast
import android.support.annotation.NonNull
import filemanager.marketable_skill.biz.filemanager.extentions.DetailInfo


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [innerItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class innerItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false
    private var mDataList : ArrayList<FileInfoData> = ArrayList<FileInfoData>()

    private val TAG : String = "InnerItemListActivity"

    //外部ファイルリスト
    var filelist : ArrayList<File> = ArrayList<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inneritem_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (inneritem_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        var externalMemPath: String? = getExternalMemoryMoutedPath()?.getAbsolutePath()
        var internalMemPath = Environment.getDataDirectory().path

        Log.i(TAG, "Can use:" + getExternalMemoryMoutedPath()?.getAbsolutePath());

        searchFiles(externalMemPath!!)
        setupRecyclerView(inneritem_list)
    }

    fun searchFiles(path : String){

        var files = File(path).listFiles()

        val res = getResources()

        for (file in files ) {
            if (file.isDirectory){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mDataList.add(FileInfoData(res.getDrawable(R.drawable.abc_action_bar_item_background_material,null),
                            file.name,
                            "フォルダ",
                            file.lastModified().toString()))
                }
            } else if (file.isFile){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mDataList.add(FileInfoData(res.getDrawable(R.drawable.abc_action_bar_item_background_material,null),
                            file.name,
                            file.totalSpace.toString(),
                            file.lastModified().toString()))
                }
            }
        }

    }

    fun pickupFiles(dir : File){

        val checkFiles : Array<out File> = dir.listFiles()

        for (file in checkFiles){
            if (file.isDirectory){
                pickupFiles(file)
            } else if (file.isFile) {
                filelist.add(file)
            } else{
                Log.d("File Info", "不明のファイルのためスキップしました" + file.name)
            }
        }
    }

    // 外部メモリーが使えるかを返す
    fun canUseExternalMemory() : Boolean {
        val state = Environment.getExternalStorageState()
        return state.equals(Environment.MEDIA_MOUNTED)
    }

    // 外部メモリーが使える場合に外部メモリーのマウントされているパスを取得する
    fun getExternalMemoryMoutedPath() : File? {

        if (canUseExternalMemory()) {
            return Environment.getExternalStorageDirectory()
        }
        return null
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
//        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, mDataList, mTwoPane)

        val adapter = object : SimpleItemRecyclerViewAdapter(this, mDataList, mTwoPane) {
            override fun onListClicked(v : View, postion: Int) {
                val intent = Intent(v.context, innerItemDetailActivity::class.java).apply {
                    putExtra(DetailInfo.NAME.name, mDataList[postion].fileName)
                    putExtra(DetailInfo.STORAGE.name, mDataList[postion].fileSize)
                    putExtra(DetailInfo.PATH.name, mDataList[postion].fileDate)
                }
                v.context.startActivity(intent)

            }
        }
        recyclerView.adapter = adapter
    }


    open class SimpleItemRecyclerViewAdapter(private val mParentActivity: innerItemListActivity,
                                        private val mValues: List<FileInfoData>,
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

//        private val mOnClickListener: View.OnClickListener
//
//        init {
//            mOnClickListener = View.OnClickListener() { v ->
//
//                if (mTwoPane) {
//                    val fragment = innerItemDetailFragment().apply {
//                        arguments = Bundle()
//                        arguments.putString(innerItemDetailFragment.ARG_ITEM_ID, "1")
//                    }
//                    mParentActivity.supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.inneritem_detail_container, fragment)
//                            .commit()
//                } else {
//
//                    val intent = Intent(v.context, innerItemDetailActivity::class.java).apply {
//
//                    putExtra(innerItemDetailFragment.ARG_ITEM_ID, "1")
//
//                    }
//                    v.context.startActivity(intent)
//                }
//            }
//        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.inneritem_list_content, parent, false)

            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                onListClicked(view, position)
            }

            return holder
        }

        open fun onListClicked(view : View, postion : Int){

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mfileImage.setImageDrawable(item.image)
            holder.mfileSize.text = item.fileSize
            holder.mfileName.text = item.fileName
            holder.mfileDate.text = item.fileDate

//            with(holder.itemView) {
//                tag = item
//                setOnClickListener(mOnClickListener)
//            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mfileImage : ImageView = mView.file_image
            val mfileName: TextView = mView.file_name
            val mfileSize: TextView = mView.file_size
            val mfileDate: TextView = mView.file_date
        }
    }
}
