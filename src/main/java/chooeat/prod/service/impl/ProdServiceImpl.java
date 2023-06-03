package chooeat.prod.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import chooeat.prod.model.vo.Prod;
import chooeat.prod.service.ProdService;
@Service
public class ProdServiceImpl implements ProdService {

	public List<Prod> selectAll() {
		return null;
	}

	@Override
	public List<Prod> selectByProdName() {
		return null;
	}

	@Override
	public int insert(Prod prod) {
		return 0;
	}


	public List<Prod> getSortedProducts(List<Prod> prod, String sortParam) {
		// 依 sortParam 執行相應的商品排序邏輯
		// 依評分排序
		if ("star".equals(sortParam)) {
			sortProductsByName(prod);
		}
		// 依價格從高到低排序
		else if ("pricefromhigh".equals(sortParam)) {
			sortProductsByPrice(prod, true);
		}
		// 依價格從低到高排序
		else if ("pricefromlow".equals(sortParam)) {
			sortProductsByPrice(prod, false);
		}
		return prod;
	}

	public void sortProductsByName(List<Prod> prod) {
		// 依評分排序
		Collections.sort(prod, Comparator.comparing(Prod::getProdCommentScore));
	}

	public void sortProductsByPrice(List<Prod> prod, boolean sortByHighToLow) {
		// 依價格排序
		if (sortByHighToLow) {
			Collections.sort(prod, Comparator.comparing(Prod::getProdPrice).reversed());
		} else {
			Collections.sort(prod, Comparator.comparing(Prod::getProdPrice));
		}
	}
}
