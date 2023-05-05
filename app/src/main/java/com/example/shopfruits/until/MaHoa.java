package com.example.shopfruits.until;
import java.security.MessageDigest;
import android.util.Base64;






public class MaHoa {
	// md5
	// sha-1 => thường được sử dụng
	public static String toSHA1(String str) {
		String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";
		String result = null;
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] hash = md.digest(dataBytes);
			result = Base64.encodeToString(hash, Base64.DEFAULT)
					.replaceAll("\\n", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



}