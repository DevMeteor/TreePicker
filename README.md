# TreePicker

TreePicker支持对树形结构中的节点进行选择，常见的应用场景有地址选择器等。

### 效果预览

![默认效果和数据类](F:\AndroidStudioProjects\TreePicker\img\ex1.jpg)![使用自定义属性和数据类](F:\AndroidStudioProjects\TreePicker\img\ex2.jpg)

### 使用方法

#### 1.添加依赖

在工程根目录下的build.gradle添加

```
allprojects {
    repositories {
        ...
        maven{url 'https://jitpack.io'}
    }
}
```

在app下的build.gradle添加

```
implementation 'com.github.DevMeteor:TreePicker:1.0.0'
```

#### 2.样式设置

##### 方法一	使用自定义属性

|          属性          |        格式        | 描述              |
| :--------------------: | :----------------: | ----------------- |
|     tabItemPadding     |     dimension      | tab块的左右边距   |
|       tabHeight        |     dimension      | tab栏高度         |
|  tabTextSelectedColor  |       color        | tab栏文字选中颜色 |
|   tabTextNormalColor   |       color        | tab栏文字默认颜色 |
|      tabUnderline      | reference(drawble) | tab选中时的下标记 |
|      tabTextSize       |     dimension      | tab栏文字大小     |
|     levelTextSize      |     dimension      | 列表文字大小      |
|      levelPadding      |     dimension      | 列表item内边距    |
|   levelCheckDrawable   | reference(drawble) | 列表item选中标记  |
| levelTextSelectedColor |       color        | 列表文字选中颜色  |
|  levelTextNormalColor  |       color        | 列表文字默认颜色  |

##### 方法二	使用Configuration类

```kotlin
val config=Configuration.Builder(this)
            .levelCheckDrawable() //drawable或其资源id
            .levelPadding()	//单位px
            .levelTextNormalColor()
            .levelTextSelectedColor()
            .levelTextSize()	//单位px
            .tabHeight()	//单位px
            .tabItemPadding()	//单位px
            .tabTextNormalColor()
            .tabTextSelectedColor()
            .tabTextSize()	//单位px
            .tabUnderline()	//drawable或其资源id
            .build()
picker.config(config)
```

尺寸类属性建议使用context.resources.getXXX获取

### 3.基本使用

在布局文件中添加

```xml
<cn.devmeteor.treepicker.TreePicker
        android:id="@+id/picker"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

在Activity中使用

```kotlin
val picker: TreePicker = findViewById(R.id.picker)
picker.setData(data)	//设置数据
picker.setOnPickListener(object : TreePicker.OnPickListener {
    /**
     * node：当前选中的节点在所有同级节点列表中的下标
     * parents：当前结点所有父节点在其所有同级节点列表中的下标
     * isFinalPick：当前节点是否是叶子节点
     */
    override fun onPick(node: Int, parents: List<Int>, isFinalPick: Boolean) {
        var selected = data
        var parentNodes = ""
        for (i in parents) {
            selected = selected.next!![i]
            parentNodes += selected
        }
        Toast.makeText(
            this@MainActivity,
            "${if (isFinalPick) "最后" else ""}选择了${selected.next!![node]}，父节点：$parentNodes",
            Toast.LENGTH_SHORT
        ).show()
    }
})
```

### 4.自定义数据类

自定义数据类须实现Convertible接口并重写convert方法以实现将自定义数据类转换为TreeNode的算法，示例：[CustomTreeNode](app/src/main/java/cn/devmeteor/treepickerdemo/CustomTreeNode.kt)

