package com.cb.constant;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;


/**
 * Created by cb on 2017-03-09.
 */
public class Utils {
    /**
     * @param amp        待写入文件的map
     * @param separators map写入文件后key和value的分隔符
     * @param filePath   文件路径
     */
    public static void writeMapToFile(Map<Object, Object> amp, String filePath, String separators) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对字符串进行无效信息清洗
     *
     * @param str
     * @return
     */
    public static String clenaString(String str) {
        str = str.replace(" ", "").replace("\t", "").replace("\r", "").replace("\n", "").replace("　", "").trim();
        return str;
    }

//    /**
//     * 判断是否由单个字的叠词组成
//     *
//     * @param que 10 由简单的叠词组成
//     */
//    public static int checkRepeatModalWord(String que) {
//        //去掉所有的标点符号
//        String queTemp = que.replaceAll("[\\pP\\p{Punct}]", "");
//
//        //纯语气词判断
//        String keys = "哦|哎|哟|喂|哈|呵|啊|吧|罢|呗|啵|的|啦|了|嘞|哩|咧|咯|啰|喽|吗|嘛|嚜|么|麽|哪|呢|呐|呵|哈|兮|噻|哉|嘻|嘿|哟|吼|恩|嗯|额|呀|噢|哇|哼";
//        if (StringUtils.isBlank(TuringStringUtils.replaceKeyWordAll(que, keys, ""))) {
//            return 10;
//        }
//
//        //如果去掉标点符号，长度为1
//        if (queTemp.length() == 1) {
//            return 0;
//        }
//
//
//        //单个语气词重复类型判断
//        char[] ques = queTemp.toCharArray();
//        Set set = new HashSet();
//        for (int i = 0; i < ques.length; i++) {
//            set.add(ques[i]);
//        }
//
//        if (set.size() == 1) {
//            return 10;
//        }
//
//        //如果去重长度变得很小
//        if ((set.size() <= que.length() / 2) && que.length() > 4) {
//            return 10;
//        }
//        return 0;
//    }

//    /**
//     * 判断是否包含[]【】
//     *
//     * @param str
//     * @return
//     */
//    public static boolean isContainsFace(String str) {
//        Pattern p = Pattern.compile("\\[.*\\]");
//        Matcher m = p.matcher(str);
//        if (m.find()) {
//            return true;
//        }
//
//        p = Pattern.compile("【.*】");
//        m = p.matcher(str);
//        if (m.find()) {
//            return true;
//        }
//
//
//        return false;
//    }
//
//    /**
//     * 色黄暴敏感判断
//     * 11
//     */
//    public static int checkSex(String que) {
//        String url = "";
//        try {
//            que = URLEncoder.encode(que, "UTF-8");
//            url = "http://192.168.10.30:8080/nlpfilter/sex-classifier?question=" + que;
//            String result = TuringHttpUtils.get(url, 2000, 2000);
//            if (result.endsWith("negative")) {
//                return 11;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(url);
//            return 0;
//        }
//        return 0;
//    }
//
//    /**
//     * 字符串是否为纯汉字（不包括标点符号等中文字符，纯汉字）
//     * @param str
//     * @return
//     */
//    public static boolean isChinese(String str) {
//
//        Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
//        Matcher m = p.matcher(str);
//        if (m.matches()) {
//            return true;
//        }
//        return false;
//    }
//    /**
//     * 判断字符串是否包含汉字
//     *
//     * @param str
//     * @return
//     */
//    public static boolean isContainChinese(String str) {
//
//        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
//        Matcher m = p.matcher(str);
//        if (m.find()) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 创建excel
     *
     * @param fileName
     */
    public static void createExcel(String fileName) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.createSheet("sheet1");
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取指定路径下的所有文件/文件夹名字
     *
     * @param path
     * @return
     */
    public static List<String> getPathFileName(String path) {
        File file = new File(path);
        List<String> list = new ArrayList<String>();
        if (!file.exists()) {
            return list;
        }

        File f[] = file.listFiles();
        for (int i = 0; i < f.length; i++) {
            File fs = f[i];
            if (fs.isDirectory()) {
            } else {
                list.add(fs.getAbsolutePath());
            }
        }
        return list;
    }

    /**
     * http post
     *
     * @param param
     * @param url
     * @return
     */
    public static String httpPost(String param, String url) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);

            out.flush();
            out.close();
            //
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "Utf-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * httpget
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();

            connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
            connection.addRequestProperty("Content-type", "application/json; charset=utf-8");
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(5000);
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }

    }
    public static void writeToTxt(String path, String content) {
        File file = new File(path);
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file, true);
            writer = new BufferedWriter(fw);
            writer.write(content);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读文件(按行读取，保存到list中，读取文件全部内容)
     *
     * @param filePath 文件路径
     */
    public static Set<String> readFileToSet(String filePath) {
        Set<String> set = new HashSet<String>();
        File file = new File(filePath);
        readFile(file, set);
        return set;
    }

    private static void readFile(File file, Collection collection) {
        BufferedReader reader = null;
        if (file.exists()) {
            try {
                reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "UTF-8"));
                String temp = null;
                while (null != (temp = reader.readLine())) {
                    collection.add(temp.trim());
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 读文件(按行读取，保存到list中，读取文件全部内容)
     *
     * @param filePath 文件路径
     */
    public static List<String> readFileToList(String filePath) {
        List<String> list = new ArrayList<String>();
        File file = new File(filePath);
        readFile(file, list);
        return list;
    }

    private static List<String> listFile(List<String> lstFileNames, File f, String suffix, boolean isdepth) {
        // 若是目录, 采用递归的方法遍历子目录
        if (f.isDirectory()) {
            File[] t = f.listFiles();

            for (int i = 0; i < t.length; i++) {
                if (isdepth || t[i].isFile()) {
                    listFile(lstFileNames, t[i], suffix, isdepth);
                }
            }
        } else {
            String filePath = f.getAbsolutePath();
            if (!suffix.equals("")) {
                int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
                String tempsuffix = "";

                if (begIndex != -1) {
                    tempsuffix = filePath.substring(begIndex + 1, filePath.length());
                    if (tempsuffix.equals(suffix)) {
                        lstFileNames.add(filePath);
                    }
                }
            } else {
                lstFileNames.add(filePath);
            }
        }
        return lstFileNames;
    }
}
