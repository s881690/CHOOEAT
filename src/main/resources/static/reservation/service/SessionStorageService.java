package chooeat.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class SessionStorageService {
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    private Gson gson = new Gson();

    public String getAccIdFromSessionStorage(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        String loginReqValue = session.getAttribute("loginReq");
        if (loginReqValue != null) {
            // 使用Gson库解析JSON字符串
            LoginReqData loginReqData = gson.fromJson(loginReqValue, LoginReqData.class);
            return String.valueOf(loginReqData.getAcc_id());
        }
        return null;
    }

    public String getRestaurantIdFromSessionStorage(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        String searchResultValue = session.getAttribute("searchResult");
        if (searchResultValue != null) {
            // 使用Gson库解析JSON字符串
            SearchResultData searchResultData = gson.fromJson(searchResultValue, SearchResultData.class);
            if (searchResultData.getMyself().size() > 0) {
                return String.valueOf(searchResultData.getMyself().get(0).getRestaurantId());
            }
        }
        return null;
    }

    // 定义登录请求数据模型
    private static class LoginReqData {
        private int acc_id;

        public int getAcc_id() {
            return acc_id;
        }
    }

    // 定义搜索结果数据模型
    private static class SearchResultData {
        private List<MyselfData> myself;

        public List<MyselfData> getMyself() {
            return myself;
        }
    }

    // 定义餐厅数据模型
    private static class MyselfData {
        private int restaurantId;

        public int getRestaurantId() {
            return restaurantId;
        }
    }
}
