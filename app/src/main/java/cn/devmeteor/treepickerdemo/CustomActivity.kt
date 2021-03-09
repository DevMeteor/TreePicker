package cn.devmeteor.treepickerdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.devmeteor.treepicker.TreePicker
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class CustomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
        val data: CustomTreeNode = Gson().fromJson(
            BufferedReader(InputStreamReader(assets.open("a.json"))),
            CustomTreeNode::class.java
        )
        val picker: TreePicker = findViewById(R.id.picker)
        picker.setData(data)
        picker.setOnPickListener(object : TreePicker.OnPickListener {
            override fun onPick(node: Int, parents: List<Int>, isFinalPick: Boolean) {
                var selected = data
                var parentNodes = ""
                for (i in parents) {
                    selected = selected.next!![i]
                    parentNodes += selected
                }
                Toast.makeText(
                    this@CustomActivity,
                    "${if (isFinalPick) "最后" else ""}选择了${selected.next!![node]}，父节点：$parentNodes",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}