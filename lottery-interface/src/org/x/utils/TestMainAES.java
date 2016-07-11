package org.x.utils;

import com.alibaba.fastjson.JSON;

public class TestMainAES {
	
	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
		String info = "{\"body\":\"woYyq7GFaPUkyktchQM1pBe64Hbabp6Q4EhZW9jrOAx0RNw9VmiOYfPSPgN9kHXwiJZGnnYOL8fn/5PqHhqAvwclC16RGKtQs2AZszDGjJEueaCIDIJshOlL8Dgf2688ciKgi+HKI3r9AjKkemnZySDYaCuqdY83nPe0mKn55j91P2wDVA53mzz19NgnjqSapCm0fSwVfprXM2bOBIP+ePlkDEVmg7tSIm5taURwybfdFHgVmQNi2zVnHLDhphRgW22WU4f6UvtQT8tsjyt3lFrZlAC8D+he5BiKs18eQnZWgelv7LvHW3aAGE+AiaumqXkZkI6v9M3skDSQjZBu9VUUVFvqYnpZFbPAbiyqAURbct/cvuduEFjDwd3UIGrwu1Auisn0uQTV2izwPl+nji9+ACVuoYard68tVARtjD5a2ZQAvA/oXuQYirNfHkJ2emGLmhsAG0zMNNKC/tLdow==\",\"head\":{\"businessId\":\"2011\",\"channelId\":\"C12001\",\"signature\":\"200213abf4691fe3c9a15376d15b3cd0\",\"transSerialNumber\":\"4028d0b55599ca380155beebcf9a08da\"}}";
		
		
		Bean bean = JSON.parseObject(info,Bean.class);
		
		System.out.println(bean.getBody());
		
		   // 
        String DeString = AES.Decrypt(bean.getBody(), "5118a4a8015118a4");
        System.out.println("" + DeString);
		
        System.out.println(bean.getHead().getBusinessId());
        System.out.println(bean.getHead().getChannelId());
        System.out.println(bean.getHead().getSignature());
        System.out.println(bean.getHead().getTransSerialNumber());
        
        
        
        
		
	}
	
	
	public static class Bean{
		private String body;
		private HeadBean head;
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public HeadBean getHead() {
			return head;
		}
		public void setHead(HeadBean head) {
			this.head = head;
		}
		
		
		
		
	}
	
	public static class HeadBean{
		private String businessId;
		private String channelId;
		private String signature;
		private String transSerialNumber;
		public String getBusinessId() {
			return businessId;
		}
		public void setBusinessId(String businessId) {
			this.businessId = businessId;
		}
		public String getChannelId() {
			return channelId;
		}
		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getTransSerialNumber() {
			return transSerialNumber;
		}
		public void setTransSerialNumber(String transSerialNumber) {
			this.transSerialNumber = transSerialNumber;
		}
		
		
		
		
		
		
		
	}
	

}
