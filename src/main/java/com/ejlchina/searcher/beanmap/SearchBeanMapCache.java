package com.ejlchina.searcher.beanmap;

import java.util.HashMap;
import java.util.Map;

import com.ejlchina.searcher.SearchResultConvertInfo;


/**
 * 用于缓存 @SearchBeanMap
 * 
 * @author Troy.Zhou @ 2017-03-20
 *
 */
public class SearchBeanMapCache {

	private static SearchBeanMapCache instance = new SearchBeanMapCache();
	
	private Map<String, SearchBeanMap> cache = new HashMap<>();
	
	
	public static SearchBeanMapCache sharedCache() {
		return instance;
	}
	
	
	public <T> void addSearchBeanMap(Class<T> beanClass, SearchBeanMap searchBeanMap) {
		SearchResultConvertInfo<T> convertInfo = new SearchResultConvertInfo<T>(beanClass);
		convertInfo.setFieldDbAliasEntrySet(searchBeanMap.getFieldDbAliasMap().entrySet());
		convertInfo.setFieldGetMethodMap(searchBeanMap.getFieldGetMethodMap());
		convertInfo.setFieldTypeMap(searchBeanMap.getFieldTypeMap());
		searchBeanMap.setConvertInfo(convertInfo);
		cache.put(beanClass.getName(), searchBeanMap);
	}
	
	
	public SearchBeanMap getSearchBeanMap(Class<?> beanClass) {
		return cache.get(beanClass.getName());
	}
	
	
	public void clear() {
		cache.clear();
	}
	
	
}
