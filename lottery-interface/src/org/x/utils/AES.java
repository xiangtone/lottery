package org.x.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Administrator
 *
 */
public class AES {

    // ����
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("KeyΪ��null");
            return null;
        }
        // �ж�Key�Ƿ�Ϊ16λ
        if (sKey.length() != 16) {
            System.out.print("Key���Ȳ���16λ");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
    }

    // ����
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
                System.out.print("KeyΪ��null");
                return null;
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
                System.out.print("Key���Ȳ���16λ");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * �˴�ʹ��AES-128-ECB����ģʽ��key��ҪΪ16λ��
         */
        String cKey = "1234567890123456";
        // ��Ҫ���ܵ��ִ�
        String cSrc = "www.gowhere.so";
        System.out.println(cSrc);
        // ����
        String enString = AES.Encrypt(cSrc, cKey);
        System.out.println("���ܺ���ִ��ǣ�" + enString);

        // ����
        String DeString = AES.Decrypt(enString, cKey);
        System.out.println("���ܺ���ִ��ǣ�" + DeString);
    }
}

//Դ����Ƭ�������ƴ���http://yuncode.net
			