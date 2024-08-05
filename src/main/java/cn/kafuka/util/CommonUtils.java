package cn.kafuka.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName CommonUtils
 * @Description 常见的辅助类
 */
@Slf4j
public class CommonUtils {


	private static String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	private static String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万",
			"拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };

	/**
	 * 判断对象是否Empty(null或元素为0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj 待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isEmpty(Object pObj) {
		if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	public static Object nvl(Object pObj, Object rObj) {
		if (isEmpty(pObj)) {
			return rObj;
		} else {
			return pObj;
		}
	}

	/**
	 * 判断对象是否为NotEmpty(!null或元素>0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj 待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null)
			return false;
		if (pObj == "")
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断一个字符串是否由数字、字母、数字字母组成
	 * 
	 * @param pStr   需要判断的字符串
	 * @param pStyle 判断规则
	 * @return boolean 返回的布尔值
	 */
	public static boolean isTheStyle(String pStr, String pStyle) {
		for (int i = 0; i < pStr.length(); i++) {
			char c = pStr.charAt(i);
			if (pStyle.equals("number")) {
				if (!Character.isDigit(c))
					return false;
			} else if (pStyle.equals("letter")) {
				if (!Character.isLetter(c))
					return false;
			} else if (pStyle.equals("numberletter")) {
				if (Character.isLetterOrDigit(c))
					return false;
			}
		}
		return true;
	}

	/**
	 * JavaBean之间对象属性值拷贝
	 * 
	 * @param pFromObj Bean源对象
	 * @param pToObj   Bean目标对象
	 */
	public static void copyPropBetweenBeans(Object pFromObj, Object pToObj) {
		if (pToObj != null) {
			try {
				BeanUtils.copyProperties(pToObj, pFromObj);
			} catch (Exception e) {
				log.error("==开发人员请注意:==\n JavaBean之间的属性值拷贝发生错误啦!" + "\n详细错误信息如下:");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将传入的身份证号码进行校验，并返回一个对应的18位身份证
	 * 
	 * @param personIDCode 身份证号码
	 * @return String 十八位身份证号码
	 * @throws
	 */
	public static String getFixedPersonIDCode(String personIDCode) throws Exception {
		if (personIDCode == null)
			throw new Exception("输入的身份证号无效，请检查");
		if (personIDCode.length() == 18) {
			if (isIdentity(personIDCode))
				return personIDCode;
			else
				throw new Exception("输入的身份证号无效，请检查");
		} else if (personIDCode.length() == 15)
			return fixPersonIDCodeWithCheck(personIDCode);
		else
			throw new Exception("输入的身份证号无效，请检查");
	}

	/**
	 * 修补15位居民身份证号码为18位，并校验15位身份证有效性
	 * 
	 * @param personIDCode 十五位身份证号码
	 * @return String 十八位身份证号码
	 * @throws
	 */
	public static String fixPersonIDCodeWithCheck(String personIDCode) throws Exception {
		if (personIDCode == null || personIDCode.trim().length() != 15)
			throw new Exception("输入的身份证号不足15位，请检查");
		if (!isIdentity(personIDCode))
			throw new Exception("输入的身份证号无效，请检查");

		return fixPersonIDCodeWithoutCheck(personIDCode);
	}

	/**
	 * 修补15位居民身份证号码为18位，不校验身份证有效性
	 * 
	 * @param personIDCode 十五位身份证号码
	 * @return 十八位身份证号码
	 * @throws
	 */
	public static String fixPersonIDCodeWithoutCheck(String personIDCode) throws Exception {
		if (personIDCode == null || personIDCode.trim().length() != 15)
			throw new Exception("输入的身份证号不足15位，请检查");

		String id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15); // 15位身份证补'19'

		char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 11个校验码字符
		int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 }; // 18个加权因子
		int[] idcd = new int[18];
		int sum; // 根据公式 ∑(ai×Wi) 计算
		int remainder; // 第18位校验码
		for (int i = 0; i < 17; i++) {
			idcd[i] = Integer.parseInt(id17.substring(i, i + 1));
		}
		sum = 0;
		for (int i = 0; i < 17; i++) {
			sum = sum + idcd[i] * factor[i];
		}
		remainder = sum % 11;
		String lastCheckBit = String.valueOf(code[remainder]);
		return id17 + lastCheckBit;
	}

	/**
	 * 判断是否是有效的18位或15位居民身份证号码
	 * 
	 * @param identity 18位或15位居民身份证号码
	 * @return 是否为有效的身份证号码
	 */
	public static boolean isIdentity(String identity) {
		if (identity == null)
			return false;
		if (identity.length() == 18 || identity.length() == 15) {
			String id15 = null;
			if (identity.length() == 18)
				id15 = identity.substring(0, 6) + identity.substring(8, 17);
			else
				id15 = identity;
			try {
				Long.parseLong(id15); // 校验是否为数字字符串

				String birthday = "19" + id15.substring(6, 12);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sdf.parse(birthday); // 校验出生日期
				if (identity.length() == 18 && !fixPersonIDCodeWithoutCheck(id15).equals(identity))
					return false; // 校验18位身份证
			} catch (Exception e) {
				return false;
			}
			return true;
		} else
			return false;
	}

	/**
	 * 从身份证号中获取出生日期，身份证号可以为15位或18位
	 * 
	 * @param identity 身份证号
	 * @return 出生日期
	 * @throws
	 */
	public static Date getBirthdayFromPersonIDCode(String identity) throws Exception {
		String id = getFixedPersonIDCode(identity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date birthday = new Date(sdf.parse(id.substring(6, 14)).getTime());
			return birthday;
		} catch (ParseException e) {
			throw new Exception("不是有效的身份证号，请检查");
		}
	}

	/**
	 * 从身份证号获取性别
	 * 
	 * @param identity 身份证号
	 * @return 性别代码
	 * @throws Exception 无效的身份证号码
	 */
	public static String getGenderFromPersonIDCode(String identity) throws Exception {
		String id = getFixedPersonIDCode(identity);
		char sex = id.charAt(16);
		return sex % 2 == 0 ? "2" : "1";
	}

	/**
	 * 将货币转换为大写形式(类内部调用)
	 * 
	 * @param
	 * @return String
	 */
	private static String PositiveIntegerToHanStr(String NumStr) {
		// 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
		String RMBStr = "";
		boolean lastzero = false;
		boolean hasvalue = false; // 亿、万进位前有数值标记
		int len, n;
		len = NumStr.length();
		if (len > 15)
			return "数值过大!";
		for (int i = len - 1; i >= 0; i--) {
			if (NumStr.charAt(len - i - 1) == ' ')
				continue;
			n = NumStr.charAt(len - i - 1) - '0';
			if (n < 0 || n > 9)
				return "输入含非数字字符!";

			if (n != 0) {
				if (lastzero)
					RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
				// 除了亿万前的零不带到后面
				// if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )
				// 如十进位前有零也不发壹音用此行
				if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
					RMBStr += HanDigiStr[n];
				RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
				hasvalue = true; // 置万进位前有值标记

			} else {
				if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
					RMBStr += HanDiviStr[i]; // “亿”或“万”
			}
			if (i % 8 == 0)
				hasvalue = false; // 万进位前有值标记逢亿复位
			lastzero = (n == 0) && (i % 4 != 0);
		}

		if (RMBStr.length() == 0)
			return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
		return RMBStr;
	}

	/**
	 * 将货币转换为大写形式
	 * 
	 * @param val 传入的数据
	 * @return String 返回的人民币大写形式字符串
	 */
	public static String numToRMBStr(double val) {
		String SignStr = "";
		String TailStr = "";
		long fraction, integer;
		int jiao, fen;

		if (val < 0) {
			val = -val;
			SignStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999)
			return "数值位数过大!";
		// 四舍五入到分
		long temp = Math.round(val * 100);
		integer = temp / 100;
		fraction = temp % 100;
		jiao = (int) fraction / 10;
		fen = (int) fraction % 10;
		if (jiao == 0 && fen == 0) {
			TailStr = "整";
		} else {
			TailStr = HanDigiStr[jiao];
			if (jiao != 0)
				TailStr += "角";
			// 零元后不写零几分
			if (integer == 0 && jiao == 0)
				TailStr = "";
			if (fen != 0)
				TailStr += HanDigiStr[fen] + "分";
		}
		// 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
		// if( !integer ) return SignStr+TailStr;
		return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
	}

	/**
	 * 格式化金额
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String formatMoney(String s, int len) {
		if (s == null || s.length() < 1) {
			return "";
		}
		java.text.NumberFormat formater = null;
		double num = Double.parseDouble(s);
		if (len == 0) {
			formater = new DecimalFormat("###,###");

		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		if (result.indexOf(".") == -1) {
			result = result + ".00";
		} else {
			result = result;
		}
		return result;
	}

	/**
	 * 合并字符串数组
	 * 
	 * @param a 字符串数组0
	 * @param b 字符串数组1
	 * @return 返回合并后的字符串数组
	 */
	public static String[] mergeStringArray(String[] a, String[] b) {
		if (a.length == 0 || isEmpty(a))
			return b;
		if (b.length == 0 || isEmpty(b))
			return a;
		String[] c = new String[a.length + b.length];
		for (int m = 0; m < a.length; m++) {
			c[m] = a[m];
		}
		for (int i = 0; i < b.length; i++) {
			c[a.length + i] = b[i];
		}
		return c;
	}

	/**
	 * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性
	 */
	public static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName) {
		String agent = request.getHeader("USER-AGENT");
		try {
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				pFileName = URLEncoder.encode(pFileName, "utf-8");
			} else {
				pFileName = new String(pFileName.getBytes("utf-8"), "iso8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return pFileName;
	}

	/**
	 * 判断是否是IE浏览器
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isIe = true;
		int index = userAgent.indexOf("msie");
		if (index == -1) {
			isIe = false;
		}
		return isIe;
	}

	/**
	 * 判断是否是Chrome浏览器
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isChrome(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isChrome = true;
		int index = userAgent.indexOf("chrome");
		if (index == -1) {
			isChrome = false;
		}
		return isChrome;
	}

	/**
	 * 判断是否是Firefox浏览器
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isFirefox(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isFirefox = true;
		int index = userAgent.indexOf("firefox");
		if (index == -1) {
			isFirefox = false;
		}
		return isFirefox;
	}

	/**
	 * 获取客户端类型
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String getClientExplorerType(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		String explorer = "非主流浏览器";
		if (isIE(request)) {
			int index = userAgent.indexOf("msie");
			explorer = userAgent.substring(index, index + 8);
		} else if (isChrome(request)) {
			int index = userAgent.indexOf("chrome");
			explorer = userAgent.substring(index, index + 12);
		} else if (isFirefox(request)) {
			int index = userAgent.indexOf("firefox");
			explorer = userAgent.substring(index, index + 11);
		}
		return explorer.toUpperCase();
	}

	/**
	 * 判断缺省数据源的JDBC类型：Oracle
	 * 
	 * @return
	 */
	public static boolean defaultJdbcTypeOracle() {
		boolean out = false;
		String jdbcType = System.getProperty("g4.JdbcType");
		if (jdbcType.equalsIgnoreCase("oracle")) {
			out = true;
		}
		return out;
	}

	/**
	 * 判断缺省数据源的JDBC类型：Mysql
	 * 
	 * @return
	 */
	public static boolean defaultJdbcTypeMysql() {
		boolean out = false;
		String jdbcType = System.getProperty("g4.JdbcType");
		if (jdbcType.equalsIgnoreCase("mysql")) {
			out = true;
		}
		return out;
	}

	/**
	 * JS输出含有\n的特殊处理
	 * 
	 * @param pStr
	 * @return
	 */
	public static String replace4JsOutput(String pStr) {
		pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
		pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		pStr = pStr.replace(" ", "&nbsp;");
		return pStr;
	}

	/**
	 * 获取class文件所在绝对路径
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 */
	public static String getPathFromClass(Class cls) {
		String path = null;
		if (cls == null) {
			throw new NullPointerException();
		}
		URL url = getClassLocationURL(cls);
		if (url != null) {
			path = url.getPath();
			if ("jar".equalsIgnoreCase(url.getProtocol())) {
				try {
					path = new URL(path).getPath();
				} catch (MalformedURLException e) {
				}
				int location = path.indexOf("!/");
				if (location != -1) {
					path = path.substring(0, location);
				}
			}
			File file = new File(path);
			try {
				path = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 这个方法可以通过与某个类的class文件的相对路径来获取文件或目录的绝对路径。 通常在程序中很难定位某个相对路径，特别是在B/S应用中。
	 * 通过这个方法，我们可以根据我们程序自身的类文件的位置来定位某个相对路径。
	 * 比如：某个txt文件相对于程序的Test类文件的路径是../../resource/test.txt，
	 * 那么使用本方法Path.getFullPathRelateClass("../../resource/test.txt",Test.class)
	 * 得到的结果是txt文件的在系统中的绝对路径。
	 * 
	 * @param relatedPath 相对路径
	 * @param cls         用来定位的类
	 * @return 相对路径所对应的绝对路径
	 * @throws IOException 因为本方法将查询文件系统，所以可能抛出IO异常
	 */
	public static String getFullPathRelateClass(String relatedPath, Class cls) {
		String path = null;
		if (relatedPath == null) {
			throw new NullPointerException();
		}
		String clsPath = getPathFromClass(cls);
		File clsFile = new File(clsPath);
		String tempPath = clsFile.getParent() + File.separator + relatedPath;
		File file = new File(tempPath);
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 获取类的class文件位置的URL
	 * 
	 * @param cls
	 * @return
	 */
	private static URL getClassLocationURL(final Class cls) {
		if (cls == null)
			throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			if (cs != null)
				result = cs.getLocation();
			if (result != null) {
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip"))
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						else if (new File(result.getFile()).isDirectory())
							result = new URL(result, clsAsResource);
					} catch (MalformedURLException ignore) {
					}
				}
			}
		}
		if (result == null) {
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource)
					: ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}

	/**
	 * 获取start到end区间的随机数,不包含start+end
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static BigDecimal getRandom(int start, int end) {
		return new BigDecimal(start + Math.random() * end);
	}

	/**
	 * @Title: getRandomCode
	 * @Description: 生成指定位数的随机码
	 * @param num
	 * @Return String
	 */
	public static String getRandomCode(int num) {
		StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
		buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
		// buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
		buf.append(",1,2,3,4,5,6,7,8,9,0");
		String[] arr = buf.toString().split(",");
		StringBuffer b = new StringBuffer();
		Random r;
		int k;
		for (int i = 0; i < num; i++) {
			r = new Random();
			k = r.nextInt();
			b.append(String.valueOf(arr[Math.abs(k % 61)]));
		}
		return b.toString();
	}

	/**
	 * TODO:格式化双精度数
	 * 
	 * @param targetNum(双精度数)
	 * @param decimalDigits(保留小数位数)
	 * @return: 格式化后的双精度数
	 * @date_created:2013-06-14 下午04:56:51
	 * @creator:zhangxu
	 * @transfer:
	 */
	public static double formatDouble(double targetNum, int decimalDigits) {
		return Math.round(targetNum * Math.pow(10, decimalDigits)) / Math.pow(10, decimalDigits);
	}

	/**
	 * @Title: isContainStr
	 * @Description: 判断字符串数组中是否包含某个特定字符串
	 * @param arrayStr
	 * @param str
	 * @Return boolean
	 */
	public static boolean isContainStr(String[] arrayStr, String str) {
		List list = Arrays.asList(arrayStr);
		if (list.contains(str)) {
			return true;
		} else {
			return false;
		}
	}

	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	/**
	 * @Title: checkDate
	 * @Description: 判断时间是否正确格式
	 * @param date
	 * @param format
	 * @Return boolean
	 */
	public static boolean checkDate(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			// 如果不能转换,肯定是错误格式
			return false;
		}
		String s1 = df.format(d);
		// 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
		// "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
		// 逻辑上不对.
		return date.equals(s1);
	}

	/**
	 * @Title: checkEmail
	 * @Description: 判断邮件email是否正确格式
	 * @param email
	 * @Return boolean
	 */
	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: checkPhone
	 * @Description: 判断手机号phone是否正确格式
	 * @param phone
	 * @Return boolean
	 */
	public static boolean checkPhone(String phone) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/** 是否为数字 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static String formatCapacity(long capacity) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.FLOOR);
		if (capacity < 1024) {
			return capacity + "B";
		} else if (capacity >= 1024 && capacity < 1024 * 1024) {
			return df.format(capacity / 1024.0) + "KB";
		} else if (capacity >= 1024 * 1024 && capacity < 1024 * 1024 * 1024) {
			return df.format(capacity / (1024.0 * 1024.0)) + "MB";
		} else if (capacity >= 1024 * 1024 * 1024l && capacity < 1024 * 1024 * 1024 * 1024l) {
			return df.format(capacity / (1024.0 * 1024.0 * 1024.0)) + "GB";
		} else {
			return df.format(capacity / (1024.0 * 1024.0 * 1024.0 * 1024)) + "TB";
		}
	}


	public static String getISO8601TimestampStr(Date date){
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(tz);
		String nowAsISO = df.format(date);
		return nowAsISO;
	}

/*	public static void main(String args[]) {
		
	}*/

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 *
	 * @param cell Excel单元格
	 * @return String 单元格数据内容
	 */
	@SuppressWarnings("deprecation")
//	public static String getStringCellValue(XSSFCell cell) {
//		if (cell == null) {
//			return "";
//		}
//		String strCell = "";
//		switch (cell.getCellType()) {
//		case XSSFCell.CELL_TYPE_STRING:
//			strCell = cell.getStringCellValue().trim();
//			break;
//		case XSSFCell.CELL_TYPE_NUMERIC:
//			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
//				// 如果是date类型则 ，获取该cell的date值
//				strCell = new SimpleDateFormat("yyyy-MM-dd")
//						.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()));
//			} else { // 纯数字
//				if(String.valueOf(cell.getNumericCellValue()).indexOf("E")==-1){
//			        String val = String.valueOf(cell.getNumericCellValue());
//			        String[] str = val.split("\\.");
//			        if (str.length > 1) {
//			            String str1 = str[1];
//			            int m = Integer.parseInt(str1);
//			            if (m == 0) {
//			                return str[0];
//			            } else {
//			                return val;
//			            }
//			        }else {
//			        	return val;
//			        }
//			    }else {
//			        return new DecimalFormat("#").format(cell.getNumericCellValue());
//			    }
//			}
//			break;
//		case XSSFCell.CELL_TYPE_BOOLEAN:
//			strCell = String.valueOf(cell.getBooleanCellValue());
//			break;
//		case XSSFCell.CELL_TYPE_BLANK:
//			strCell = "";
//			break;
//		default:
//			strCell = "";
//			break;
//		}
//		if (strCell.equals("") || strCell == null) {
//			return "";
//		}
//
//		return strCell;
//	}

	public static <T> T getFirstData(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	public static <T> T getLastData(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}
		return list.get(list.size() - 1);
	}
}