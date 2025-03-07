package com.example.writepost_cgs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var pager:ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        //        tabLayout.apply {
        //            this.addTab(this.newTab().setText("Upload"))
        //            this.addTab(this.newTab().setText("History"))
        //        }
        //        val adapter = ViewPagerAdapter(
        //            writeFragment,
        //            LayoutInflater.from(this@MainActivity),
        //            2
        //        )
        val adapter = FragmentAdapter(
            this,
            3
        )
        pager =findViewById<ViewPager2>(R.id.view_pager)
        pager.apply {
            this.adapter = adapter
            TabLayoutMediator(tabLayout, this) { tab, position ->
                when (position) {
                    0 -> tab.text = "Upload"
                    1 -> tab.text = "History"
                    2 -> tab.text = "Settings"
                }
            }.attach()
        }
    }
    fun moveHistoryTab(){
        pager.setCurrentItem(1)
    }

}

class FragmentAdapter(
    fragmentActivity: FragmentActivity,
    val tapCount: Int
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tapCount
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return WriteFragment()
            1 -> return HistotyFragment()
            else -> return SettingsFragment()
        }
    }
}


//class ViewPagerAdapter(
//    val fragment: Fragment,
//    val inflater: LayoutInflater,
//    val amount: Int
//) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
//    inner class ViewHolder(pagerView: View) : RecyclerView.ViewHolder(pagerView) {
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = inflater.inflate(R.layout.activity_write, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//    }
//
//    override fun getItemCount(): Int {
//        return amount
//    }
//}