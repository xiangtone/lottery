/*    */ package com.iwt.yt.util;
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
/*    */ import java.security.interfaces.RSAPublicKey;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class ServerUtil
/*    */ {
/* 22 */   public static ServerUtil serverUtil = new ServerUtil();
/*    */   public static Map<String, RSAPublicKey> publicKeyMap;
/*    */   private static final String LOTTOMAGIC_CHANNEL_COMM_PLUGIN_PROPERTIES = "lottomagic-channel-comm-plugin.properties";
/*    */   private static final String PUBLIC_KEY = "public-key";
/*    */ 
/*    */   public ServerUtil()
/*    */   {
/* 31 */     publicKeyMap = new ConcurrentHashMap();
/* 32 */     initpublicKeyFile();
/*    */   }
/*    */ 
/*    */   public static ServerUtil getInstance() {
/* 36 */     return serverUtil;
/*    */   }
/*    */ 
/*    */   public String encrypt(String channelId, String transData)
/*    */     throws RsaEncryptException
/*    */   {
/* 42 */     RSAPublicKey publicKey = (RSAPublicKey)publicKeyMap.get(channelId);
/* 43 */     if (publicKey == null) {
/* 44 */       throw new RsaEncryptException("加密时渠道商" + channelId + "的公钥不存在！");
/*    */     }
/* 46 */     return RsaHelper.encryptBase64ByPublicKey(publicKey, transData);
/*    */   }
/*    */ 
/*    */   public String decrypt(String channelId, String transData)
/*    */     throws RsaDecryptException
/*    */   {
/* 52 */     RSAPublicKey publicKey = (RSAPublicKey)publicKeyMap.get(channelId);
/* 53 */     if (publicKey == null) {
/* 54 */       throw new RsaDecryptException("解密时渠道商" + channelId + "的公钥不存在！");
/*    */     }
/* 56 */     return RsaHelper.decryptBase64ByPublicKey(publicKey, transData);
/*    */   }
/*    */ 
/*    */   public void initpublicKeyFile() {
/*    */     try {
/* 61 */       String publicKeyPath = URLDecoder.decode(ServerUtil.class.getClassLoader().getResource("lottomagic-channel-comm-plugin.properties").getPath(), "UTF-8");
/*    */ 
/* 64 */       String dirPath = publicKeyPath.substring(0, publicKeyPath.length() - "lottomagic-channel-comm-plugin.properties".length());
/*    */ 
/* 66 */       InputStream in = new BufferedInputStream(new FileInputStream(publicKeyPath));
/* 67 */       Properties priKeyProps = new Properties();
/* 68 */       priKeyProps.load(in);
/* 69 */       File publicKeyFileDirectory = new File(dirPath + priKeyProps.getProperty("public-key"));
/* 70 */       if (publicKeyFileDirectory.isDirectory()) {
/* 71 */         File[] publicKeyFileArray = publicKeyFileDirectory.listFiles();
/* 72 */         for (File publicKeyFile : publicKeyFileArray) {
/* 73 */           String channelId = publicKeyFile.getName();
/* 74 */           channelId = channelId.substring(7, channelId.length() - 4);
/* 75 */           ObjectInputStream publicKeyInput = new ObjectInputStream(new FileInputStream(publicKeyFile));
/* 76 */           RSAPublicKey publicKey = (RSAPublicKey)publicKeyInput.readObject();
/* 77 */           publicKeyMap.put(channelId, publicKey);
/* 78 */           publicKeyInput.close();
/*    */         }
/*    */       } else {
/* 81 */         String channelId = priKeyProps.getProperty("public-key");
/* 82 */         channelId = channelId.substring(7, channelId.length() - 4);
/* 83 */         ObjectInputStream publicKeyInput = new ObjectInputStream(new FileInputStream(publicKeyFileDirectory));
/* 84 */         RSAPublicKey publicKey = (RSAPublicKey)publicKeyInput.readObject();
/* 85 */         publicKeyMap.put(channelId, publicKey);
/* 86 */         publicKeyInput.close();
/*    */       }
/*    */     } catch (Exception e) {
/* 89 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.util.ServerUtil
 * JD-Core Version:    0.6.2
 */