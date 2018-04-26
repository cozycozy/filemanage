package filemanager.marketable_skill.biz.filemanager

import android.content.ContentResolver
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.os.StatFs
import android.provider.MediaStore
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
    var tViewD : TextView? = null

    //外部ファイルリスト
    var filelist : ArrayList<File> = ArrayList<File>()

    //内部ファイルリスト
    var filelist2 : ArrayList<File> = ArrayList<File>()


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
        tViewD = TextView(this)

        layout.addView(tViewA)
        layout.addView(tViewB)
        layout.addView(tViewC)
        layout.addView(tViewD)


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

        tViewB?.text = "Path: " + path + " / total(M): " + total.toString() + " / free(M): " + FreeSpace_i +"/" + free.toString()

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

       // tViewC?.setText("ExternalPath: " + Externalpath + " / Externaltotal(M):" + Externaltotal.toString() + " / Externalfree(M):" + Freespace + "/" + Externalfree.toString());

        var files = File(Externalpath).listFiles()

        for (file in files ) {
            if (file.isDirectory){
                pickupFiles(file)
            } else if (file.isFile){
                filelist.add(file)
            }
        }

        var filename = ""
        for (file in filelist) {
            filename = filename + " / " + file
        }

        tViewC?.text = "外部ストレージ" + filename

        searchExternalImages()

//        var files2 = File(path).listFiles()
//
//        for (file in files2 ) {
//            if (file.isDirectory){
//                pickupFiles(file)
//            } else if (file.isFile){
//                filelist2.add(file)
//            }
//        }
//
//        filename = ""
//        for (file in filelist2) {
//            filename = filename + file
//        }
//
//        tViewD?.text = "内部ストレージ:" + filename

    }

    private fun searchExternalImages() {

        var cr : ContentResolver = contentResolver

        var projection : Array<String> = arrayOf(
                MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.DATE_MODIFIED)

        var cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,null,null,null)

        var pathIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
        var nameIndex = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
        var sizeIndex = cursor.getColumnIndex(MediaStore.Images.Media.SIZE)
        var heightIndex = cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT)
        var widthIndex = cursor.getColumnIndex(MediaStore.Images.Media.WIDTH)
        var modify_date_Index = cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)

        cursor.moveToFirst()

        while (!cursor.isAfterLast){
            Log.d("CursolInfo","画像パス = " + cursor.getString(pathIndex))
            Log.d("CursolInfo","画像名 = " + cursor.getString(nameIndex))
            Log.d("CursolInfo","サイズ = " + cursor.getString(sizeIndex))
            Log.d("CursolInfo","高さ = " + cursor.getString(heightIndex))
            Log.d("CursolInfo","幅 = " + cursor.getString(widthIndex))
            Log.d("CursolInfo","更新日付 = " + cursor.getString(modify_date_Index))
            cursor.moveToNext()
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