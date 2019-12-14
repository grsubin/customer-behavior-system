package com.texas.CustomerBehaviorSystem.controller;

import static com.texas.CustomerBehaviorSystem.model.Constants.TOKEN_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.config.TokenProvider;
import com.texas.CustomerBehaviorSystem.dao.CartItemDAO;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.CartItem;
import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.CartService;
import com.texas.CustomerBehaviorSystem.service.ProductService;
import com.texas.CustomerBehaviorSystem.service.UserService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/cart")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemDAO cartItemDAO;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private TokenProvider jwtTokenUtil;
    
    @Autowired
    private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Cart> findByUsername(@RequestHeader String authorization, @RequestParam String username){
		authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		if(tokenUsername.equals(username)) {
			Cart cart = userService.findOne(username).getCart();
			return new ResponseEntity<>(cart, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	

    @RequestMapping(value = "/{productId}", method = RequestMethod.POST)
    public ResponseEntity<CartItem> saveItem(@RequestHeader String authorization, @RequestParam (value = "q", required = false) Integer quantity,
                        @PathVariable(value = "productId") Long productId){
    	
		if (quantity == null){throw new IllegalArgumentException("Quantity = null");}

        int q = quantity;
        authorization = authorization.replace(TOKEN_PREFIX, "");
        String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
    	User user = userService.findOne(tokenUsername);
        Cart cart = user.getCart();
        Product product = productService.findById(productId);
        List<CartItem> cartItems = cart.getCartItems();
        
        // The code below is magic! Do not touch!
        for(int i = 0; i<cartItems.size(); i++){
            if (product.getId()==cartItems.get(i).getProduct().getId()){
                CartItem cartItem = cartItems.get(i);
                if(cartItem.getQuantity()+q <= product.getStock()) {
                    cartItem.setQuantity(cartItem.getQuantity() + q);
                }else {
                    cartItem.setQuantity(product.getStock());
                    throw new IllegalArgumentException("Not so much quantity in sotck.");
                }
                cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
                cartItemDAO.save(cartItem);
                cart.getCartItems().add(cartItem);
                cartService.update(cart);
                return new ResponseEntity<>(cartItem, HttpStatus.OK);
            }
        }
        
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        if(q <= product.getStock()) {
            cartItem.setQuantity(q);
        }else {
            cartItem.setQuantity(product.getStock());
            throw new IllegalArgumentException("Not so much quantity in sotck.");
        }
        cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
        cartItem.setCart(cart);
        
        CartItem cartItemSave = cartItemDAO.save(cartItem);
        cart.getCartItems().add(cartItemSave);
//        cartService.saveCart(cart);
        cartService.update(cart);	
        return new ResponseEntity<>(cartItemSave, HttpStatus.OK);
        
    }
    
    
    @RequestMapping(value = "/{cartItemId}", method = RequestMethod.DELETE)
    public  ResponseEntity<CartItem> removeItem(@RequestHeader String authorization, @PathVariable (value = "cartItemId") Long cartItemId){
        
    	authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
    	CartItem cartItem = cartItemDAO.findById(cartItemId).get();
    	Cart cart = userService.findOne(tokenUsername).getCart();
    	if(cartItem == null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	cartItemDAO.deleteById(cartItemId);
    	cart.getCartItems().remove(cartItem);
    	cartService.update(cart);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{cartItemId}", method = RequestMethod.PUT)
    public ResponseEntity<CartItem> editItem(@RequestHeader String authorization, @RequestParam (value = "q") int quantity,
                         @PathVariable (value = "cartItemId") Long cartItemId){
    	
    	authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		Cart cart = userService.findOne(tokenUsername).getCart();
        CartItem cartItem = cartItemDAO.findById(cartItemId).get();
        cartItem.setTotalPrice(cartItem.getProduct().getPrice() * quantity);
        if(quantity <= cartItem.getProduct().getStock()) {
            cartItem.setQuantity(quantity);
        }else {
            cartItem.setQuantity(cartItem.getProduct().getStock());
            throw new IllegalArgumentException("Not so much quantity in sotck.");
        }
        cartItemDAO.save(cartItem);
        cartService.update(cart);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
	
	

}
