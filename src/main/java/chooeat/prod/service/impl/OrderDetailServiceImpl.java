package chooeat.prod.service.impl;

import java.security.SecureRandom;
import java.util.List;

import chooeat.prod.model.vo.OrderDetail;

public class OrderDetailServiceImpl {
	
	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int CODE_LENGTH = 16;
	private static final SecureRandom random = new SecureRandom();
	
	public List<OrderDetail> selectAll() {
		return null;
	}

	public static void main(String[] args) {
		String randomCode = generateRandomCode();
		System.out.println("Random Code: " + randomCode);
	}

	public static String generateRandomCode() {
		StringBuilder sb = new StringBuilder(CODE_LENGTH);

		for (int i = 0; i < CODE_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}

}
