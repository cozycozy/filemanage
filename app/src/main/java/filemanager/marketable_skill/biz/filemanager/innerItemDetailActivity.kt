package filemanager.marketable_skill.biz.filemanager

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import filemanager.marketable_skill.biz.filemanager.extentions.DetailInfo
import kotlinx.android.synthetic.main.activity_inneritem_detail.*

/**
 * An activity representing a single innerItem detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [innerItemListActivity].
 */
class innerItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inneritem_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        d_file_name.text = intent.getStringExtra(DetailInfo.NAME.name)
        d_file_size.text = intent.getStringExtra(DetailInfo.STORAGE.name)
        d_file_path.text = intent.getStringExtra(DetailInfo.PATH.name)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            val arguments = Bundle()
//            arguments.putString(innerItemDetailFragment.ARG_ITEM_ID,
//                    intent.getStringExtra(innerItemDetailFragment.ARG_ITEM_ID))
//            val fragment = innerItemDetailFragment()
//            fragment.arguments = arguments
//            supportFragmentManager.beginTransaction()
//                    .add(R.id.inneritem_detail_container, fragment)
//                    .commit()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, innerItemListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
