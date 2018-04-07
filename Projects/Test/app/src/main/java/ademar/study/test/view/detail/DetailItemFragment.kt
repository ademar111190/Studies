package ademar.study.test.view.detail

import ademar.study.test.R
import ademar.study.test.injection.GlideApp
import ademar.study.test.navigation.ARG_IMAGE
import ademar.study.test.navigation.ARG_TITLE
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.detail_item_fragment.*

class DetailItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_item_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = arguments?.getString(ARG_TITLE)
        GlideApp.with(this)
                .load(arguments?.getString(ARG_IMAGE))
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
    }

}
