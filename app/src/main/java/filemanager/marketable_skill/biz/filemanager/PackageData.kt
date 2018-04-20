package filemanager.marketable_skill.biz.filemanager

import android.graphics.drawable.Drawable

/**
 * Created by koji_mitake on 2018/04/17.
 */
class PackageData(val dir : String, val l : String, val draw : Drawable) {

    var sourceDir : String = dir
    var label : String = l
    var drawable : Drawable = draw

}