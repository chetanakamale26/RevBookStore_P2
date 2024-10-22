package com.buyerservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.buyerservice.controller.BuyerController;
import com.buyerservice.dto.ProductDTO;
import com.buyerservice.dto.ProductDetailsResponse;
import com.buyerservice.dto.ReviewDTO;
import com.buyerservice.dto.UserDTO;
import com.buyerservice.entity.Products;
import com.buyerservice.entity.Review;
import com.buyerservice.entity.User;
import com.buyerservice.service.BuyerReviewService;
import com.buyerservice.service.BuyerService;

@SpringBootTest
class BuyerserviceApplicationTests {
	 @InjectMocks
	    private BuyerController buyerController;

	    @Mock
	    private BuyerService buyerService;

	    @Mock
	    private BuyerReviewService reviewService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this); // Initialize mocks
	    }

	    @Test
	    void testViewProductDetails_ProductExists() {
	        // Arrange
	        Long productId = 1L;
	        Products product = new Products();
	        product.setProductId(productId);
	        product.setProductName("Test Product");
	        product.setProductDescription("Product Description");
	        product.setPrice(100.0);
	        product.setDiscountPrice(90.0);
	        product.setQuantity(5);

	        User seller = new User();
	        seller.setUserId(1L);
	        seller.setName("Test Seller");
	        product.setUser(seller);

	        Review review = new Review();
	        review.setReviewId(1L);
	        review.setReviewText("Good product");
	        review.setRating(4);
	        review.setUser(seller); // Assigning seller as the review user for simplicity

	        when(buyerService.viewProductDetails(productId)).thenReturn(Optional.of(product));
	        when(reviewService.getReviewsByProductId(productId)).thenReturn(Arrays.asList(review));

	        // Act
	        ResponseEntity<ProductDetailsResponse> response = buyerController.viewProductDetails(productId);

	        // Assert
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertNotNull(response.getBody());
	        ProductDetailsResponse body = response.getBody();

	        // Verify the product details
	        ProductDTO productDTO = body.getProduct();
	        assertEquals(productId, productDTO.getProductId());
	        assertEquals("Test Product", productDTO.getProductName());

	        // Verify the seller details
	        UserDTO sellerDTO = body.getSeller();
	        assertEquals(1L, sellerDTO.getUserId());
	        assertEquals("Test Seller", sellerDTO.getName());

	        // Verify the review details
	        List<ReviewDTO> reviewDTOs = body.getReviews();
	        assertEquals(1, reviewDTOs.size());
	        assertEquals("Good product", reviewDTOs.get(0).getReviewText());
	        assertEquals(4, reviewDTOs.get(0).getRating());

	        verify(buyerService, times(1)).viewProductDetails(productId);
	        verify(reviewService, times(1)).getReviewsByProductId(productId);
	    }

	    @Test
	    void testViewProductDetails_ProductNotFound() {
	        // Arrange
	        Long productId = 1L;
	        when(buyerService.viewProductDetails(productId)).thenReturn(Optional.empty());

	        // Act
	        ResponseEntity<ProductDetailsResponse> response = buyerController.viewProductDetails(productId);

	        // Assert
	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertNull(response.getBody());

	        verify(buyerService, times(1)).viewProductDetails(productId);
	        verifyNoInteractions(reviewService); // Reviews shouldn't be called when product not found
	    }

	    @Test
	    void testViewProductDetails_SellerNotFound() {
	        // Arrange
	        Long productId = 1L;
	        Products product = new Products();
	        product.setProductId(productId);
	        product.setProductName("Test Product");
	        product.setUser(null); // Seller is not set

	        when(buyerService.viewProductDetails(productId)).thenReturn(Optional.of(product));

	        // Act
	        ResponseEntity<ProductDetailsResponse> response = buyerController.viewProductDetails(productId);

	        // Assert
	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertNull(response.getBody());

	        verify(buyerService, times(1)).viewProductDetails(productId);
	        verifyNoInteractions(reviewService); // Reviews shouldn't be called when seller not found
	    }
	

}
