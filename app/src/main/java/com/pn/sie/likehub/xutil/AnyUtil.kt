package com.pn.sie.likehub.xutil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
//https://github.com/AbrahamCaiJin/CommonUtilLibrary
//http://www.oschina.net/question/1766970_193252
/**
 *
String enStr = android.util.Base64.encodeToString(originStr.getBytes(),Base64.DEFAULT);

byte b[] = android.util.Base64.decode(enStr,Base64.DEFAULT);

String deStr = new String(b);
 */
//这里Any类型是Kotlin中所有类的父类，类似于Java中的Object类
fun Any.getDimension(context: Context, resId: Int) = context.resources.getDimension(resId)

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

//内联模式(代码替换, 而不会一个创建函数包裹的对象): inline
//reified具体化类型: 在函数内部通过类型来恢复一个类，而不是将类的类型作为一个参数传入
inline public fun <reified T : Activity> Activity.navigate(param: String) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("acId", param)//只一个参数: acId; 比较局限
    startActivity(intent)
}