package com.kodlamiyoruz.ecomm;

import com.kodlamiyoruz.ecomm.model.Category;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.model.Seller;
import com.kodlamiyoruz.ecomm.repository.CategoryRepository;
import com.kodlamiyoruz.ecomm.repository.ProductRepository;
import com.kodlamiyoruz.ecomm.repository.SellerRepository;
import com.kodlamiyoruz.ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		Product p=new Product();
		Category c=new Category();
		Seller seller=new Seller();

		c.setCategoryName("polar");
		categoryRepository.save(c);
		seller.setName("a kisisi");
		seller.setEmail("akisisi@mail.com");
		sellerRepository.save(seller);

		p.setProductBrand("mavi");
		p.setProductName("dik yaka polar");
		p.setStock(1);
		p.setProductPrice(1.0);
		p.setCategory(c);
		p.setSeller(seller);
		productRepository.save(p);
		seller.setProducts(Arrays.asList(p));
		sellerRepository.save(seller);
		c.setProducts(Arrays.asList(p));
		categoryRepository.save(c);

		*/

	}


}
