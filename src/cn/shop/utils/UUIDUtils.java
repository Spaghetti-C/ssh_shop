package cn.shop.utils;

import java.util.UUID;

/**
 * ��������ַ����Ĺ�����
 * @author Administrator
 *
 */
public class UUIDUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
