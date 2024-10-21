package cn.kafuka.util;

import cn.hutool.core.text.StrFormatter;
import cn.kafuka.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.springframework.util.AntPathMatcher;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;


public class StringUtil {

    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 获取字符串有效汉字
     * */
    public static String getChineseValidWord(String origStr){

        //可以替换大部分空白字符， 不限于空格 . 说明:\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
        origStr = origStr.replaceAll("\\s*","");

       /* //完全清除标点
        origStr = origStr.replaceAll("\\pP","");*/

        //清除所有符号,只留下字母 数字  汉字  共3类.
        origStr = origStr.replaceAll("[\\pP\\p{Punct}]","");

        //去除字母和数字
        origStr = origStr.replaceAll("[A-Za-z0-9]*","");

        return origStr;
    }

    /**
     * 获取字符串有效字符(去除空格)
     * */
    public static String getValidWord(String origStr){

        //可以替换大部分空白字符， 不限于空格 . 说明:\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
        origStr = origStr.replaceAll("\\s*","");

        return origStr;
    }

    /**
     * 获取字符串英文单词数量
     * */
    public static int getEnglishWordCount(String origStr){
        Pattern pattern = compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(origStr);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;

    }

    /**
     * 获取字符串中文汉字数量
     * */
    public static int getChineseWordCount(String origStr){
        Pattern pattern = compile("[\u4e00-\u9fa5]");
        Matcher matcher = pattern.matcher(origStr);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;

    }

    /**
     * 获取字符串有效数字数量
     * */
    public static int getNumberWordCount(String origStr){
        Pattern pattern = compile("\\d+");
        Matcher matcher = pattern.matcher(origStr);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }


    /**
     * 获取字符串有效字数(符号也算入，所以直接去取空格后length就行了)
     * */
    public static int getStrValidWordCount(String origStr){
        //return getChineseWordCount(origStr) + getEnglishWordCount(origStr) + getNumberWordCount(origStr);
        int wordCount = 0;
        if(!ObjUtil.isEmpty(origStr)){
            wordCount = getValidWord(origStr).length();
        }
        return wordCount;

    }


    //是否匹配以逗号分隔的字符串，包含中日韩英文,比如  科幻,玄幻,异界  或者 あんまり,じゃなくても,けっこうある
    public static Boolean isMatchComma(String origStr){
        //String regex = "^[\u4e00-\u9fa5a-zA-Z0-9 ]+(,[\u4e00-\u9fa5a-zA-Z0-9 ]+)*$";//只匹配中英文数字
        //匹配中日韩英文数字(2E80～33FFh：中日韩符号区)
        String regex = "^[\u2E80-\u9fa5a-zA-Z0-9]+(,[\u2E80-\u9fa5a-zA-Z0-9]+)*$";
        boolean matches = Pattern.matches(regex, origStr);
        return matches;

    }


    /*由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。
     * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。
     * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。
     * 因此，可以编写如下的方法来获得以字节为单位的字符串长度。*/
    public static int getWordCount(String s)
    {
        int length = 0;
        for(int i = 0; i < s.length(); i++)
        {
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255)
                length++;
            else
                length += 2;

        }
        return length;

    }

    /*基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了*/
    public static  int getWordCountRegex(String s)
    {

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }


    //获取标点符号数量
    public static  int geSymbolCountRegex(String s)
    {
        s = s.replaceAll("[\\pP\\p{Punct}]", "**");
        int length = s.length();
        return length;
    }

    /*按特定的编码格式获取长度*/
    public static int getWordCountCode(String str, String code) throws UnsupportedEncodingException {
        return str.getBytes(code).length;
    }

   /***
     　　* 统计字符串中中文，英文，数字，空格等字符个数
     　　* @param str 需要统计的字符串
     　　*/
   public static void countStr(String str) {


         /**中文字符 */
        int chCharacter = 0;

        /**英文字符 */
        int enCharacter = 0;


        /**英文标点符号 */
        int enSymbol = 0;

        /**空格 */
        int spaceCharacter = 0;

        /**数字 */
        int numberCharacter = 0;

        /**其他字符 */
        int otherCharacter = 0;

     //记录英文标点符号
     StringBuilder sben=new StringBuilder();

        //记录中文字符
        StringBuilder sb1=new StringBuilder();


        //记录英文字符
        StringBuilder sb2=new StringBuilder();


        //记录数字
        StringBuilder sb3=new StringBuilder();


        //记录特殊字符
         StringBuilder sb4=new StringBuilder();


         if(str.equals("") || str==null){
         System.out.println("字符串为空");
           return;
            }
         for (int i = 0; i < str.length(); i++) {
         char tmp = str.charAt(i);
         if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
         enCharacter ++;
         sb2.append(tmp+" ");
         } else if ((tmp >= '0') && (tmp <= '9')) {
         numberCharacter ++;
         sb3.append(tmp +" ");
         } else if (tmp ==' ') {
         spaceCharacter ++;
         }  else if (isChinese(tmp)) {
             chCharacter ++;
             sb1.append(tmp+" ");
         }  else {
         otherCharacter ++;
         sb4.append(tmp +" ");
         }
         }
         System.out.println("字符串:" + str + " \r\n");
         System.out.println("中文字符有:" + chCharacter +"个 ("+sb1.toString()+")");
         System.out.println("英文字符有:" + enCharacter +"个 ("+sb2.toString()+")");
         System.out.println("数字有:" + numberCharacter+"个 ("+sb3.toString()+")");
         System.out.println("空格有:" + spaceCharacter+"个");
         System.out.println("其他字符有:" + otherCharacter+"个 ("+sb4.toString()+")");
 }

     /***
　　 * 判断字符是否为中文
　　 * @param ch 需要判断的字符
　　 * @return 中文返回true，非中文返回false
　　 */
     private static boolean isChinese(char ch) {
        //获取此字符的UniCodeBlock
         Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        // GENERAL_PUNCTUATION 判断中文的“号
        // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
             //System.out.println(ch + " 是中文");
             //sb1.append(ch+" ");
             return true;
        }
        return false;
     }


    /**
     * 该函数判断一个字符串是否包含标点符号（中文英文标点符号）。
     * 原理是原字符串做一次清洗，清洗掉所有标点符号。
     * 此时，如果原字符串包含标点符号，那么清洗后的长度和原字符串长度不同。返回true。
     * 如果原字符串未包含标点符号，则清洗后长度不变。返回false。
     * @param s
     * @return
     */
    public boolean checkSymbol(String s) {
        boolean b = false;

        String tmp = s;
        tmp = tmp.replaceAll("\\p{P}", "");
        if (s.length() != tmp.length()) {
            b = true;
        }

        return b;
    }

    public static int getEnglishWordAmount(String str){
        int blank=0;
        String[] count1 =str.split("[ \\t\\n\\x0B\\f\\r]");
        int len=count1.length;
        for (int i = 0; i < count1.length; i++) {
            if (count1[i].equals("")) {
                blank++;
            }
        }
        return len-blank;
    }


    /**
     * 统计字数，参照MS office word 2007规则
     * @param context 文本内容
     * @return 字数
     */
    public static int getMSWordsCount(String context){
        int words_count = 0;
//中文单词
        String cn_words = context.replaceAll("[^(\\u4e00-\\u9fa5，。《》？；’‘：“”【】、）（……￥！·)]", "");
        int cn_words_count = cn_words.length();
//非中文单词
        String non_cn_words = context.replaceAll("[^(a-zA-Z0-9`\\-=\';.,/~!@#$%^&*()_+|}{\":><?\\[\\])]", " ");
        int non_cn_words_count = 0;
        String[] ss = non_cn_words.split(" ");
        for(String s:ss){
            if(s.trim().length()!=0) non_cn_words_count++;
        }
//中文和非中文单词合计
        words_count = cn_words_count + non_cn_words_count;
        return words_count;
    }

    /**
     * 字数统计
     * @param sContent 正文内容
     * @return
     */
    public static Integer wordCount(String sContent){
        int byteCount = 0;
        //中文字符的处理
        String cn_words = sContent.replaceAll("[^(\\u4e00-\\u9fa5\\x3130-\\x318F\\u0800-\\u4e00，。《》？；’‘：“”【】、）（……￥！·)]", "");
        int cn_word_count = cn_words.length();
        //英文字符的处理
        String en_words = sContent.replaceAll("[^(a-zA-Z0-9`\\-=\\';.,/~!@#$%^&*()_+|}{\\\":><?\\[\\]\" \")]", "");
        int en_words_count = 0;
        String[] en = en_words.split(" ");
        for (String s : en){
            if (s.trim().length() != 0){
                en_words_count++;
            }
        }
        //韩文字符处理
        String kr_words = sContent.replaceAll("[(\\u4e00-\\u9fa5\\u0800-\\u4e00a-zA-Z0-9!\" \"，。《》？；’‘：“”【】、）（……￥！·)]", "");
        int kr_word_count = kr_words.length();
        //合计处理
        byteCount = cn_word_count + en_words_count + kr_word_count;
        return byteCount;
    }

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 读取doc文件内容
     *
     * @
     *            想要读取的文件对象
     * @return 返回文件内容
     * @throws IOException
     */
    public static String doc2String(File file) throws IOException {
        FileInputStream fs = new FileInputStream(file);
        StringBuilder result = new StringBuilder();
        WordExtractor re = new WordExtractor(fs);
        result.append(re.getText());
        re.close();
        return result.toString();
    }

    public static void getWordStr(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        XWPFDocument doc = new XWPFDocument(is);
        List<XWPFParagraph> paras = doc.getParagraphs();

        for (XWPFParagraph para : paras) {
            //当前段落的属性
            CTPPr pr = para.getCTP().getPPr();
            System.out.println(para.getText());
        }
    }

    public static void countLength() throws IOException {
        File file = new File("E:/123.docx");
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = xdoc.getParagraphs();

            int count = 0;
            int i = 1;
            for (XWPFParagraph xwpfParagraph:paragraphs) {
                int linLength = 0;
                String lineStr = "";
                List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
                for (XWPFRun xwpfRun : xwpfRuns) {
                    linLength +=  xwpfRun.toString().trim().length();
                    lineStr += xwpfRun.toString();
                    count += xwpfRun.toString().trim().length();
                }
                System.out.println("第"+i+"行内容：'"+lineStr+"'      长度："+linLength);
                i++;
            }
            System.out.println("文章总行数："+paragraphs.size() +" 行");
            System.out.println("文章总字数："+count);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 空字符串 */
    private static final String NULLSTR = "";

    /** 下划线 */
    private static final char SEPARATOR = '_';

    /**
     * 获取参数不为空值
     *
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue)
    {
        return value != null ? value : defaultValue;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object)
    {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去空格
     */
    public static String trim(String str)
    {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串
     *
     * @param str 字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = str.length() + start;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str 字符串
     * @param start 开始
     * @param end 结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }

        if (end > str.length())
        {
            end = str.length();
        }

        if (start > end)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params 参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params)
    {
        if (isEmpty(params) || isEmpty(template))
        {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean ishttp(String link)
    {
        return StringUtils.startsWithAny(link, Constant.HTTP, Constant.HTTPS);
    }

    /**
     * 字符串转set
     *
     * @param str 字符串
     * @param sep 分隔符
     * @return set集合
     */
    public static final Set<String> str2Set(String str, String sep)
    {
        return new HashSet<String>(str2List(str, sep, true, false));
    }

    /**
     * 字符串转list
     *
     * @param str 字符串
     * @param sep 分隔符
     * @param filterBlank 过滤纯空白
     * @param trim 去掉首尾空白
     * @return list集合
     */
    public static final List<String> str2List(String str, String sep, boolean filterBlank, boolean trim)
    {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(str))
        {
            return list;
        }

        // 过滤空白字符串
        if (filterBlank && StringUtils.isBlank(str))
        {
            return list;
        }
        String[] split = str.split(sep);
        for (String string : split)
        {
            if (filterBlank && StringUtils.isBlank(string))
            {
                continue;
            }
            if (trim)
            {
                string = string.trim();
            }
            list.add(string);
        }

        return list;
    }

    /**
     * 查找指定字符串是否包含指定字符串列表中的任意一个字符串同时串忽略大小写
     *
     * @param cs 指定字符串
     * @param searchCharSequences 需要检查的字符串数组
     * @return 是否包含任意一个字符串
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences)
    {
        if (ObjUtil.isEmpty(cs) || isEmpty(searchCharSequences))
        {
            return false;
        }
        for (CharSequence testStr : searchCharSequences)
        {
            if (containsIgnoreCase(cs, testStr))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 驼峰转下划线命名
     */
    public static String toUnderScoreCase(String str)
    {
        if (str == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (i > 0)
            {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            }
            else
            {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1))
            {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty())
        {
            // 没必要转换
            return "";
        }
        else if (!name.contains("_"))
        {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels)
        {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty())
            {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == SEPARATOR)
            {
                upperCase = true;
            }
            else if (upperCase)
            {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str 指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> strs)
    {
        if (isEmpty(str) || isEmpty(strs))
        {
            return false;
        }
        for (String pattern : strs)
        {
            if (isMatch(pattern, str))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 匹配规则
     * @param url 需要匹配的url
     * @return
     */
    public static boolean isMatch(String pattern, String url)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj)
    {
        return (T) obj;
    }


    /**
     * 去除空格--在Java中，可以使用 trim() 方法过滤字符串前后的特殊空格字符，但是该方法无法过滤特殊空格字符 NBSP（非断空格）。要过滤 NBSP，可以使用正则表达式来实现
     */
    public static String replaceNbspWithSpace(String str) {
        if (str == null) {
            return null;
        }
        String s = str.replaceAll("\\u00A0", "");
        s.trim();
        return s;
    }

    /**
     * 去除所有连续空格
     */
    public static String replaceBlankSpace(String str) {
        if (str == null) {
            return null;
        }
        String s = str.replaceAll("\\s+", "");
        return s;
    }

/*    public static void main(String[] args) throws IOException {
        List<String> strings = Arrays.asList("Hello", "World", "Java");

        List<String> quotedStrings = strings.stream()
                .map(s -> "'" + s + "'")
                .collect(Collectors.toList());

        System.out.println(quotedStrings);
    }*/

}
