package com.xiangtai.framework.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {
	private static Logger logger = LoggerFactory.getLogger(IOUtils.class);
	
	 private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";

	 private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";

	 private static final double MAX_VALUE = 9999999999999.99D;
	 
	 //静态集合（是否正在统计利息）
	 public static Map<String, String> interestOpen = new HashMap<String, String>();
	 
	/**
	  * 方法描述：读取TXT文件中的数据,封装到List中
	  * @version: 1.0
	  * @author: www.sinoufc.com
	  * @time: 2014-2-26 下午12:08:33
	  */
	public static List<String> readLine(String filename, String charset)throws IOException{
		 List<String> lists = new ArrayList<String>();
		    File f = new File(filename);      
		    if(f.isFile()&&f.exists()) {       
		        InputStreamReader read = new InputStreamReader(new FileInputStream(f), charset);       
		        BufferedReader reader=new BufferedReader(read);       
		        String line;       
		        while ((line = reader.readLine()) != null) {        
		        	if(line != null && !line.trim().equals("")) {
			    		lists.add(line);
			    	}
		        }         
		        read.close();      
		    }     
		    return lists;   
	 }
	

	  /**
	   * 从流中读取一行文本, 读取到一行的结束为止
	   * @param in
	   * @return 一行文本
	   */
	  public static String readLine(InputStream in, String charset)
	    throws IOException{
	    byte[] buf = {};
	    int b;
	    while(true){
	      b = in.read();
	      if(b=='\n' || b=='\r' || b==-1){//编码是否是回车换行
	        break;
	      }
	      buf=Arrays.copyOf(buf, buf.length+1);
	      buf[buf.length-1]=(byte)b;
	    }
	    if(buf.length==0 && b==-1)
	      return null;
	    return new String(buf,charset);
	  }
	  
	  
	/**
	  * 方法描述：从流中写入一行文本
	  * @version: 1.0
	  * @author: www.sinoufc.com
	  * @time: 2014-3-4 下午4:39:22
	  */
	public static void writerLine(String pathFile, String outBody, String charset) 
		  throws IOException {
		    FileOutputStream fos=new FileOutputStream(pathFile);
		    OutputStreamWriter osw=new OutputStreamWriter(fos, charset);
			PrintWriter out=new PrintWriter(osw);
			out.println(outBody);
			out.close();
	  }
	  
	/**
	  * 方法描述：读取文本文件, 把每一行封装到List中
	  * @version: 1.0
	  * @author: www.sinoufc.com
	  * @time: 2014-2-19 下午1:23:58
	  */
	public static List<String> readLine(InputStream in)throws IOException{
		  List<String> lists = new ArrayList<String>();
		  ByteArrayOutputStream out=new ByteArrayOutputStream();
		    int b;
		    while(true){
		      b = in.read();
		      if(b=='\n' || b=='\r' ){   //编码是否是回车换行
		    	String s = new String(out.toByteArray());
		    	String[] strs = s.split("\\|");
		    	if(s != null && !s.trim().equals("")) {
		    		lists.add(s);
		    	}
		    	 out=new ByteArrayOutputStream();
		      } else {
		    	  out.write(b);
		      }
		      if(b==-1) {
		    	  break;
		      }
		      
		    }
		    return lists;
	 }
	
	
	  /**
	   * 读取文件的全部内容到一个byte数组
	   * 可以缓存一个"小"文件到堆内存中
	   */
	  public static byte[] read(String filename)
	    throws IOException{
	    return read(new File(filename));
	  }
	  /**
	   * 读取文件的全部内容到一个byte数组
	   * 可以缓存一个"小"文件到堆内存中
	   */
	  public static byte[] read(File file)
	    throws IOException{
	    //用RAF打开文件
	    RandomAccessFile raf = 
	      new RandomAccessFile(file, "r");
	    //安装文件的长度开辟 缓冲区数组(byte数组)
	    byte[] buf = new byte[(int)raf.length()];
	    //读取文件的缓冲区
	    raf.read(buf);
	    //关闭文件(RAF)
	    raf.close();
	    //返回缓冲区数组引用.
	    return buf;
	  }
	  /**
	   * 读取文件的全部内容到一个byte数组
	   * 可以缓存一个"小"文件到堆内存中
	   * 如: 文件内容: ABC中 读取为: {41, 42, 43, d6, d0}
	   */
	  public static byte[] read(InputStream in)
	    throws IOException{
	    byte[] ary = new byte[in.available()];
	    in.read(ary);
	    in.close();
	    return ary;
	  }
	  

	    /**
	     * 实现leftPad功能, 对字符串实现左填充
	     * @param str 被填充字符串: 5
	     * @param ch 填充字符: #
	     * @param length 填充以后的长度: 8
	     * @return "#######5"
	     */
	    public static String leftPad(String str, char ch, int length){
	      if(str.length() == length){
	        return str;
	      }
	      char[] chs = new char[length];
	      Arrays.fill(chs, ch);
	      System.arraycopy(str.toCharArray(), 0, chs, 
	          length - str.length(), str.length());
	      return new String(chs);
	    }
	    /**
	     * 实现数字转成大写
	     * @param 
	     * @param 
	     * @param 
	     * @return
	     */ 
	    public static String change(double v) {
	    	  if (v < 0 || v > MAX_VALUE)
	    	   return "参数非法!";
	    	  long l = Math.round(v * 100);
	    	  if (l == 0)
	    	   return "零元整";
	    	  String strValue = l + "";
	    	  // i用来控制数
	    	  int i = 0;
	    	  // j用来控制单位
	    	  int j = UNIT.length() - strValue.length();
	    	  String rs = "";
	    	  boolean isZero = false;
	    	  for (; i < strValue.length(); i++, j++) {
	    	   char ch = strValue.charAt(i);

	    	   if (ch == '0') {
	    	    isZero = true;
	    	    if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {
	    	     rs = rs + UNIT.charAt(j);
	    	     isZero = false;
	    	    }
	    	   } else {
	    	    if (isZero) {
	    	     rs = rs + "零";
	    	     isZero = false;
	    	    }
	    	    rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
	    	   }
	    	  }

	    	  if (!rs.endsWith("分")) {
	    	   rs = rs + "整";
	    	  }
	    	  rs = rs.replaceAll("亿万", "亿");
	    	  return rs;
	    	 }  
	    
	    /**
	     * 方法说明：860中间添加-分隔符
	     * 创建人：范兴乾
	     * 返回类型：String
	     * 创建时间：2015-8-25 下午2:37:06
	     */
	    public static String addSepaBy860(String custNo) {
	    	if(custNo == null || custNo.trim().length() < 5){
	    		return custNo;
	    	}
	    	custNo = custNo.replace("-", "");
	    	String custStr = custNo.substring(0, 3);
	    	custStr = custStr + "-";
	    	custStr = custStr + custNo.substring(3);
	    	return custStr;
	    }
	  
}
