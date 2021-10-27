package com.ian.clearscoreinterview.common.views

import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ian.clearscoreinterview.R
import com.ian.clearscoreinterview.presentation.MainActivity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.AttributeSetBuilder
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ScoreDonutTest {

    private lateinit var scoreDonut: ScoreDonut
    private lateinit var root:FrameLayout
    private lateinit var attributeSet:AttributeSet

    @Before
    fun setUp(){

        val controller = Robolectric.buildActivity(MainActivity::class.java)
        val activity = controller.get()

        attributeSet = buildAttributeSet {
            addAttribute(R.styleable.ScoreDonut_score, 200f.toString())
            addAttribute(R.styleable.ScoreDonut_maxScore, 300f.toString())
        }

        scoreDonut = ScoreDonut(activity, attributeSet)
    }

    @Test
    fun `check angles conform correctly`(){
        assertEquals(0.67,scoreDonut.arcAngle)
    }

    private fun buildAttributeSet(attrs: AttributeSetBuilder.() -> Unit): AttributeSet {
        return with(Robolectric.buildAttributeSet()) {
            attrs()
            build()
        }
    }



}