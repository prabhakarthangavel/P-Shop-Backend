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
import com.pshop.products.entity.User;
import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.request.UserRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.ProductsResponse;
import com.pshop.products.model.response.SaveResponse;
import com.pshop.products.service.ProductsService;

@Service
public class ProductServiceImpl implements ProductsService {
	
	@Autowired
	public Auth authRepo;
	
	@Autowired
	private ProductsDAOImpl productsDAO;
	
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
}
