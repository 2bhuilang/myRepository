package com.greatcattle.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.nutz.castor.Castors;

public class AppBeanUtil {

	/**
	 * 根据key路径获取Map的value
	 * 
	 * @param map
	 *            Map对象
	 * @param keys
	 *            key路径，用"/"分隔，如key1/key2/key3
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Map<String, ?> map, String keys) {
		if (map == null || map.isEmpty() || StringUtils.isBlank(keys)) {
			return null;
		}

		Object value = map;
		String[] keyArr = keys.split("/");
		for (String key : keyArr) {
			if (value instanceof Map) {
				value = ((Map<String, Object>) value).get(key);
			} else if (value instanceof List) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) value;
				if (list != null && !list.isEmpty()) {
					// 路径中有节点是数组，只能获取第一个
					value = list.get(0).get(key);
				} else {
					return null;
				}
			} else {
				return null;
			}
		}

		return (T) value;
	}

	/**
	 * 根据key路径删除Map对象的实体
	 * 
	 * @param map
	 *            Map对象
	 * @param keys
	 *            key路径，用"/"分隔，最后一个key支持多个，用"&"分隔，如key1/key2/key31&key32&key33
	 * @return 删除多个的时候只能返回最后一个value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T remove(Map<String, ?> map, String keys) {
		if (map == null || map.isEmpty() || StringUtils.isBlank(keys)) {
			return null;
		}

		Object value = map;
		String[] keyArr = keys.split("/");
		for (int i = 0; i < keyArr.length; i++) {
			String key = keyArr[i];
			if (i == keyArr.length - 1) {
				if (value instanceof Map) {
					Object lastValue = null;
					String[] _keyArr = key.split("&");
					for (String _key : _keyArr) {
						// 删除同级中多个key
						lastValue = ((Map<String, Object>) value).remove(_key);
					}

					return (T) lastValue;
				} else if (value instanceof List) {
					Object lastValue = null;
					List<Map<String, Object>> list = (List<Map<String, Object>>) value;
					if (list != null && !list.isEmpty()) {
						// 循环删除
						for (Map<String, Object> element : list) {
							String[] _keyArr = key.split("&");
							for (String _key : _keyArr) {
								// 删除同级中多个key
								lastValue = ((Map<String, Object>) element).remove(_key);
							}
						}
					}

					return (T) lastValue;
				}
			} else {
				if (value instanceof Map) {
					value = ((Map<String, Object>) value).get(key);
				} else if (value instanceof List) {
					Object lastValue = null;
					List<Map<String, Object>> list = (List<Map<String, Object>>) value;
					if (list != null && !list.isEmpty()) {
						// 重新组装key路径，循环递归删除
						String newKeys = StrUtil.join(Arrays.asList(Arrays.copyOfRange(keyArr, i, keyArr.length)),
								"/");
						for (Map<String, Object> element : list) {
							lastValue = remove(element, newKeys);
						}
					}

					return (T) lastValue;
				} else {
					break;
				}
			}
		}

		return null;
	}

	public static String getStrValue(Map<String, Object> map, String key) {
		if (map == null || map.isEmpty() || StringUtils.isBlank(key)) {
			return "";
		}
		Object t = map.get(key);
		if (t != null) {
			return t.toString();
		} else {
			for (Object o : map.keySet()) {
				String name = (String) o;
				if (name.toLowerCase().equals(key.toLowerCase())) {
					Object value =  map.get(o);
					return value.toString();
				}
			}
		}

		return "";
	}

	/**
	 *Map转化为Bean对象
	 * @param map
	 * @param c
	 * @param <T>
     * @return
     */
	public static <T> T mapToBean(Map map, Class<T> c){
		return Castors.me().castTo(map,c);
	}
	
	/**
     * 全数据库大写下划线为key的map转化为Bean（Dto）对象
     * @param map
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T queryResultMapToBean(Map<String, String> map, Class<T> c){
        Map<String,String> newone = new HashMap<String,String>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if(value != null){
                newone.put(StrUtil.getCamelCaseString(key, false), value.toString());
            }else{
                newone.put(StrUtil.getCamelCaseString(key, false), null);
            }
        }
        return Castors.me().castTo(newone,c);
    }

	
}
