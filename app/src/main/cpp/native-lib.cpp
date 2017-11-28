#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_pn_sie_likehub_view_activity_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string pass = "likes1128";//关于jni的进一步安全措施待实现
    return env->NewStringUTF(pass.c_str());
}
