package com.greatcattle.utils;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;

public class StrUtil {
    
    
    /**
     * 构造函数.
     */
    protected StrUtil() {
        
    }
    
    /**
     * 将Unicode码字符串转为为GBK码.
     * 
     * @param strIn
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String GBToUnicode(final String strIn) {
        String strOut = null;
        
        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            final byte[] b = strIn.getBytes("GBK");
            strOut = new String(b, "ISO8859_1");
        } catch (final Exception e) {
        }
        return strOut;
    }
    
    /**
     * 将GBK码转换为Unicode码.
     * 
     * @param strIn
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String unicodeToGB(final String strIn) {
        String strOut = null;
        
        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            final byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        } catch (final Exception e) {
        }
        return strOut;
    }
    
    /**
     * strFormat:将字符串中%s用参数替换，格式化输出. 最多支持10个<br/>  
     * @author samuel  
     * @param str
     * @param args
     * @return  
     * @since JDK 1.7
     */
    public static String strFormat(String str, String ... args){
        switch(args.length){
            case 0:
                return str;
            case 1:
                try{
                    return String.format(str, args[0]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0],"##","##","##","##","##","##","##","##","##");
                }
            case 2:
                try{
                    return String.format(str, args[0], args[1]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1],"##","##","##","##","##","##","##","##");
                }
                
            case 3:
                try{
                    return String.format(str, args[0], args[1], args[2]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2],"##","##","##","##","##","##","##");
                }
            case 4:
                try{
                    return String.format(str, args[0], args[1], args[2], args[3]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2], args[3],"##","##","##","##","##","##");
                }
            case 5:
                try{
                    return String.format(str, args[0], args[1], args[2], args[3], args[4]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2], args[3], args[4],"##","##","##","##","##");
                }
                
            case 6:
                try{
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5],"##","##","##","##");
                }
                
            case 7:
                try{
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6],"##","##","##");
                }
                
            case 8:
                try{
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7],"##","##");
                }
                
            case 9:
                try{
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
                }catch(MissingFormatArgumentException e){
                    return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],"##");
                }
                
            case 10:
                return String.format(str, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
            default:
                throw new RuntimeException("Too many args");
        }
    }
    
    /**
     * 字符串编码类型转换.
     * 
     * @param str
     *            待转换的字符串
     * @param oldCharset
     *            待转换的字符串的编码类型
     * @param newCharset
     *            新的编码类型
     * @return 转换成新编码类型的字符串
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String encode(final String str, final String oldCharset, final String newCharset) {
        if (str == null) {
            return str;
        }
        String newStr = null;
        try {
            newStr = new String(str.getBytes(oldCharset), newCharset);
        } catch (final Exception e) {
        }
        return newStr;
        
    }
    
    /**
     * 将以sgn为分隔符的字符串转化为数组.
     * 
     * @param str
     *            String
     * @param sgn
     *            String
     * @return String[]
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2004-11-4 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String[] split(String str, final String sgn) {
        String[] returnValue = null;
        if (!StrUtil.strnull(str).equals("")) {
            final Vector vectors = new Vector();
            int i = str.indexOf(sgn);
            String tempStr = "";
            for (; i >= 0; i = str.indexOf(sgn)) {
                tempStr = str.substring(0, i);
                str = str.substring(i + 1);
                vectors.addElement(tempStr);
            }
            if (!str.equalsIgnoreCase("")) {
                vectors.addElement(str);
            }
            returnValue = new String[vectors.size()];
            for (i = 0; i < vectors.size(); i++) {
                returnValue[i] = (String) vectors.get(i);
                returnValue[i] = returnValue[i].trim();
            }
        }
        return returnValue;
    }
    
    /**
     * 把数组转化为字符串.
     * 
     * @param array
     *            字符串数组
     * @param split
     *            分割字符串
     * @return string whose format is like "1,2,3"
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String arrayToStr(final String[] array, final String split) {
        if (array == null || array.length < 1) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(split);
            }
            sb.append(StrUtil.strnull(array[i]));
        }
        return sb.toString();
        
    }
    
    /**
     * @param array
     *            String[]
     * @param split
     *            String
     * @return string whose format like " '1','2','3'"
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String arrayToStrWithStr(final String[] array, final String split) {
        return StrUtil.arrayToStrWithStr(array, split, "0");
        
    }
    
    /**
     * @param array
     *            String[]
     * @param split
     *            String
     * @param optType
     *            操作类型0:普通; 1:在解析角色时去掉
     * @return String string whose format like " '1','2','3'"
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String arrayToStrWithStr(final String[] array, final String split,
        final String optType) {
        if (array == null || array.length < 1) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("'");
            if (optType.equals("1")) {
                final String temp = StrUtil.strnull(array[i]);
                sb.append(temp.substring(1, temp.length()));
            } else {
                sb.append(StrUtil.strnull(array[i]));
            }
            sb.append("'");
            
        }
        return sb.toString();
        
    }
    
    /**
     * 将以sgn为分隔符的字符串转化为数组.
     * 
     * @param str String
     * @param sgn String
     * @return String[]
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2004-11-4 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String[] strConvertoArray(final String str, final String sgn) {
        final StringTokenizer st = new StringTokenizer(str, sgn);
        final String[] retstr = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            retstr[i] = st.nextToken();
        }
        return retstr;
    }
    
    /**
     * 将以sgn为分隔符的字符串转化为List链表.
     * 
     * @param str
     *            String 要处理的字符串
     * @param sgn
     *            String 间隔符
     * @return List
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2005-03-17 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static List strConvertoList(final String str, final String sgn) {
        final StringTokenizer st = new StringTokenizer(str, sgn);
        final List result = new LinkedList();
        
        for (int i = 0; st.hasMoreTokens(); i++) {
            result.add(st.nextToken());
        }
        return result;
    }
    
    /**
     * 1 --> 00001将整数转化为指定长度字符串(lpMaxLength为5).
     * 
     * @param lpInt
     *            int
     * @param lpMaxLength
     *            int
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String intToStr(final int lpInt, final int lpMaxLength) {
        int length, i;
        String returnValue = "";
        
        length = Integer.toString(lpInt).length();
        if (length < lpMaxLength) {
            i = lpMaxLength - length;
            while (i > 0) {
                returnValue = returnValue + "0";
                i--;
            }
            returnValue = returnValue + Integer.toString(lpInt);
        } else {
            returnValue = Integer.toString(lpInt);
        }
        return returnValue;
    }
    
    /**
     * 将字符集转换成整型.
     * 
     * @param source
     *            String
     * @return int
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static int toInt(final String source) {
        try {
            return Integer.parseInt(source);
        } catch (final NumberFormatException notint) {
            return 0;
        }
    }
    
    /**
     * 取路径后的文件名，也就是路径字串最后一个斜杠后的字串.
     * 
     * @param path
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getPathFile(final String path) {
        String substr = "";
        try {
            if (path != null && !path.equals("")) {
                final int i = path.lastIndexOf("/");
                substr = path.substring(i + 1).trim();
            }
        } catch (final Exception ex) {
            System.err.println(ex);
        }
        return substr;
    }
    
    /**
     * 取小数点后的字串，也就是小数点后的字串.
     * 
     * @param str
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getLastTwo(final String str) {
        String substr = "";
        try {
            if (str != null && !str.equals("")) {
                final int i = str.lastIndexOf(".");
                substr = str.substring(i + 1).trim();
            }
        } catch (final Exception ex) {
            System.err.println(ex);
        }
        return substr;
    }
    
    /**
     * 取得文件名的文件类型(如2003001.doc-->doc).
     * 
     * @param lpFileName
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getFileType(final String lpFileName) {
        String lpReturnValue = "";
        
        if (lpFileName != null && !lpFileName.equals("")) {
            final int i = lpFileName.lastIndexOf(".");
            lpReturnValue = lpFileName.substring(i, lpFileName.length());
        }
        return lpReturnValue;
    }
    
    /**
     * 返回位于 String 对象中指定位置的子字符串.
     * 
     * @param str
     *            String
     * @param beginIndex
     *            int
     * @param endIndex
     *            int
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getSubString(String str, final int beginIndex, final int endIndex) {
        String str1 = "";
        
        if (str == null) {
            str = "";
        }
        if (str.length() >= beginIndex && str.length() >= endIndex) {
            str1 = str.substring(beginIndex, endIndex);
        } else {
            str1 = str;
        }
        return str1;
    }
    
    /**
     * 如果入参是null或者"",用另一入参rpt替代入参返回，否则返回入参的trim().
     * 
     * @param str
     *            String
     * @param rpt
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final String str, final String rpt) {
        if (str == null || str.equals("null") || str.equals("") || str.trim() == null) {
            return rpt;
        } else {
            return str.trim();
        }
    }
    
    /**
     * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号.
     * 
     * @param strn
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final String strn) {
        return StrUtil.strnull(strn, "");
    }
    
    /**
     * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号.
     * 
     * @param str
     *            Object
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final Object str) {
        String result = "";
        if (str == null) {
            result = "";
        } else {
            result = str.toString();
        }
        return result;
    }
    
    /**
     * 适用于web层 为检查null值，如果为null，将返回"&nbsp;"，不为空时将替换其非html符号.
     * 
     * @param strn
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String repnull(final String strn) {
        return StrUtil.strnull(strn, "&nbsp;");
    }
    
    /**
     * 把Date的转化为字符串，为空时将为"0000-00-00 00:00:00".
     * 
     * @param strn
     *            Date
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final Date strn) {
        String str = "";
        
        if (strn == null) {
            str = "0000-00-00 00:00:00";
        } else {
            // strn.toGMTString();
            str = strn.toString();
        }
        return (str);
    }
    
    /**
     * 把BigDecimal的转换为字符串，为空将返回0.
     * 
     * @param strn
     *            BigDecimal
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final BigDecimal strn) {
        String str = "";
        
        if (strn == null) {
            str = "0";
        } else {
            str = strn.toString();
        }
        return (str);
    }
    
    /**
     * 把int的转换为字符串(不为空，只起转换作用).
     * 
     * @param strn
     *            int
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final int strn) {
        final String str = String.valueOf(strn);
        return (str);
    }
    
    /**
     * 把float的转换为字符串(不为空，只起转换作用).
     * 
     * @param strn
     *            float
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final float strn) {
        final String str = String.valueOf(strn);
        return (str);
    }
    
    /**
     * 把long的转换为字符串(不为空，只起转换作用).
     * 
     * @param strn
     *            float
     * @return String
     */
    public static String strnull(final long strn) {
        final String str = String.valueOf(strn);
        return (str);
    }
    
    /**
     * 把double的转换为字符串(不为空，只起转换作用).
     * 
     * @param strn
     *            double
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String strnull(final double strn) {
        final String str = String.valueOf(strn);
        return (str);
    }
    
    /**
     * 0-15转化为0-F.
     * 
     * @param bin
     *            int
     * @return char
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static char hex(final int bin) {
        char retval;
        if (bin >= 0 && bin <= 9) {
            retval = (char) ('0' + bin);
        } else if (bin >= 10 && bin <= 15) {
            retval = (char) ('A' + bin - 10);
        } else {
            retval = '0';
        }
        return retval;
    }
    
    /**
     * 字符串替换.
     * 
     * @param content
     *            String
     * @param oldString
     *            String
     * @param newString
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2004-1-6 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String replace(final String content, final String oldString,
        final String newString) {
        if (content == null || oldString == null) {
            return content;
        }
        if (content.equals("") || oldString.equals("")) {
            return content;
        }
        
        String resultString = "";
        int stringAtLocal = content.indexOf(oldString);
        int startLocal = 0;
        while (stringAtLocal >= 0) {
            resultString = resultString + content.substring(startLocal, stringAtLocal) + newString;
            startLocal = stringAtLocal + oldString.length();
            stringAtLocal = content.indexOf(oldString, startLocal);
        }
        
        resultString = resultString + content.substring(startLocal, content.length());
        return resultString;
    }
    
    /**
     * 替换字符串内容.
     * 
     * @param strSource
     *            源字符串
     * @param strFrom
     *            源
     * @param strTo
     *            目标
     * @return String
     * @author: 
     * @修改记录： ==============================================================<br>
     *        日期:Feb 9, 2010  创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String replaceStr(String strSource, final String strFrom, final String strTo) {
        if (strFrom == null || strFrom.equals("")) {
            return strSource;
        }
        String strDest = "";
        final int intFromLen = strFrom.length();
        int intPos;
        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
    }
    
    /**
     * @param strn
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String formatToHTML(final String strn) {
        final StringBuffer dest = new StringBuffer();
        if (StrUtil.strnull(strn).equals("")) {
            dest.append("&nbsp;");
        } else {
            for (int i = 0; strn != null && i < strn.length(); i++) {
                final char c = strn.charAt(i);
                if (c == '\n') {
                    dest.append("<br>");
                } else if (c == '\'') {
                    dest.append("&#39;");
                } else if (c == '\"') {
                    dest.append("&#34;");
                } else if (c == '<') {
                    dest.append("&lt;");
                } else if (c == '>') {
                    dest.append("&gt;");
                } else if (c == '&') {
                    dest.append("&amp;");
                } else if (c == 0x20) {
                    dest.append("&nbsp;");
                } else {
                    dest.append(c);
                }
            }
        }
        return (dest.toString());
    }
    
    /**
     * @param strn
     *            String
     * @param length
     *            int
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String formatToHTML(final String strn, final int length) {
        int m = 0;
        final StringBuffer dest = new StringBuffer();
        if (StrUtil.strnull(strn).equals("")) {
            dest.append("&nbsp;");
        } else {
            for (int i = 0; strn != null && i < strn.length(); i++) {
                m++;
                if (m == length) {
                    dest.append("...");
                    break;
                }
                final char c = strn.charAt(i);
                if (c == '\n') {
                    dest.append("<br>");
                } else if (c == '\'') {
                    dest.append("&#39;");
                } else if (c == '\"') {
                    dest.append("&#34;");
                } else if (c == '<') {
                    dest.append("&lt;");
                } else if (c == '>') {
                    dest.append("&gt;");
                } else if (c == '&') {
                    dest.append("&amp;");
                } else if (c == 0x20) {
                    dest.append("&nbsp;");
                } else {
                    dest.append(c);
                }
            }
        }
        return (dest.toString());
    }
    
    /**
     * @param strb
     *            BigDecimal
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String formatToHTML(final BigDecimal strb) {
        String strn = "";
        if (strb == null) {
            strn = "&nbsp;";
        } else {
            strn = StrUtil.strnull(strb);
        }
        return strn;
    }
    
    /**
     * 将多行字符串转为有带有回车、换行的HTML格式.
     * 
     * @param source
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String nl2Br(final String source) {
        if (StrUtil.strnull(source).equals("")) {
            return "&nbsp;";
        }
        final StringBuffer dest = new StringBuffer(source.length());
        for (int i = 0; i < source.length(); i++) {
            char c;
            c = source.charAt(i);
            if (c == '\n') {
                dest.append("<br>");
            } else if (c == 0x20) {
                dest.append("&nbsp;");
            } else {
                dest.append(c);
            }
        }
        return dest.toString();
    }
    
    /**
     * @param sourceStr
     *            String
     * @param fieldStr
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static boolean findString(final String sourceStr, final String fieldStr) {
        boolean strExist = false;
        if (sourceStr.length() == 0) {
            return strExist;
        }
        if (sourceStr.indexOf(fieldStr) >= 0) {
            strExist = true;
        }
        return strExist;
    }
    
    /**
     * @param exception
     *            Throwable
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getStackTrace(final Throwable exception) {
        final StringWriter sw = new StringWriter();
        return sw.toString();
    }
    
    /**
     * 给字符串数组排序.
     * 
     * @param arr
     *            String[] 要进行排序的字符串数组
     * @return String[] 排序后的字符串数组
     * @author: Linxf
     * @修改记录： ==============================================================<br>
     *        日期:2004-08-09 Linxf 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String[] bubbleSort(final String[] arr) {
        int tag = 1;
        for (int i = 1; i < arr.length && tag == 1; i++) {
            tag = 0;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    final String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    tag = 1;
                }
            }
        }
        return arr;
    }
    
    /**
     * 依据ValueArr数组的排序，为ContentArr排序.
     * 
     * @param valueArr
     *            String[]
     * @param contentArr
     *            String[]
     * @return String[]
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String[] bubbleSort(final String[] valueArr, final String[] contentArr) {
        int tag = 1;
        for (int i = 1; i < valueArr.length && tag == 1; i++) {
            tag = 0;
            for (int j = 0; j < valueArr.length - i; j++) {
                if (valueArr[j].compareTo(valueArr[j + 1]) > 0) {
                    final String temp1 = valueArr[j];
                    final String temp2 = contentArr[j];
                    valueArr[j] = valueArr[j + 1];
                    contentArr[j] = contentArr[j + 1];
                    valueArr[j + 1] = temp1;
                    contentArr[j + 1] = temp2;
                    tag = 1;
                }
            }
        }
        return valueArr;
    }
    
    /**
     * 冒泡排序.
     * 
     * @param arr
     *            int[] 要进行排序的数组
     * @return int[] 排序后的数组
     * @author: Linxf
     * @修改记录： ==============================================================<br>
     *        日期:2004-08-09 Linxf 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static int[] bubbleSort(final int[] arr) {
        int tag = 1;
        for (int i = 1; i < arr.length && tag == 1; i++) {
            tag = 0;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    final int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    tag = 1;
                }
            }
        }
        return arr;
    }
    
    /**
     * 将空字符串转为"0"字符串.
     * 
     * @param str
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String nullToZero(String str) {
        if (str == null || str.equals("")) {
            str = "0";
        }
        return str;
    }
    
    
    
    /**
     * 返回字段的PO名.
     * 
     * @param obName
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getPOFieldName(final String obName) {
        String aFieldName = obName;
        if (aFieldName == null) {
            return null;
        }
        aFieldName = aFieldName.toLowerCase();
        while (aFieldName.indexOf("_") >= 0) {
            if (aFieldName.indexOf("_") >= 0) {
                final int pos = aFieldName.indexOf("_");
                final String low = aFieldName.substring(0, pos);
                final String midd = aFieldName.substring(pos + 1, pos + 2).toUpperCase();
                final String end = aFieldName.substring(pos + 2, aFieldName.length());
                aFieldName = low + midd + end;
            }
        } // end while
        return aFieldName;
    }
    
    /**
     * 返回表的PO名.
     * 
     * @param obName
     *            String
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String getPOTableName(final String obName) {
        String aTableName = obName;
        if (aTableName == null) {
            return null;
        }
        aTableName = aTableName.toLowerCase();
        while (aTableName.indexOf("_") >= 0) {
            if (aTableName.indexOf("_") >= 0) {
                final int pos = aTableName.indexOf("_");
                final String low = aTableName.substring(0, pos);
                final String midd = aTableName.substring(pos + 1, pos + 2).toUpperCase();
                final String end = aTableName.substring(pos + 2, aTableName.length());
                aTableName = low + midd + end;
            }
        } // end while
        aTableName = aTableName.substring(0, 1).toUpperCase()
            + aTableName.substring(1, aTableName.length());
        return aTableName;
    }
    
    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials string
     * is returned
     * 
     * @param password
     *            Password or other credentials to use in authenticating this
     *            username
     * @param algorithm
     *            Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(final String password, final String algorithm) {
        final byte[] unencodedPassword = password.getBytes();
        
        MessageDigest md = null;
        
        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (final Exception e) {
            
            return password;
        }
        
        md.reset();
        
        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);
        
        // now calculate the hash
        final byte[] encodedPassword = md.digest();
        
        final StringBuffer buf = new StringBuffer();
        
        for (final byte element : encodedPassword) {
            if ((element & 0xff) < 0x10) {
                buf.append("0");
            }
            
            buf.append(Long.toString(element & 0xff, 16));
        }
        
        return buf.toString();
    }
    
    
    
    /**
     * 在右边填充字符串.
     * 
     * @param rString
     *            要填充的初始字符串
     * @param rLength
     *            填充后的长度
     * @param rPad
     *            填充字符
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String padTrailing(final String rString, final int rLength, final String rPad) {
        String lTmpPad = "";
        
        final String lTmpStr = StrUtil.strnull(rString);
        
        if (lTmpStr.length() >= rLength) {
            return lTmpStr.substring(0, lTmpStr.length());
        } else {
            for (int gCnt = 1; gCnt <= rLength - lTmpStr.length(); gCnt++) {
                lTmpPad = rPad + lTmpPad;
            }
        }
        return lTmpStr + lTmpPad;
    }
    
    /**
     * 在左边填充字符串.
     * 
     * @param rString
     *            要填充的初始字符串
     * @param rLength
     *            填充后的长度
     * @param rPad
     *            填充字符
     * @return String
     * @author: chenjun
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 chenjun 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static String padLeading(final String rString, final int rLength, final String rPad) {
        String lTmpPad = "";
        
        final String lTmpStr = StrUtil.strnull(rString);
        
        if (lTmpStr.length() >= rLength) {
            return lTmpStr.substring(0, lTmpStr.length());
        } else {
            for (int gCnt = 1; gCnt <= rLength - lTmpStr.length(); gCnt++) {
                lTmpPad = lTmpPad + rPad;
            }
        }
        return lTmpPad + lTmpStr;
    }
    
    /**
     * 判断数组是否存在某个字符串，并返回索引.
     * 
     * @param source
     *            String[]
     * @param subStr
     *            String
     * @return int
     * @author: panchh
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-1 panchh 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static int contains(final String[] source, final String subStr) {
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals(subStr)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 方法功能:
     * 判断一个对象或者是字符串是否为空
     * 
     * @param
     * @return
     * @param str
     * @return
     * @author: 
     * @修改记录：
     *        ==============================================================<br>
     *        日期:2010-12-17  创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static boolean isNullOrEmpty(final Object str) {
        boolean result = false;
        if (str == null || "null".equals(str) || "".equals(str.toString().trim())) {
            result = true;
        }
        return result;
    }
    
    /**
     * 方法功能:
     * 将时间的格式转换成YYYYMMDDHHMMSS .
     * 
     * @param aDate
     *            aDate
     * @return String String
     * @author: 
     * @修改记录：
     *        ==============================================================<br>
     *        日期:2011-2-26  创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public static final String dateTimeToStr(String aDate) {
        String returnValue = "";
        if (aDate != null && aDate.length() < 14) {
            StrUtil.padTrailing(aDate, 14, "0");
        }
        if(!StrUtil.isNullOrEmpty(aDate)){
            String str = aDate.replaceAll("-", "");
            String str1 = str.replaceAll(" ", "");
            String str2 = str1.replaceAll(":", "");
            returnValue = str2.substring(0, 14);
        }
        
        
        return (returnValue);
    }
    
    /**
     * getLength.
     * 
     * @param str
     *            String
     * @return
     * @author wuyx
     *         2011-3-23 wuyx
     */
    public static final int getLength(String str) {
        return str == null ? 0
            : str.length();
    }
    
    /**
     * 判断是否包括中文字符串.
     * 
     * @param str
     *            字符串
     * @return 是否中文字符串
     * @author zfz
     *         2011-4-25 zfz
     */
    public static boolean isContainChnStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fbb]+")) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 正则表达式校验公用方法，传入值 和 正则表达式，返回校验结构
     * .
     * 
     * @param value
     * @param expr
     * @return
     * @author wangzy
     *         2011-5-25 wangzy
     */
    static public boolean matchExpr(String value, String expr) {
        Pattern pattern = Pattern.compile(expr);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    
    /**
     * 将HTML编码转换成普通字符串
     * .
     * 
     * @param src String
     * @return
     * @author 
     * 2011-6-7 
     */
    public static String strConverFromHtml(String src) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("&[a-zA-Z]*;");
        Matcher m = p.matcher(src);
        int pos1 = 0;
        while (m.find(pos1)) {
            int pos2 = m.start();
            sb.append(src.substring(pos1, pos2));
            String entity = m.group().toLowerCase();
            if ("&#39;".equals(entity)) {
                sb.append("\'");
            } else if ("&#34;".equals(entity)) {
                sb.append("\"");
            } else if ("&lt;".equals(entity)) {
                sb.append("<");
            } else if ("&gt;".equals(entity)) {
                sb.append(">");
            } else if ("&nbsp;".equals(entity)) {
                sb.append(" ");
            } else if ("&amp;".equals(entity)) {
                sb.append("&");
            } else {
                sb.append("[UNKNOWN] ");
            }
            pos1 = m.end();
        }
        sb.append(src.substring(pos1));
        return sb.toString();
    }
    
    public static void main(String[] args) {
        String str = "&lt;temp&gt;测试&amp;一下是&amp;否有效&lt;/temp&gt;";
        System.out.println(strConverFromHtml(str));
    }
    
    
    /**
     * 方法功能: 获取下级的列表格式.
     * @param inXml 输入xml串
     * @param maskStartStr 起始掩码
     * @param maskEndStr 结束掩码
     * @return List 列表
     * @author: 
     * @修改记录： 
     * ==============================================================<br>
     * 日期:2011-10-8  创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static List<String> getSubXmlList(String inXml, String maskStartStr, String maskEndStr) {
        
        String tmp = inXml.replace(maskEndStr, maskStartStr);
        tmp += " ";
        String[] list = tmp.split(maskStartStr);
        List ret = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {
            if (i != 0 && i != list.length - 1 && !list[i].trim().equals("")) {
                ret.add(list[i]);
            }
        }
        return ret;
    }
    
    
    /**
     * 
     * 方法功能:
     *  去除字符串头尾的空格.
     * @param source
     * @return
     * @author: linzhiqiang
     * @修改记录： 
     * ==============================================================<br>
     * 日期:2012-2-23 linzhiqiang 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String strTrim(String source) {
        if (source == null) {
            return null;
        }
        
        String str = source.trim();
        return str;
    }
    
    /**
     * 
     * 截取字符串
     * @param oriStr
     * @param subLength
     * @return
     * @author 
     * 2014-4-28 
     */
    public static String subStr(String oriStr, int subLength) {
        if(oriStr == null || oriStr.length() == 0 || subLength == 0) {
            return "";
        }
        if(oriStr.length() < subLength) {
            return oriStr.toString();
        }
        return oriStr.substring(0, subLength);
    }

    /**
     * getCamelCaseString: 把带下划线的字符串转成驼峰型. <br/>  
     * @author samuel  
     * @param str
     * @param firstUpperCase
     * @return  
     * @since JDK 1.7
     */
    public static String getCamelCaseString(String str, boolean firstUpperCase){
        StringBuffer sb = new StringBuffer();

        str = str.toUpperCase();
        boolean nextUpperCase = firstUpperCase;
        
        for(int i=0; i<str.length(); i++){
            if( str.charAt(i)=='_' ){
                nextUpperCase = true;
                continue;
            }
            
            if( nextUpperCase ){
                sb.append(String.valueOf(str.charAt(i)).toUpperCase());
                nextUpperCase = false;
            }else{
                sb.append(String.valueOf(str.charAt(i)).toLowerCase());
            }
        }
        
        return sb.toString();
    }


    /**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    /**
     * 将下划线风格替换为驼峰风格
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static char toUpperAscii(char c) {
        if (isLowercaseAlpha(c)) {
            c -= (char) 0x20;
        }
        return c;
    }

    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) 0x20;
        }
        return c;
    }
    
    public static String join(Collection<?> collection, String separator){
        if(collection == null){
            return null;
        }
        return join(collection.iterator(), separator);
    }
    
    public static String join(Iterator<?> iterator, String separator){
        if(iterator == null){
            return null;
        }
        if(!(iterator.hasNext())){
            return "";
        }
        Object first = iterator.next();
        if(!(iterator.hasNext())){
            return ObjectUtils.toString(first);
        }
        
        StringBuffer buf = new StringBuffer(256);
        if(first != null){
            buf.append(first);
        }
        
        while(iterator.hasNext()){
            if(separator != null){
                buf.append(separator);
            }
            Object obj = iterator.next();
            if(obj != null){
                buf.append(obj);
            }
        }
        return buf.toString();
    }
 
}
