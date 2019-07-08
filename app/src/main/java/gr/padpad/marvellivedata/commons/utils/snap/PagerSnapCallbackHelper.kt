package gr.padpad.marvellivedata.commons.utils.snap

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

import timber.log.Timber

class PagerSnapCallbackHelper : PagerSnapHelper() {

    private var  onSnapFinishCallback : OnSnapFinishCallback

    init {
        this.onSnapFinishCallback = object : OnSnapFinishCallback {
            override fun onSnapFinished(position: Int) {
                Timber.d("Snap finished!! position = %s", position)
            }
        }
    }

    @Synchronized
    fun setOnSnapFinishCallback(onSnapFinishCallback: OnSnapFinishCallback) {
        this.onSnapFinishCallback = onSnapFinishCallback
    }

    @Throws(IllegalStateException::class)
    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)

        if (recyclerView == null) {
            return
        }

        var layoutManager: LinearLayoutManager? = null
        try {
            layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        } catch (e: Exception) {
            Timber.w("PagerSnapCallbackHelper should be used with a LinearLayoutManager", e)
        }


        if (layoutManager == null) {
            return
        }

        val finalLayoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val visibleItem = getFirstVisiblePosition(finalLayoutManager)

                    if (visibleItem == RecyclerView.NO_POSITION) {
                        return
                    }

                    this@PagerSnapCallbackHelper.onSnapFinishCallback!!.onSnapFinished(visibleItem)
                }
            }
        })
    }

    private fun getFirstVisiblePosition(layoutManager: LinearLayoutManager): Int {
        var visibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
        if (visibleItem == RecyclerView.NO_POSITION) {
            visibleItem = layoutManager.findFirstVisibleItemPosition()
        }

        return visibleItem
    }

    //    @Override
    //    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
    //        int targetPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
    //        if (this.onSnapFinishCallback != null && targetPosition >= 0) {
    //            this.onSnapFinishCallback.onSnapFinished(targetPosition);
    //        }
    //        return targetPosition;
    //    }

    interface OnSnapFinishCallback {
        fun onSnapFinished(position: Int)
    }
}