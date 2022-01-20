package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.dao.Order;
import com.example.ecommerceapp.dao.OrderCRUD;
import com.example.ecommerceapp.dao.ProductCrud;
import com.example.ecommerceapp.pojo.OrderRequest;
import com.example.ecommerceapp.pojo.Orders;
import com.example.ecommerceapp.pojo.Product;
import com.example.ecommerceapp.pojo.SearchRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@OpenAPIDefinition(info = @Info(title = "E-Commerce App",
        description = "API for E-Commerce App",
        version = "2.0",
        contact = @Contact(
                name = "E Commerce Owner"
        )
))
@Controller
@RequestMapping(path = "/e-commerce")
public class ProductController {

    @Autowired
    private ProductCrud productCrud;
    @Autowired
    private OrderCRUD orderCRUD;

    @PostMapping(path = "/search-product")
    public @ResponseBody Iterable<Product> searchProduct(@RequestBody SearchRequest request) {
        if(request.getCategory() == null) return null;
        Iterable<com.example.ecommerceapp.dao.Product> products = productCrud.findByCategory(request.getCategory());
        List<Product> productList = new ArrayList();
        for(com.example.ecommerceapp.dao.Product product: products){
            productList.add(product.getPojo());
        }
        return productList;
    }

    @PostMapping(path = "/buy-product")
    public @ResponseBody Orders buyProduct(@RequestBody OrderRequest request) throws Exception{
        if(request.getProduct_id() == null || "".equals(request.getProduct_id())) return null;
        if(request.getQuantity() == null || request.getQuantity() == 0) throw new Exception("Minimum quantity to place the order is 1");
        Optional<com.example.ecommerceapp.dao.Product> product = productCrud.findById(request.getProduct_id());
        if(!product.isPresent()) throw new Exception("No product available with provided Product ID"+request.getProduct_id());
        String result = initiateFundTransfer(request.getAccount_number(), 365400003778l, product.get().getPrice()*request.getQuantity(), "E-Commerce Transaction for purchase of :"+product.get().getName());
        if("Transaction successful".equals(result)){
            Order order = new Order();
            order.setOrderDate(new Date());
            order.setProduct_id(request.getProduct_id()+"");
            order.setProduct_name(product.get().getName());
            order.setQuantity(request.getQuantity()+"");
            order.setCustomerid(request.getCustomerid());
            orderCRUD.save(order);
            return order.getPojo();
        }else {
			System.out.println(result);
		}
        return null
        	;
    }
    private String initiateFundTransfer(Long fromAccNumber, Long toAccNumber, Long amount, String comments){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map= new HashMap();
        map.put("fromAccountNumber", fromAccNumber.toString());
        map.put("toAccountNumber", toAccNumber.toString());
        map.put("amount", amount.toString());
        map.put("comments", comments);

        try {
            String result = restTemplate.postForObject("http://localhost:8082/fund-transfer//bankcustomer/transfer", map, String.class);
            return  result;
        } catch (Exception exception) {
            System.out.println("Exception raised");
            return "Exception Raised";
        }
    }

    @GetMapping(path = "/order-history")
    public @ResponseBody Iterable<Orders> getOrderHistory(@RequestParam Integer customerId) {
        Iterable<Order> ordersList = orderCRUD.findByCustomerid(customerId);
        List<Orders> orders = new ArrayList();
        for(Order order: ordersList){
            orders.add(order.getPojo());
        }
        return orders;
    }


}
