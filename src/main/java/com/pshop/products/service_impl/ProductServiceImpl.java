package com.pshop.products.service_impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pshop.products.DAO.Auth;
import com.pshop.products.DAO_Impl.ProductsDAOImpl;
import com.pshop.products.entity.AllProducts;
import com.pshop.products.entity.CartProduct;
import com.pshop.products.entity.Role;
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
import com.pshop.repo.ProductsRepo;

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
	
	@Autowired
	private ProductsRepo pagingRepo;
	
	@Transactional
	@Override
	public SaveResponse save() {
		AllProducts products = new AllProducts("bread","https://static.pexels.com/photos/2434/bread-food-healthy-breakfast.jpg",
				35.0, "Freshly Baked Bread",10);
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
				response.setStatus("Authenticated");
				response.setRole(user.getRoles().iterator().next().getRole());
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

	@Transactional
	@Override
	public ShoppingCartResponse clearCart(String id) {
		productRepo.clearCart(id);
		ShoppingCart entity = cartRepo.findByid(id);
		ModelMapper mapper = new ModelMapper();
		ShoppingCartResponse response = mapper.map(entity, ShoppingCartResponse.class);
		return response;
	}

	@Override
	public List<ProductsResponse> pagableProduct(int firstIndex, int lastIndex) {
		List<ProductsResponse> response = new ArrayList<ProductsResponse>();
		Pageable sortByTitle = PageRequest.of(firstIndex, lastIndex, Sort.by("title").ascending());
		Page<AllProducts> products = pagingRepo.findAll(sortByTitle);
		for(AllProducts prod:products) {
			ProductsResponse res = new ProductsResponse();
			BeanUtils.copyProperties(prod, res);
			response.add(res);
		}
		return response;
	}

	@Override
	public List<ProductsResponse> searchProduct(String value) {
		List<ProductsResponse> response = new ArrayList<ProductsResponse>();
		List<AllProducts> products = pagingRepo.findProducts(value);
		for(AllProducts prod:products) {
			ProductsResponse res = new ProductsResponse();
			BeanUtils.copyProperties(prod, res);
			response.add(res);
		}
		return response;
	}

	@Override
	public ProductsResponse getProductByTitle(String product) {
		ProductsResponse response = new ProductsResponse();
		AllProducts products = pagingRepo.findByTitle(product);
		if(products == null) {
			return response;
		}
		BeanUtils.copyProperties(products, response);
		return response;
	}
	
	@Transactional
	@Override
	public SaveResponse updateProduct(ProductsResponse request) {
		SaveResponse response = new SaveResponse();
		int updated = pagingRepo.updateProduct(request.getId(),request.getTitle(),request.getPrice(),
				request.getCategory(),request.getImage_url(),request.getStock());
		if(updated==1) {
			response.setStatus("Saved Sacessfully");
		}else {
			response.setStatus("Unable to save the data");
		}
		return response;
	}
}
