package com.ian.clearscoreinterview.common.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.ian.clearscoreinterview.R
import com.ian.clearscoreinterview.common.Constants
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.properties.Delegates

class ScoreDonut  : View {

    //custom view attributes
    private var mCtx: Context

    var value = 0
    var arcAngle = 0f

    var size by Delegates.notNull<Int>()
    var strokeSize by Delegates.notNull<Int>()
    var arcSize by Delegates.notNull<Int>()
    var textSize by Delegates.notNull<Int>()
    var primaryText by Delegates.notNull<Int>()

    var viewWidth by Delegates.notNull<Int>()
    var viewHeight by Delegates.notNull<Int>()

    //defaults
    var defScoreColor = Color.rgb(255,165,0)
    var defInfoColor = Color.BLACK
    var defBorderColor = Color.GRAY

    //widget attributes
    var score = 0
    var maxScore = 0
    var scoreColor = defScoreColor
    var infoColor = defInfoColor
    var borderColor = defBorderColor

    constructor(mCtx: Context) : super(mCtx){
        this.mCtx = mCtx
        initResources()
    }

    constructor(mCtx: Context, attributeSet: AttributeSet) : super(mCtx, attributeSet){
        this.mCtx = mCtx
        initResources()
        initAttrs(attributeSet)
    }

    constructor(mCtx: Context, attributeSet: AttributeSet, defaultStyleAttr:Int) : super(mCtx, attributeSet, defaultStyleAttr){
        this.mCtx = mCtx
        initResources()
        initAttrs(attributeSet)
    }

    //init attributes
    private fun initAttrs(attributeSet: AttributeSet){

        mCtx.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ScoreDonut,
            0,0
        ).apply {

            try {

                score = getInteger(R.styleable.ScoreDonut_score, 0)
                maxScore = getInteger(R.styleable.ScoreDonut_maxScore, 0)
                arcAngle =   getFloat(R.styleable.ScoreDonut_arcAngle, 10f)
                scoreColor = getColor(R.styleable.ScoreDonut_scoreColor, defScoreColor)
                infoColor = getColor(R.styleable.ScoreDonut_infoColor, defInfoColor)
                borderColor = getColor(R.styleable.ScoreDonut_borderColor, defBorderColor)

            }finally {

                recycle()

            }


        }

    }


    //initialize resources
    private fun initResources(){
        //initialize resources
        strokeSize = resources.getDimensionPixelSize(R.dimen.donut_stroke)
        arcSize = resources.getDimensionPixelSize(R.dimen.arc_stroke_size)
        textSize = resources.getDimensionPixelSize(R.dimen.donut_text)
        primaryText = resources.getDimensionPixelSize(R.dimen.donut_score_text)


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        size = viewWidth
        //adjust angle
        arcAngle = angle(score, maxScore)
    }

    @JvmName("setValue1")
    fun setValue(value: Int){
        this.value = value
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

        //only draw if canvas is ready
        canvas?.let { c ->

            //reset canvas
            c.drawColor(Color.alpha(Color.CYAN))

            //paint object
            val paint = Paint();
            //create oval object
            val rectF = RectF(20f, 20f, size - 20f, size - 20f)
            //arc oval
            val rectF2 = RectF(40f, 40f, size - 40f, size - 40f)
            //draw border
            drawBorder(c, paint, rectF)
            //draw arc
            drawArc(c,paint,rectF2)
            //intro text
            drawIntro(c, paint)
            //actual score
            drawActualScore(c, paint)
            //outro
            drawOutroText(c, paint)

        }

    }


    //draw border
    private fun drawBorder(canvas: Canvas, paint: Paint, oval:RectF){

        //adjust paint properties
        paint.color = borderColor
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeSize.toFloat()

        //draw border
        canvas.drawArc(oval, 0F, 360F, false, paint)

    }


    //draw arc
    private fun drawArc(canvas: Canvas, paint: Paint, oval:RectF){

        //adjust paint properties
        paint.color = scoreColor
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = arcSize.toFloat()

        //draw arc
        canvas.drawArc(oval, -90F, arcAngle, false, paint)

    }

    //draw intro text
    private fun drawIntro(canvas: Canvas, paint: Paint){

        paint.textSize = textSize.toFloat()
        paint.style = Paint.Style.FILL
        paint.color = infoColor

        //position x
        val xPos = width / 2 - (paint.measureText(Constants.SCORE_INTRO) / 2)
        val yPos = (height / 3f - (paint.descent() + paint.ascent()) / 2)

        canvas.drawText(Constants.SCORE_INTRO, xPos, yPos, paint)


    }

    //draw actualScore
    private fun drawActualScore(canvas: Canvas, paint: Paint){

        paint.textSize = primaryText.toFloat()
        paint.color = scoreColor

        //position x
        val xPos = width / 2 - (paint.measureText(score.toString()) / 2)
        val yPos = (height / 2 - (paint.descent() + paint.ascent()) / 2)

        canvas.drawText(score.toString(), xPos, yPos, paint)

    }

    //draw outro text
    private fun drawOutroText(canvas: Canvas, paint: Paint){

        paint.textSize = textSize.toFloat()
        paint.color = infoColor

        //position x
        val xPos = width / 2 - (paint.measureText(Constants.SCORE_OUTRO + maxScore) / 2)
        val yPos = (((height / 1.5f) + (height / 4 ) * 0) - (paint.descent() + paint.ascent()) / 2)

        canvas.drawText(Constants.SCORE_OUTRO + maxScore, xPos, yPos, paint)

    }

    //calculate angle
    private fun angle(score: Int, maxScore: Int): Float {
        val ratio = score.toDouble() / maxScore
        val arcAng = ratio * 360

        //confirm score <= maxScore
        return if(score <= maxScore)
            arcAng.toFloat().roundNumber()
        else
            0.0f.roundNumber()
    }

    //round number to 2dp
    private fun Float.roundNumber() : Float{
       val format = DecimalFormat("#.##")
       format.roundingMode = RoundingMode.CEILING
       return format.format(this).toFloat()
    }




}