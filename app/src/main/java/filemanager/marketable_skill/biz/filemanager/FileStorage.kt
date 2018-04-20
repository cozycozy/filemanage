package filemanager.marketable_skill.biz.filemanager

import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.os.StatFs
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_file_storage.*
import java.io.File
import java.text.DecimalFormat

class FileStorage : AppCompatActivity() {

    val TAG = "MainActivity"
    var tViewA : TextView? = null
    var tViewB : TextView? = null
    var tViewC : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val df = DecimalFormat("#,###KB");

        val externalMemPath = Environment.getExternalStorageDirectory().absolutePath
        val internalMemPath = Environment.getDataDirectory().path

        Log.i(TAG,"---------- Data --------");
        Log.i(TAG,"外部メモリーの利用可能容量:" + df.format(getTotalSize(externalMemPath) / 1024));
        Log.i(TAG,"外部メモリーの総容量:" + df.format(getAvailableSize(externalMemPath) / 1024));
        Log.i(TAG,"内部メモリーの利用可能容量:" + df.format(getTotalSize(internalMemPath) / 1024));
        Log.i(TAG,"内部メモリーの総容量:" + df.format(getAvailableSize(internalMemPath) / 1024));
        Log.i(TAG,"--------------------------------");

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        setContentView(layout)

        tViewA = TextView(this)
        tViewB = TextView(this)
        tViewC = TextView(this)

        layout.addView(tViewA)
        layout.addView(tViewB)
        layout.addView(tViewC)

//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//        setContentView(layout);
//
//        tViewA = new TextView(this);
//        tViewB = new TextView(this);
//        tViewC = new TextView(this);
//
//        layout.addView(tViewA);
//        layout.addView(tViewB);
//        layout.addView(tViewC);

//        {
//            Log.i(TAG, "Can use:" + getExternalMemoryMoutedPath().getAbsolutePath());
//        }
//
//        {
//            String path = Environment.getDataDirectory().getAbsolutePath();
//            Log.d(TAG, "path:" + path);
//            StatFs statFs = new StatFs(path);
//            long total = (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024;
//            long free =  (statFs.getBlockSize() * statFs.getBlockCount()) / 1024;
//
//            Log.i(TAG,"---------- SD Card Info --------");
//            Log.i(TAG,"Total = " + df.format(total));
//            Log.i(TAG,"Free  = " + df.format(free));
//            Log.i(TAG,"--------------------------------");
//        }
//
//        {
//            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//            Log.d(TAG, "path:" + path);
//            StatFs statFs = new StatFs(path);
//            long total = (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024;
//            long free =  (statFs.getBlockSize() * statFs.getBlockCount()) / 1024;
//
//            Log.i(TAG,"---------- SD Card Info --------");
//            Log.i(TAG,"Total = " + df.format(total));
//            Log.i(TAG,"Free  = " + df.format(free));
//            Log.i(TAG,"--------------------------------");
//        }

    }

    override fun onResume() {
        super.onResume()

        val df = DecimalFormat("#,###KB")

        var externalMemPath = Environment.getExternalStorageDirectory().absolutePath
        var internalMemPath = Environment.getDataDirectory().path

        Log.i(TAG, "Can use:" + getExternalMemoryMoutedPath()?.getAbsolutePath());
        tViewA?.text = getExternalMemoryMoutedPath()?.getAbsolutePath();

        val path = Environment.getDataDirectory().absolutePath
        Log.d(TAG,"path : " + path)

        val statFs = StatFs(path)

        val total = (statFs.blockSizeLong * statFs.availableBlocksLong) / 1024
        val free = (statFs.blockSizeLong * statFs.blockCountLong) / 1024

        Log.i(TAG,"---------- SD Card Info --------");
        Log.i(TAG,"Total = " + df.format(total));
        Log.i(TAG,"Free  = " + df.format(free));
        Log.i(TAG,"--------------------------------");

        tViewB?.text = "total: " + total.toString() + " / free: " + free.toString()

        val Externalpath  = Environment.getExternalStorageDirectory().absolutePath
        Log.d(TAG, "Externalpath:" + Externalpath);
        val statExFs = StatFs(Externalpath);
        val Externaltotal = (statExFs.blockSizeLong * statExFs.availableBlocksLong) / 1024
        val Externalfree = (statExFs.blockSizeLong * statExFs.blockCountLong) / 1024

        Log.i(TAG,"---------- SD Card Info --------");
        Log.i(TAG,"Total = " + df.format(Externaltotal));
        Log.i(TAG,"Free  = " + df.format(Externalfree));
        Log.i(TAG,"--------------------------------");

        tViewC?.setText("Externaltotal:" + Externaltotal.toString() + " / Externalfree:" + Externalfree.toString());

    }

//    @Override
//    public void onResume(){
//        super.onResume();
//
//        DecimalFormat df =   new DecimalFormat("#,###KB");
//
//        String externalMemPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String internalMemPath = Environment.getDataDirectory().getPath();
//
//        {
//            Log.i(TAG, "Can use:" + getExternalMemoryMoutedPath().getAbsolutePath());
//            tViewA.setText(getExternalMemoryMoutedPath().getAbsolutePath());
//        }
//
//        {
//            String path = Environment.getDataDirectory().getAbsolutePath();
//            Log.d(TAG, "path:" + path);
//            StatFs statFs = new StatFs(path);
//            long total = (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024;
//            long free =  (statFs.getBlockSize() * statFs.getBlockCount()) / 1024;
//
//            Log.i(TAG,"---------- SD Card Info --------");
//            Log.i(TAG,"Total = " + df.format(total));
//            Log.i(TAG,"Free  = " + df.format(free));
//            Log.i(TAG,"--------------------------------");
//
//            tViewB.setText("free:" + free);
//        }
//
//        {
//            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//            Log.d(TAG, "path:" + path);
//            StatFs statFs = new StatFs(path);
//            long total = (statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024;
//            long free =  (statFs.getBlockSize() * statFs.getBlockCount()) / 1024;
//
//            Log.i(TAG,"---------- SD Card Info --------");
//            Log.i(TAG,"Total = " + df.format(total));
//            Log.i(TAG,"Free  = " + df.format(free));
//            Log.i(TAG,"--------------------------------");
//
//            tViewC.setText("free:" + free);
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    // 外部メモリーが使えるかを返す

    fun canUseExternalMemory() : Boolean {
        val state = Environment.getExternalStorageState()
        return state.equals(Environment.MEDIA_MOUNTED)
    }

//    public static boolean canUseExternalMemory() {
//        String state = Environment.getExternalStorageState();
//        return state.equals(Environment.MEDIA_MOUNTED);
//    }

    // 外部メモリーが使える場合に外部メモリーのマウントされているパスを取得する
    fun getExternalMemoryMoutedPath() : File? {

        if (canUseExternalMemory()){
            return Environment.getExternalStorageDirectory()
        }
        return null
    }

//    public static File getExternalMemoryMoutedPath(){
//        if( canUseExternalMemory() ){
//            return Environment.getExternalStorageDirectory();
//        }
//        return null;
//    }

    // 総容量(トータルサイズ)を取得する
    fun getTotalSize(path : String) : Long {

        var size : Long = -1

        if (path != null){
            val fs = StatFs(path)
            val bkSize = fs.blockSizeLong
            val bkCount = fs.blockCountLong
            size = bkSize * bkCount
        }
        return size
    }

    // 空き容量(利用可能)を取得する

    fun getAvailableSize(path: String) : Long {
        var size : Long = -1

        if (path != null){
            val fs = StatFs(path)
            val blockSize = fs.blockSizeLong
            val availableBlockSize = fs.availableBlocksLong
            size = blockSize * availableBlockSize
        }

        return size
    }

}