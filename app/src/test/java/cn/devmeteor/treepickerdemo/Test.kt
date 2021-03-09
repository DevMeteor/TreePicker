package cn.devmeteor.treepickerdemo

import com.google.gson.Gson
import org.junit.Test
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileWriter
import java.io.InputStreamReader

class Test {
    @Test
    fun test() {
        val data: CustomTreeNode = Gson().fromJson(
            BufferedReader(InputStreamReader(FileInputStream("src/main/assets/a.json"))),
            CustomTreeNode::class.java
        )
        FileWriter("test.txt").use {
            it.write(data.toString())
            it.write("\n\n\n")
            it.write(data.convert().toString())
        }
    }
}