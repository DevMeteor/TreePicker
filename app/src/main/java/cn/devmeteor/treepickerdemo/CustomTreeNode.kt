package cn.devmeteor.treepickerdemo

import cn.devmeteor.treepicker.TreeNode
import cn.devmeteor.treepicker.TreePicker
import kotlin.random.Random

class CustomTreeNode() : TreePicker.Convertible {
    companion object {
        private val WEATHERS = arrayOf("晴", "晴转多云", "多云", "小雨", "中雨", "大雨")
    }

    var name: String? = null
    var next: MutableList<CustomTreeNode>? = null

    constructor(name: String) : this() {
        this.name = name
    }

    constructor(name: String, next: MutableList<CustomTreeNode>) : this() {
        this.name = name
        this.next = next
    }

    var weather: String = WEATHERS[Random(System.currentTimeMillis()).nextInt(WEATHERS.size)]

    override fun toString(): String =
        "{name:$name,weather:$weather}"

    override fun convert(): TreeNode = convertNodes(this)!!

    private fun convertNodes(node: CustomTreeNode?): TreeNode? {
        if (node == null)
            return null
        val converted = TreeNode(node.name!!)
        if (node.next != null) {
            converted.next = ArrayList()
            for (n in node.next!!) {
                (converted.next as ArrayList<TreeNode>).add(convertNodes(n)!!)
            }
        }
        return converted
    }

}