package chooeat.activity.utils;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class GsonUtils {
	private static final Gson GSON = new Gson();

	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		System.out.println(json);
		return GSON.fromJson(json, classOfT);
	}

	// 從req取得json後轉為VO
	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
