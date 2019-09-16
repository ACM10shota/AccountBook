package com.miwa.myaccounts

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.miwa.my.R
import kotlinx.android.synthetic.main.activity_accounts_graph.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.data.PieData
import java.util.Arrays.asList
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.components.Legend
import java.util.*
import kotlin.collections.ArrayList

class AccountsGraph : AppCompatActivity() {
    lateinit var mPieChart: PieChart
    val accountDbHandler = AccountDBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts_graph)

        val database = accountDbHandler.writableDatabase
        //SELECT用SQL文
        val sqlSelectFood = "SELECT * FROM accounttable WHERE label = 1"
        val sqlSelectHang = "SELECT * FROM accounttable WHERE label = 2"
        val sqlSelectOnline = "SELECT * FROM accounttable WHERE label = 3"
        val sqlSelectLoan = "SELECT * FROM accounttable WHERE label = 4"

        //SELECT実行
        val cursolFood = database.rawQuery(sqlSelectFood, null)
        val cursolHang = database.rawQuery(sqlSelectHang, null)
        val cursolOnline = database.rawQuery(sqlSelectOnline, null)
        val cursolLoan = database.rawQuery(sqlSelectLoan, null)

        //食費
        var foodRate = 40f
        //表の出力
        while (cursolFood.moveToNext()) {
            val cursolIndex = cursolFood.getColumnIndex("spend")
            foodRate = cursolFood.getString(cursolIndex).toFloat()
        }

        //遊休費
        var hangRate = 30f
        while (cursolHang.moveToNext()) {
            val cursolIndex = cursolHang.getColumnIndex("spend")
            hangRate = cursolHang.getString(cursolIndex).toFloat()
        }

        //通販代
        var onlineRate = 20f
        while (cursolOnline.moveToNext()) {
            val cursolIndex = cursolOnline.getColumnIndex("spend")
            onlineRate = cursolOnline.getString(cursolIndex).toFloat()
        }

        //ローン
        var loanRate = 10f
        while (cursolLoan.moveToNext()) {
            val cursolIndex = cursolLoan.getColumnIndex("spend")
            loanRate = cursolLoan.getString(cursolIndex).toFloat()
        }


//        //データ取得
//        var chartBar = bar_chart
//        chartBar.data = BarData(getBarData())
//
//        //Y軸(左)の設定
//        chartBar.axisLeft.apply {
//            axisMinimum = 0f
//            axisMaximum = 100f
//            labelCount = 5
//            setDrawTopYLabelEntry(true)
//            //setValueFormatter{ value, axis -> "" + value.toInt()}
//        }
//
//        //Y軸(右)の設定
//        chartBar.axisRight.apply {
//            setDrawLabels(false)
//            setDrawGridLines(false)
//            setDrawZeroLine(false)
//            setDrawTopYLabelEntry(true)
//        }
//
//        //X軸の設定
//        val lables = arrayOf("", "国語", "数学", "英語")
//        chartBar.xAxis.apply {
//            valueFormatter = IndexAxisValueFormatter(lables) as ValueFormatter?
//            labelCount = 3
//            position = XAxis.XAxisPosition.BOTTOM
//            setDrawLabels(true)
//            setDrawGridLines(false)
//            setDrawAxisLine(true)
//        }
//
//        //グラフ上の表示
//        chartBar.apply {
//            setDrawValueAboveBar(true)
//            description.isEnabled = false
//            isClickable = false
//            legend.isEnabled = false
//            setScaleEnabled(false)
//            animateY(1200, Easing.EaseInBack)
//        }


//        //レーダーチャート
//        //グラフ初期化
//        val chartRadar = findViewById(R.id.chart) as RadarChart
//
//        val entries = ArrayList<RadarEntry>()
//        val own = intArrayOf(3, 4, 3, 4, 3, 5)
//        entries.add(RadarEntry(own[0].toFloat(), 0.toFloat()))
//        entries.add(RadarEntry(own[1].toFloat(), 1.toFloat()))
//        entries.add(RadarEntry(own[2].toFloat(), 2.toFloat()))
//        entries.add(RadarEntry(own[3].toFloat(), 3.toFloat()))
//        entries.add(RadarEntry(own[4].toFloat(), 4.toFloat()))
//        entries.add(RadarEntry(own[5].toFloat(), 5.toFloat()))
//
//        val average = intArrayOf(5, 3, 4, 2, 4, 4)
//        val entries2 = ArrayList<RadarEntry>()
//        entries.add(RadarEntry(average[0].toFloat(), 0.toFloat()))
//        entries.add(RadarEntry(average[1].toFloat(), 1.toFloat()))
//        entries.add(RadarEntry(average[2].toFloat(), 2.toFloat()))
//        entries.add(RadarEntry(average[3].toFloat(), 3.toFloat()))
//        entries.add(RadarEntry(average[4].toFloat(), 4.toFloat()))
//        entries.add(RadarEntry(average[5].toFloat(), 5.toFloat()))
//
//        val dataSet1 = RadarDataSet(entries, "自分の評価")
//        val dataSet2 = RadarDataSet(entries2, "全国平均")
//
//        //色の設定
//        dataSet1.setColor(Color.RED);
//        dataSet1.setDrawFilled(true);
//        dataSet2.setColor(Color.CYAN);
//        dataSet2.setDrawFilled(true);
//
//        //ラベルづけ
//        val labels = ArrayList<String>()
//        labels.add("盛付")
//        labels.add("スープ")
//        labels.add("接客")
//        labels.add("中毒性")
//        labels.add("店内")
//        labels.add("麺")
//
//        val dataSets = ArrayList<IRadarDataSet>()
//        dataSets.add(dataSet2)
//        dataSets.add(dataSet1)
//
//        //データの初期化
//        val data = RadarData(dataSets)
//        chartRadar.data = data//データを設定
//        //chartRadar.setDescription("脚注が表示される")
//        chartRadar.isRotationEnabled = false//ドラックすると回転するので制御する
//        chartRadar.invalidate()//チャートの表示を更新したいときに呼ぶ
//        chartRadar.yAxis.setDrawLabels(false)//値の目盛表記

        //円グラフ
        mPieChart = findViewById(R.id.pie_chart);

        mPieChart.setUsePercentValues(true)
        //mPieChart.setDescription("チャートの説明")

        val legend = mPieChart.legend
        //legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT)

        // 円グラフに表示するデータ
        val values = Arrays.asList(foodRate, hangRate, onlineRate, loanRate)
        val entries = ArrayList<PieEntry>()
        for (i in 0..3) {
            entries.add(PieEntry(values.get(i), i.toFloat()))
        }

        val dataSet = PieDataSet(entries, "チャートのラベル")
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        dataSet.setDrawValues(true)

        val labels = Arrays.asList("A", "B", "C", "D")
        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.WHITE)

        mPieChart.setData(pieData)
    }

    override fun onDestroy() {
        accountDbHandler.close()
        super.onDestroy()
    }

    private fun getBarData(): ArrayList<IBarDataSet> {
        val entries = ArrayList<BarEntry>().apply {
            add(BarEntry(1f, 60f))
            add(BarEntry(2f, 80f))
            add(BarEntry(3f, 70f))
        }

        val dataSet = BarDataSet(entries, "bar").apply {
            //valueFormatter = IValueFormatter { value, _, _, _ -> "" + value.toInt() } as ValueFormatter?

            isHighlightEnabled = false

            setColors(intArrayOf(R.color.material_blue, R.color.material_green, R.color.material_yellow), this@AccountsGraph)
        }

        var bars = ArrayList<IBarDataSet>()
        bars.add(dataSet)

        return bars
    }
}
