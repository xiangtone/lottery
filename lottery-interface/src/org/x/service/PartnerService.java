package org.x.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.x.info.PartnerInfo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class PartnerService {

	private static LoadingCache<String, PartnerInfo> cache;
	private static PartnerService instance = new PartnerService();

	static {
		cache = CacheBuilder.newBuilder().maximumSize(2000)// 设置大小，条目数
				.expireAfterWrite(10, TimeUnit.SECONDS)// 设置失效时间，创建时间
				.expireAfterAccess(20, TimeUnit.HOURS) // 设置时效时间，最后一次被访问
				.removalListener(new RemovalListener<String, PartnerInfo>() { // 移除缓存的监听器
					public void onRemoval(RemovalNotification<String, PartnerInfo> notification) {
					}
				}).build(new CacheLoader<String, PartnerInfo>() { // 通过回调加载缓存
					@Override
					public PartnerInfo load(String name) throws Exception {
						DaoService daoService = new DaoService();
						return daoService.getTargetUrlByTitle(name);
					}
				});
	}

	public static PartnerService getInstance() {
		return instance;
	}

	@SuppressWarnings("finally")
	public PartnerInfo getNameLoadingCache(String name) {
		PartnerInfo partnerInfo = null;
		try {
			partnerInfo = cache.get(name);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			partnerInfo = null;
		} finally {
			return partnerInfo;
		}
		// cache.invalidateAll();
	}
}
