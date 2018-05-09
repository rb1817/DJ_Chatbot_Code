package net.n1books.dev2.jackson;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@RequestMapping(value="getProduct",
		headers="Accept=application/json;charset=UTF-8",
		produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Product getProduct() {
		return new Product("LG Gram", 2000000, 2, "짱 좋음");
	}
	
	@RequestMapping("getProductList")
	public List<Product> getProductList() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product("LG Gram", 2000000, 2, "짱 좋음"));
		list.add(new Product("Samsung 9", 1900000, 4, "태블릿이 좋음"));
		list.add(new Product("Thinkpad", 1800000, 5, "가성비가 좋음"));
		list.add(new Product("shintick", 2000000, 2, "쵝오의 태브릿"));
		return list;
	}
}