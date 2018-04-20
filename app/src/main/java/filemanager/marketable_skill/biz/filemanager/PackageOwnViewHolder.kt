package filemanager.marketable_skill.biz.filemanager

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by koji_mitake on 2018/04/18.
 */
class PackageOwnViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val itemImageView : ImageView = view.findViewById(R.id.imageView1)
    val itemTextViwe1 : TextView = view.findViewById(R.id.textView1)
    val itemTextViwe2 : TextView = view.findViewById(R.id.textView2)

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


}
