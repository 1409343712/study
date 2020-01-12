package com.scream.study.java8.base64;

import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;

public class Base64for18 {
	/**
	 * 使⽤JDK⾥sun.misc套件下的BASE64Encoder和BASE64Decoder这两个类
	 * 缺点：编码和解码的效率⽐较差，公开信息说以后的版本会取消这个⽅法
	 * @throws Exception
	 */
	@Test
	public void testOld() throws Exception{
		BASE64Encoder encoder = new BASE64Encoder();
		BASE64Decoder decoder = new BASE64Decoder();
		String text = "这是老方法";
		byte[] textByte = text.getBytes("UTF-8");
		//编码
		String encodedText = encoder.encode(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(decoder.decodeBuffer(encodedText),"UTF-8"));
	}

	/**
	 * Jdk1.8的java.util包中，新增了Base64的类
	 * 好处：不⽤引包，编解码销量远⼤于 sun.misc 和 Apache Commons Codec
	 * @throws Exception
	 */
	@Test
	public void testNew() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		Base64.Encoder encoder = Base64.getEncoder();
		String text = "虽然Lambada表达式是对函数式接口的简化写法，但是我就想传递一个正常的参数，不行么？";
		byte[] textByte = text.getBytes("UTF-8");
		//编码
		String encodedText = encoder.encodeToString(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	}
}
