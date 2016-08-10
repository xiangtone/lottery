/*    */ package com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util;
/*    */ 
/*    */ import com.iwt.vasoss.common.security.RsaHelper;
/*    */ import com.iwt.vasoss.common.security.exception.RsaDecryptException;
/*    */ import com.iwt.vasoss.common.security.exception.RsaEncryptException;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLDecoder;
/*    */ import java.security.interfaces.RSAPrivateKey;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class ClientUtil
/*    */ {
/* 22 */   private static ClientUtil clientUtil = new ClientUtil();
/*    */   private Map<String, String> paramMap;
/*    */   private Map<String, RSAPrivateKey> privateKeyMap;
/*    */   private static final String LOTTOMAGIC_CHANNEL_COMM_PLUGIN_PROPERTIES = "lottomagic-channel-comm-plugin.properties";
/*    */   private static final String CHANNEL_ID = "channel-id";
/*    */   private static final String PRIVATE_KEY = "private-key";
/*    */   private static final String POINT_EXCHANGE_LOTTERY_URL = "point-exchange-lottery-url";
/*    */   private static final String QUERY_MODIFY_BET_ACCOUNT_INFO_URL = "query-modify-bet-account-info-url";
/*    */ 
/*    */   private ClientUtil()
/*    */   {
/* 35 */     this.paramMap = new ConcurrentHashMap();
/* 36 */     this.privateKeyMap = new ConcurrentHashMap();
/* 37 */     initPrivateKeyFile();
/*    */   }
/*    */ 
/*    */   public static ClientUtil getInstance() {
/* 41 */     return clientUtil;
/*    */   }
/*    */ 
/*    */   public String getChannelId() {
/* 45 */     return (String)this.paramMap.get("channel-id");
/*    */   }
/*    */ 
/*    */   public String getPointExchangeLotteryUrl() {
/* 49 */     return (String)this.paramMap.get("point-exchange-lottery-url");
/*    */   }
/*    */ 
/*    */   public String getQueryModifyBetAccountInfoUrl() {
/* 53 */     return (String)this.paramMap.get("query-modify-bet-account-info-url");
/*    */   }
/*    */ 
/*    */   public String encrypt(String channelId, String transData)
/*    */     throws RsaEncryptException
/*    */   {
/* 59 */     RSAPrivateKey privateKey = (RSAPrivateKey)this.privateKeyMap.get(channelId);
/* 60 */     if (privateKey == null) {
/* 61 */       throw new RsaEncryptException("加密时渠道商" + channelId + "的私钥不存在！");
/*    */     }
/* 63 */     return RsaHelper.encryptBase64ByPrivateKey(privateKey, transData);
/*    */   }
/*    */ 
/*    */   public String decrypt(String channelId, String transData)
/*    */     throws RsaDecryptException
/*    */   {
/* 69 */     RSAPrivateKey privateKey = (RSAPrivateKey)this.privateKeyMap.get(channelId);
/* 70 */     if (privateKey == null) {
/* 71 */       throw new RsaDecryptException("解密时渠道商" + channelId + "的私钥不存在！");
/*    */     }
/* 73 */     return RsaHelper.decryptBase64ByPrivateKey(privateKey, transData);
/*    */   }
/*    */ 
/*    */   private void initPrivateKeyFile() {
/*    */     try {
/* 78 */       String privateKeyPath = URLDecoder.decode(ClientUtil.class.getClassLoader().getResource("lottomagic-channel-comm-plugin.properties").getPath(), "UTF-8");
/*    */ 
/* 81 */       String dirPath = privateKeyPath.substring(0, privateKeyPath.length() - "lottomagic-channel-comm-plugin.properties".length());
/*    */ 
/* 83 */       InputStream in = new BufferedInputStream(new FileInputStream(privateKeyPath));
/* 84 */       Properties priKeyProps = new Properties();
/* 85 */       priKeyProps.load(in);
/* 86 */       this.paramMap.put("channel-id", priKeyProps.getProperty("channel-id"));
/* 87 */       this.paramMap.put("private-key", priKeyProps.getProperty("private-key"));
/* 88 */       this.paramMap.put("point-exchange-lottery-url", priKeyProps.getProperty("point-exchange-lottery-url"));
/* 89 */       this.paramMap.put("query-modify-bet-account-info-url", priKeyProps.getProperty("query-modify-bet-account-info-url"));
/* 90 */       File privateKeyFile = new File(dirPath + (String)this.paramMap.get("private-key"));
/* 91 */       ObjectInputStream privateKeyInput = new ObjectInputStream(new FileInputStream(privateKeyFile));
/* 92 */       RSAPrivateKey privateKey = (RSAPrivateKey)privateKeyInput.readObject();
/* 93 */       this.privateKeyMap.put(getChannelId(), privateKey);
/* 94 */       privateKeyInput.close();
/*    */     } catch (Exception e) {
/* 96 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util.ClientUtil
 * JD-Core Version:    0.6.2
 */