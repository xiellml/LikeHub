package com.pn.sie.likehub.xutil;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created with Android Studio
 * Email: sielee@163.com
 * Date: 2015/12/17
 * Author: SieLee
 * CopyRight: MilesLife
 * <p>
 * Description: 日志打印工具类
 * 输出结果: AppTag @sie@ <线程> <java类名> <当前行号> <方法名> This is onCreate().
 */
public class LogPrinter {

    //tag都是一样 AppTag
    private final static String tag = "AppTag";
    //分段
    private static final int MAX_LOG_LENGTH = 4000;
    private static final String FILE_PREFIX = "LogPrinter_";
    private static final String FILE_FORMAT = ".log";
    //默认日志级别
    private static int logLevel = Log.VERBOSE;
    //是否显示日志
    private static boolean isPrintLog;
    //存储: <user,logger>
    private static Hashtable<String, LogPrinter> loggers = new Hashtable<>();

    private static String nowCaller = "";

    private LogPrinter() {
    }

    /**
     * @param level      输出过滤(2-7: 越高代表低级别日志无法输出看到VERBOSE==2, DEBUG==3)
     * @param outWhenRel outWhenRel 是否在release时候打印
     */
    public static void enableRel(@SuppressWarnings("SameParameterValue") int level, boolean outWhenRel) {
        logLevel = level;
        isPrintLog = outWhenRel;
    }

    /**
     * @param username coder
     * @return print
     */
    private static LogPrinter user(String username) {
        if (TextUtils.isEmpty(username))
            throw new IllegalArgumentException("coder name is none");
        LogPrinter logPrinter = loggers.get(username);
        if (logPrinter == null) {
            logPrinter = new LogPrinter();
            loggers.put(username, logPrinter);
        }
        return logPrinter;
    }

    private static void splitJson(int priority, String tag, String msg) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        String sept = System.getProperty("line.separator");
        message = sept + message;
        String[] lines = message.split(sept);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLine(tag, false);
    }

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    private static void splitLog(int priority, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LOG_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LOG_LENGTH);
                printSub(priority, tag, sub);
                index += MAX_LOG_LENGTH;
            }
            printSub(priority, tag, msg.substring(index, length));
            //printFile(tag, new File("E://"), null, "", msg);
        } else {
            printSub(priority, tag, msg);
        }
    }

    private static void printSub(int priority, String sTag, String sub) {
        if (priority == Log.ASSERT) {
            Log.wtf(sTag, sub);
        } else {
            Log.println(priority, sTag, sub);
        }
    }

    private static void printFile(String tag, File targetDirectory, @Nullable String fileName, String headString, String msg) {
        if (!targetDirectory.exists() || !targetDirectory.isDirectory()) return;
        fileName = (fileName == null) ? getFileName() : fileName;
        if (save(targetDirectory, fileName, msg)) {
            Log.d(tag, headString + " save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
        } else {
            Log.e(tag, headString + "save log fails !");
        }
    }

    private static boolean save(File dic, @NonNull String fileName, String msg) {

        File file = new File(dic, fileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static String getFileName() {
        Random random = new Random();
        return FILE_PREFIX + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + FILE_FORMAT;
    }

    //-------------------------正式调用--------------------------------
    //每个人可以调用
    public static void addUserLogger(@SuppressWarnings("SameParameterValue") String callerName) {
        user(callerName);
    }

    //只能使用一次, 这次确定是输出谁的日志
    public static void takeOver(@SuppressWarnings("SameParameterValue") String callerName) {
        nowCaller = callerName;
    }

    public static void v(Object obj) {
        user(nowCaller).verbose(obj);
    }

    public static void d(Object obj) {
        user(nowCaller).debug(obj);
    }

    //todo ---------------------------保存到文件-------------------------------------------

    public static void i(Object obj) {
        user(nowCaller).info(obj);
    }

    public static void w(Object obj) {
        user(nowCaller).warn(obj);
    }

    public static void e(Object obj) {
        user(nowCaller).error(obj);
    }

    public static void ex(Exception ex) {
        user(nowCaller).errorEx(ex);
    }

    public static void errThread(String msg, Throwable throwable) {
        user(nowCaller).errorThreadName(msg, throwable);
    }

    public static void json(Object obj) {
        user(nowCaller).jsonFormat(obj);
    }

    /**
     * 获取当前方法名
     *
     * @return func name
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return "*" + nowCaller + "*[ " +
                    st.getFileName() + "(File)# " +
                    st.getMethodName() + "(Function); " +
                    Thread.currentThread().getName() + "(Thread); " +
                    "Line Numbers -> " + st.getLineNumber() + " ] - ";
        }
        return null;
    }

    /**
     * The Log Level:i
     *
     * @param msg 输出对象
     */
    private void info(Object msg) {
        if (isPrintLog) {
            if (logLevel <= Log.INFO) {
                String sign = getFunctionName();
                if (sign != null) {
                    splitLog(Log.INFO, tag, sign + msg);
                } else {
                    splitLog(Log.INFO, tag, msg.toString());
                }
            }
        }

    }

    /**
     * The Log Level:d
     *
     * @param msg 输出对象
     */
    private void debug(Object msg) {
        if (isPrintLog) {
            if (logLevel <= Log.DEBUG) {
                String sign = getFunctionName();
                if (sign != null) {
                    splitLog(Log.DEBUG, tag, sign + msg);
                } else {
                    splitLog(Log.DEBUG, tag, msg.toString());
                }
            }
        }
    }

    private void jsonFormat(Object msg) {
        if (isPrintLog) {
            if (logLevel <= Log.DEBUG) {
                String sign = getFunctionName();
                if (sign != null) {
                    splitJson(Log.DEBUG, tag, sign + msg);
                } else {
                    splitJson(Log.DEBUG, tag, msg.toString());
                }
            }
        }
    }

    /**
     * The Log Level:V
     *
     * @param msg 输出对象
     */
    private void verbose(Object msg) {
        if (isPrintLog) {
            if (logLevel <= Log.VERBOSE) {
                String sign = getFunctionName();
                if (sign != null) {
                    splitLog(Log.VERBOSE, tag, sign + msg);
                } else {
                    splitLog(Log.VERBOSE, tag, msg.toString());
                }
            }
        }
    }

    /**
     * The Log Level:w
     *
     * @param msg 输出对象
     */
    private void warn(Object msg) {
        if (isPrintLog) {
            if (logLevel <= Log.WARN) {
                String sign = getFunctionName();
                if (sign != null) {
                    splitLog(Log.WARN, tag, sign + msg);
                } else {
                    splitLog(Log.WARN, tag, msg.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param msg 输出对象
     */
    private void error(Object msg) {
        if (isPrintLog) {
            if (logLevel <= Log.ERROR) {
                String sign = getFunctionName();
                if (sign != null) {
                    splitLog(Log.ERROR, tag, sign + msg);
                } else {
                    splitLog(Log.ERROR, tag, msg.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param ex 异常
     */
    private void errorEx(Exception ex) {
        if (isPrintLog) {
            if (logLevel <= Log.ERROR) {
                Log.e(tag, "error", ex);
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param log 日志
     * @param tr  错误接口
     */
    private void errorThreadName(String log, Throwable tr) {
        if (isPrintLog) {
            String line = getFunctionName();
            Log.e(tag, line + log + "\n", tr);
        }
    }
}