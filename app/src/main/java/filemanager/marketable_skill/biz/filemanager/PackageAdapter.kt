package filemanager.marketable_skill.biz.filemanager

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by koji_mitake on 2018/04/18.
 */

class PackageAdapter(private val context: Context,
                     private val itemClickListener: PackageOwnViewHolder.ItemClickListener,
                     private val itemList:ArrayList<PackageData>)
                     : RecyclerView.Adapter<PackageOwnViewHolder>() {


    var mRecyclerView : RecyclerView? = null
    val mItemlist : ArrayList<PackageData> = itemList

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        return mItemlist.size
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onBindViewHolder(holder: PackageOwnViewHolder?, position: Int) {
        holder?.let {
            it.itemImageView.setImageDrawable(mItemlist[position].drawable)
            it.itemTextViwe1.text = mItemlist[position].label.toString()
            it.itemTextViwe2.text = mItemlist[position].sourceDir.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PackageOwnViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.adapterlow, parent, false)

        mView.setOnClickListener{ view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return PackageOwnViewHolder(mView)

    }

    class PackageViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val itemImageView : ImageView = view.findViewById(R.id.imageView1)
        val itemTextViwe1 : TextView = view.findViewById(R.id.textView1)
        val itemTextViwe2 : TextView = view.findViewById(R.id.textView2)

        interface ItemClickListener {
            fun onItemClick(view: View, position: Int)
        }

    }


}