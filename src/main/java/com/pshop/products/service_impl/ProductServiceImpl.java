package com.pshop.products.service_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pshop.products.DAO.Auth;
import com.pshop.products.DAO_Impl.ProductsDAOImpl;
import com.pshop.products.entity.AllProducts;
import com.pshop.products.entity.CartProduct;
import com.pshop.products.entity.ShoppingCart;
import com.pshop.products.entity.User;
import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.request.RegisterRequest;
import com.pshop.products.model.request.ShoppingCartRequest;
import com.pshop.products.model.request.UserRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.ProductsResponse;
import com.pshop.products.model.response.SaveResponse;
import com.pshop.products.model.response.ShoppingCartResponse;
import com.pshop.products.service.ProductsService;
import com.pshop.repo.CartProductRepo;
import com.pshop.repo.CartRepo;

@Service
public class ProductServiceImpl implements ProductsService {
	
	@Autowired
	public Auth authRepo;
	
	@Autowired
	private ProductsDAOImpl productsDAO;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CartProductRepo productRepo;
	
	@Transactional
	@Override
	public SaveResponse save() {
		AllProducts products = new AllProducts("bread","https://static.pexels.com/photos/2434/bread-food-healthy-breakfast.jpg",
				35.0, "Freshly Baked Bread");
		SaveResponse response = new SaveResponse();
		try {
			productsDAO.save(products);
			response.setStatus("Success");
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("Failed");
		}
		
		return response;
	}

	@Transactional
	@Override
	public List<ProductsResponse> getProducts(String product) {
		List<ProductsResponse> response = new ArrayList<ProductsResponse>();
		List<AllProducts> products = productsDAO.getProducts(product);
		for(AllProducts prod:products) {
			ProductsResponse res = new ProductsResponse();
			BeanUtils.copyProperties(prod, res);
			response.add(res);
		}
		return response;
	}
	
	@Transactional
	@Override
	public void saveUser(UserRequest user) {
		ModelMapper mapper = new ModelMapper();
		java.lang.reflect.Type source = new TypeToken<User>() {
		}.getType();
		User userEntity = mapper.map(user, source);
		System.out.println(userEntity.getUsername()+userEntity.getUser_id()+userEntity.getRoles().size());
		authRepo.save(userEntity);
	}

	@Override
	public LoginResponse authenticate(AuthRequest request) {
		User user = authRepo.findUser(request.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		LoginResponse response = new LoginResponse();
		if(user == null) {
			response.setUsername(request.getUsername());
			response.setStatus("failed");
		}else {
			if(encoder.matches(request.getPassword(), user.getPassword())) {
				response.setUsername(request.getUsername());
				response.setStatus("success");
			}else {
				response.setUsername(request.getUsername());
				response.setStatus("failed");
			}
		}
		return response;
	}
	
	@Transactional
	@Override
	public ShoppingCartResponse addToCart(ShoppingCartRequest request) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		ShoppingCart entity = cartRepo.findByid(request.getId());
		List<String> products = new ArrayList();
		if(entity != null) {
			for(CartProduct list:entity.getCartProduct()) {
				products.add(list.getTitle());
				if(list.getTitle().equalsIgnoreCase(request.getTitle())) {
					list.setQuantity(list.getQuantity()+1);
					list.setTotal_price(list.getQuantity()*request.getPrice());
				}
			}
			if(!products.contains(request.getTitle())){
				List<CartProduct> add = entity.getCartProduct();
				CartProduct prod = new CartProduct();
				prod.setTitle(request.getTitle());
				prod.setImage_url(request.getImage_url());
				prod.setQuantity(1);
				prod.setTotal_price(prod.getQuantity()*request.getPrice());
				prod.setPrice(request.getPrice());
				add.add(prod);	
			}
			ModelMapper mapper = new ModelMapper();
			response = mapper.map(entity, ShoppingCartResponse.class);
			cartRepo.save(entity);
		}else {
			ShoppingCart cart = new ShoppingCart();
			cart.setId(request.getId());
			List<CartProduct> product = new ArrayList<CartProduct>();
			CartProduct prod = new CartProduct();
			prod.setTitle(request.getTitle());
			prod.setImage_url(request.getImage_url());
			prod.setQuantity(1);
			prod.setTotal_price(prod.getQuantity()*request.getPrice());
			prod.setPrice(request.getPrice());
			product.add(prod);
			cart.setCartProduct(product);
			cartRepo.save(cart);	
		}
		ModelMapper mapper = new ModelMapper();
		entity = cartRepo.findByid(request.getId());
		response = mapper.map(entity, ShoppingCartResponse.class);
		return response;
	}
	
	@Transactional
	@Override
	public ShoppingCartResponse removeFromCart(ShoppingCartRequest request) {
		ModelMapper mapper = new ModelMapper();
		productRepo.removeItem(request.getId(),request.getTitle(),request.getPrice());
		ShoppingCart entity = cartRepo.findByid(request.getId());
		List<CartProduct> products = entity.getCartProduct();
		for(CartProduct product:products) {
			if(product.getQuantity() == 0) {
				productRepo.deleteProduct(product.getTitle(),request.getId());
			};
		}
		entity = cartRepo.findByid(request.getId());
		ShoppingCartResponse response = mapper.map(entity, ShoppingCartResponse.class);
		return response;
	}
}
