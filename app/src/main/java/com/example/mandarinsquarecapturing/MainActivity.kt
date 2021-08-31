package com.example.mandarinsquarecapturing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var linearLayout_PlayerTop: LinearLayout
    lateinit var linearLayout_PlayerBot: LinearLayout
    lateinit var Main0: LinearLayout
    lateinit var Main1: LinearLayout
    lateinit var Main2: LinearLayout
    lateinit var Main3: LinearLayout
    lateinit var Main4: LinearLayout
    lateinit var Main5: LinearLayout
    lateinit var Main6: LinearLayout
    lateinit var Main7: LinearLayout
    lateinit var Main8: LinearLayout
    lateinit var Main9: LinearLayout
    lateinit var Main10: LinearLayout
    lateinit var Main11: LinearLayout
    lateinit var imgBack: ImageView
    lateinit var imgReady: ImageView
    lateinit var imgBigRock_Main0: ImageView
    lateinit var imgBigRock_Main6: ImageView
    lateinit var imgSmallRock_Main0: ImageView
    lateinit var imgSmallRock_Main1: ImageView
    lateinit var imgSmallRock_Main2: ImageView
    lateinit var imgSmallRock_Main3: ImageView
    lateinit var imgSmallRock_Main4: ImageView
    lateinit var imgSmallRock_Main5: ImageView
    lateinit var imgSmallRock_Main6: ImageView
    lateinit var imgSmallRock_Main7: ImageView
    lateinit var imgSmallRock_Main8: ImageView
    lateinit var imgSmallRock_Main9: ImageView
    lateinit var imgSmallRock_Main10: ImageView
    lateinit var imgSmallRock_Main11: ImageView
    lateinit var txtMain0: TextView
    lateinit var txtMain1: TextView
    lateinit var txtMain2: TextView
    lateinit var txtMain3: TextView
    lateinit var txtMain4: TextView
    lateinit var txtMain5: TextView
    lateinit var txtMain6: TextView
    lateinit var txtMain7: TextView
    lateinit var txtMain8: TextView
    lateinit var txtMain9: TextView
    lateinit var txtMain10: TextView
    lateinit var txtMain11: TextView
    lateinit var txtNamePlayerTop: TextView
    lateinit var txtScorePlayerTop: TextView
    lateinit var txtNamePlayerBot: TextView
    lateinit var txtScorePlayerBot: TextView
    lateinit var btnSame: ImageButton
    lateinit var btnOpp: ImageButton
    var list = listOf<String>("same", "opp")
    var listMain: MutableList<LinearLayout> = mutableListOf()
    var listSmallRock: MutableList<ImageView> = mutableListOf()
    var listScoreMain: MutableList<TextView> = mutableListOf()
    var n: Int = 0
    var turn = "top"
    var pick = false
    var reset = true
    var isRun = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mappingControls()
        addList()
        n = intent.getIntExtra("mode", 0)
        if(n == 2)
            startGame()

        if(n == 1) {
            txtNamePlayerBot.text = "ROBOT"
            txtNamePlayerTop.text = "YOU"
            startGame()
        }

        imgReady.setOnClickListener {
            if(reset)
                restartGame()
        }

    }

    private fun gameWithMachine() {
        reset = false
        setColorPlayer()
        var index = 6
        while(index == 6 || listScoreMain[index].text.toString() == "0") {
            index = (1..11).random()
        }
        val way: String = list.random()
        choose(index, way)
    }

    private fun startGame() {
        setColorPlayer()

        listMain[1].setOnClickListener {
            if(!isRun)
            pickBox(1)
        }

        listMain[2].setOnClickListener {
            if(!isRun)
            pickBox(2)
        }


        listMain[3].setOnClickListener {
            if(!isRun)
            pickBox(3)
        }


        listMain[4].setOnClickListener {
            if(!isRun)
            pickBox(4)

        }


        listMain[5].setOnClickListener {
            if(!isRun)
            pickBox(5)
        }


        listMain[7].setOnClickListener {
            if(!isRun)
            pickBox(7)
        }


        listMain[8].setOnClickListener {
            if(!isRun)
            pickBox(8)
        }


        listMain[9].setOnClickListener {
            if(!isRun)
            pickBox(9)
        }

        listMain[10].setOnClickListener {
            if(!isRun)
            pickBox(10)

        }


        listMain[11].setOnClickListener {
            if(!isRun)
            pickBox(11)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun restartGame() {
        setDefaultBox()
        for(i in 0..11) {
            listScoreMain[i].text = "5"
        }
        listScoreMain[0].text = "10"
        listScoreMain[6].text = "10"
        txtScorePlayerBot.text = "0"
        txtScorePlayerTop.text = "0"
    }

    private fun pickBox(index : Int) {
        setDefaultBox() // set all boxs to default
        pick = true
        listMain[index].setBackgroundResource(R.drawable.box_pick)

        btnSame.setOnClickListener {
            if(!isRun && pick)
            if (listScoreMain[index].text != "0") {
                reset = false
                choose(index, "same")
            }
            else {
                listMain[index].setBackgroundResource(R.drawable.box_default)
                pick = false
                reset = true
                if(turn == "top") {
                    turn = "bot"
                    gameWithMachine()
                }
                else {
                    pick = false
                    isRun = false
                    turn = "top"
                    startGame()
                }

            }
        }

        btnOpp.setOnClickListener {
            if(!isRun && pick)
            if (pick && listScoreMain[index].text != "0") {
                reset = false
                choose(index, "opp")
            }
            else {
                listMain[index].setBackgroundResource(R.drawable.box_default)
                pick = false
                reset = true
                if(turn == "top") {
                    turn = "bot"
                    gameWithMachine()
                }
                else {
                    pick = false
                    isRun = false
                    turn = "top"
                    startGame()
                }
            }
        }
    }

    private fun setColorPlayer() {
        if (turn == "top") {
            linearLayout_PlayerBot.setBackgroundResource(R.drawable.match)
            linearLayout_PlayerTop.setBackgroundResource(R.drawable.box_player)
        }
        if (turn == "bot") {
            linearLayout_PlayerTop.setBackgroundResource(R.drawable.match)
            linearLayout_PlayerBot.setBackgroundResource(R.drawable.box_player)
        }
    }

    fun choose(index: Int, way: String) {
        isRun = true
        if (index == 0 || index == 6) {

            if(index == 0)
            listMain[0].setBackgroundResource(R.drawable.box_radius_left_default)
            if(index == 6)
            listMain[6].setBackgroundResource(R.drawable.box_radius_right_default)
            pick = false
            reset = true
            if(turn == "top") {
                turn = "bot"
                if(n == 1)
                    gameWithMachine()
                else {
                    pick = false
                    isRun = false
                    startGame()
                }
            }
            else {
                pick = false
                isRun = false
                turn = "top"
                startGame()
            }
            return

        }

        if (listScoreMain[index].text == "0") {
            if (way == "same") {
                score(index - 1, way)
                return
            }
            if (way == "opp") {
                score(index + 1, way)
                return
            }
        }

        val n = listScoreMain[index].text.toString().toInt()


        if (way == "same") {
            var i = 0
            val timer = object : CountDownTimer(( n + 1) * 1000.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    var m = same(index + (i-1))
                    if(i > 0) {
                        if (i-1 == 0) {
                            listMain[index].setBackgroundResource(R.drawable.box_default)
                        }
                        else
                        setDefaultBox_OnTick(m)
                    }
                    m = same(index + i)
                    if(i == 0) {
                        listMain[index].setBackgroundResource(R.drawable.box_pick)
                        listScoreMain[m].text = "0"
                    }
                    else
                        setRunBox_OnTick(m)
                    i++
                }

                override fun onFinish() {
                    val k = same(index + n)
                    setDefaultBox_OnTick(k)
                    choose(same(index + n + 1), "same")
                }
            }
            timer.start()
        } else
            if (way == "opp") {
                var i = 0
                val timer = object : CountDownTimer((n+1) * 1000.toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        var m = opp(index - (i-1))
                        if(i > 0) {
                            if (i - 1 == 0) {
                                listMain[index].setBackgroundResource(R.drawable.box_default)
                            }
                            else
                                setDefaultBox_OnTick(m)
                        }
                        m = opp(index - i)
                        if(i == 0) {
                            listMain[index].setBackgroundResource(R.drawable.box_pick)
                            listScoreMain[m].text = "0"
                        }
                        else setRunBox_OnTick(m)
                        i++
                    }

                    override fun onFinish() {
                        var k = opp(index - n)
                        setDefaultBox_OnTick(k)
                        choose(opp(index - n - 1), "opp")
                    }
                }
                timer.start()
            }

    }

    private fun setRunBox_OnTick(m: Int) {
        if(m== 0) {
            listMain[m].setBackgroundResource(R.drawable.box_radius_left_run)
            listScoreMain[m].text = (listScoreMain[m].text.toString().toInt() + 1).toString()
        }
        else if(m == 6) {
            listMain[m].setBackgroundResource(R.drawable.box_radius_right_run)
            listScoreMain[m].text = (listScoreMain[m].text.toString().toInt() + 1).toString()
        }
        else {
            listMain[m].setBackgroundResource(R.drawable.box_run)
            listScoreMain[m].text = (listScoreMain[m].text.toString().toInt() + 1).toString()
        }
    }

    private fun setDefaultBox_OnTick(m: Int) {
        if (m == 0) {
            listMain[m].setBackgroundResource(R.drawable.box_radius_left_default)
        } else if (m == 6) {
            listMain[m].setBackgroundResource(R.drawable.box_radius_right_default)
        } else {
            listMain[m].setBackgroundResource(R.drawable.box_default)
        }
    }


    fun score(index: Int, way: String) {
        if (way == "same") {
            if (listScoreMain[same(index + 1)].text == "0") {

                val timer = object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        setDefaultBox()

                        setJumpBox_OnTick(same(index + 1))
                    }

                    override fun onFinish() {
                        setDefaultBox_OnFinish(same(index + 1))
                        if (listScoreMain[same(index + 2)].text == "0") {
                            score(same(index + 1), way)
                        } else {
                            val timer1 = object : CountDownTimer(1000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    setScoreBox_onTick(same(index + 2))
                                }

                                override fun onFinish() {
                                    setDefaultBox_OnTick(same(index + 2))
                                    score(same(index + 2), way)
                                    listScoreMain[same(index + 2)].text = "0"
                                }
                            }
                            timer1.start()
                            if (turn == "top")
                                txtScorePlayerTop.text = (txtScorePlayerTop.text.toString().toInt() + listScoreMain[same(index + 2)].text.toString().toInt()).toString()
                            if (turn == "bot")
                                txtScorePlayerBot.text = (txtScorePlayerBot.text.toString().toInt() + listScoreMain[same(index + 2)].text.toString().toInt()).toString()


                        }
                    }
                }
                timer.start()
            } else {
                pick = false
                reset = true
                if(turn == "top") {
                    turn = "bot"
                    if(n == 1)
                        gameWithMachine()
                    else {
                        pick = false
                        isRun = false
                        startGame()
                    }
                }
                else {
                    pick = false
                    isRun = false
                    turn = "top"
                    startGame()
                }
                return
            }
        }

        if (way == "opp") {
            if (listScoreMain[opp(index - 1)].text == "0") {

                val timer = object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        setDefaultBox()
                        setJumpBox_OnTick(opp(index - 1))
                    }

                    override fun onFinish() {
                        setDefaultBox_OnFinish(opp(index - 1))

                        if (listScoreMain[opp(index - 2)].text == "0") {
                            score(opp(index - 1), way)
                        } else {
                            val timer1 = object : CountDownTimer(1000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    setScoreBox_onTick(opp(index - 2))
                                }

                                override fun onFinish() {
                                    setDefaultBox_OnTick(opp(index - 2))
                                    score(opp(index - 2), way)
                                    listScoreMain[opp(index - 2)].text = "0"
                                }
                            }
                            timer1.start()

                            if (turn == "top")
                                txtScorePlayerTop.text = (txtScorePlayerTop.text.toString().toInt() + listScoreMain[opp(index - 2)].text.toString().toInt()).toString()
                            if (turn == "bot")
                                txtScorePlayerBot.text = (txtScorePlayerBot.text.toString().toInt() + listScoreMain[opp(index - 2)].text.toString().toInt()).toString()

                        }
                    }
                }
                timer.start()
            } else {
                pick = false
                reset = true
                if(turn == "top") {
                    turn = "bot"
                    if(n == 1)
                        gameWithMachine()
                    else {
                        pick = false
                        isRun = false
                        startGame()
                    }
                }
                else {
                    pick = false
                    isRun = false
                    turn = "top"
                    startGame()
                }
                return
            }
        }
    }

    private fun setScoreBox_onTick(i: Int) {
        if (i == 0)
            listMain[i].setBackgroundResource(R.drawable.box_radius_left_score)
        else if (i == 6)
            listMain[i].setBackgroundResource(R.drawable.box_radius_right_score)
        else
            listMain[i].setBackgroundResource(R.drawable.box_score)
    }

    private fun setDefaultBox_OnFinish(i: Int) {
        if(i == 0)
            Main0.setBackgroundResource(R.drawable.box_radius_left_default)
        else if(i == 6)
            Main6.setBackgroundResource(R.drawable.box_radius_right_default)
        else
            listMain[i].setBackgroundResource(R.drawable.box_default)
    }

    private fun setJumpBox_OnTick(i: Int) {
        if(i == 0)
            Main0.setBackgroundResource(R.drawable.box_radius_left_jump)
        else if(i == 6)
            Main6.setBackgroundResource(R.drawable.box_radius_right_jump)
        else
            listMain[i].setBackgroundResource(R.drawable.box_jump)
    }

    private fun same(i: Int): Int {
        if (i > 11)
            return i % 12
        return i
    }
    private fun opp(i: Int): Int {
        if (i < 0)
            return 12 - ((-i) % 12)
        return i
    }
    private fun setDefaultBox() {
        for(i in listMain) {
            i.setBackgroundResource(R.drawable.box_default)
        }
        listMain[0].setBackgroundResource(R.drawable.box_radius_left_default)
        listMain[6].setBackgroundResource(R.drawable.box_radius_right_default)
    }
    private fun addList() {
        listMain.add(Main0)
        listMain.add(Main1)
        listMain.add(Main2)
        listMain.add(Main3)
        listMain.add(Main4)
        listMain.add(Main5)
        listMain.add(Main6)
        listMain.add(Main7)
        listMain.add(Main8)
        listMain.add(Main9)
        listMain.add(Main10)
        listMain.add(Main11)
        listScoreMain.add(txtMain0)
        listScoreMain.add(txtMain1)
        listScoreMain.add(txtMain2)
        listScoreMain.add(txtMain3)
        listScoreMain.add(txtMain4)
        listScoreMain.add(txtMain5)
        listScoreMain.add(txtMain6)
        listScoreMain.add(txtMain7)
        listScoreMain.add(txtMain8)
        listScoreMain.add(txtMain9)
        listScoreMain.add(txtMain10)
        listScoreMain.add(txtMain11)
        listSmallRock.add(imgSmallRock_Main0)
        listSmallRock.add(imgSmallRock_Main1)
        listSmallRock.add(imgSmallRock_Main2)
        listSmallRock.add(imgSmallRock_Main3)
        listSmallRock.add(imgSmallRock_Main4)
        listSmallRock.add(imgSmallRock_Main5)
        listSmallRock.add(imgSmallRock_Main6)
        listSmallRock.add(imgSmallRock_Main7)
        listSmallRock.add(imgSmallRock_Main8)
        listSmallRock.add(imgSmallRock_Main9)
        listSmallRock.add(imgSmallRock_Main10)
        listSmallRock.add(imgSmallRock_Main11)
    }
    private fun mappingControls() {
        linearLayout_PlayerTop = findViewById(R.id.linearLayout_PlayerTop)
        linearLayout_PlayerBot = findViewById(R.id.linearLayout_PlayerBot)
        Main0 = findViewById(R.id.Main0)
        Main1 = findViewById(R.id.Main1)
        Main2 = findViewById(R.id.Main2)
        Main3 = findViewById(R.id.Main3)
        Main4 = findViewById(R.id.Main4)
        Main5 = findViewById(R.id.Main5)
        Main6 = findViewById(R.id.Main6)
        Main7 = findViewById(R.id.Main7)
        Main8 = findViewById(R.id.Main8)
        Main9 = findViewById(R.id.Main9)
        Main10 = findViewById(R.id.Main10)
        Main11 = findViewById(R.id.Main11)
        imgBack = findViewById(R.id.imgBack)
        imgReady = findViewById(R.id.imgReady)
        imgBigRock_Main0 = findViewById(R.id.imgBigRock_Main0)
        imgBigRock_Main6 = findViewById(R.id.imgBigRock_Main6)
        imgSmallRock_Main0 = findViewById(R.id.imgSmallRock_Main0)
        imgSmallRock_Main1 = findViewById(R.id.imgSmallRock_Main1)
        imgSmallRock_Main2 = findViewById(R.id.imgSmallRock_Main2)
        imgSmallRock_Main3 = findViewById(R.id.imgSmallRock_Main3)
        imgSmallRock_Main4 = findViewById(R.id.imgSmallRock_Main4)
        imgSmallRock_Main5 = findViewById(R.id.imgSmallRock_Main5)
        imgSmallRock_Main6 = findViewById(R.id.imgSmallRock_Main6)
        imgSmallRock_Main7 = findViewById(R.id.imgSmallRock_Main7)
        imgSmallRock_Main8 = findViewById(R.id.imgSmallRock_Main8)
        imgSmallRock_Main9 = findViewById(R.id.imgSmallRock_Main9)
        imgSmallRock_Main10 = findViewById(R.id.imgSmallRock_Main10)
        imgSmallRock_Main11 = findViewById(R.id.imgSmallRock_Main11)
        txtMain0 = findViewById(R.id.txtMain0)
        txtMain1 = findViewById(R.id.txtMain1)
        txtMain2 = findViewById(R.id.txtMain2)
        txtMain3 = findViewById(R.id.txtMain3)
        txtMain4 = findViewById(R.id.txtMain4)
        txtMain5 = findViewById(R.id.txtMain5)
        txtMain6 = findViewById(R.id.txtMain6)
        txtMain7 = findViewById(R.id.txtMain7)
        txtMain8 = findViewById(R.id.txtMain8)
        txtMain9 = findViewById(R.id.txtMain9)
        txtMain10 = findViewById(R.id.txtMain10)
        txtMain11 = findViewById(R.id.txtMain11)
        txtNamePlayerTop = findViewById(R.id.txtNamePlayerTop)
        txtScorePlayerTop = findViewById(R.id.txtScorePlayerTop)
        txtNamePlayerBot = findViewById(R.id.txtNamePlayerBot)
        txtScorePlayerBot = findViewById(R.id.txtScorePlayerBot)
        btnSame = findViewById(R.id.btnSame)
        btnOpp = findViewById(R.id.btnOpp)
    }

}