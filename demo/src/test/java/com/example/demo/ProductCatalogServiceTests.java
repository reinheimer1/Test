@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductCatalogServiceTests { 
    
    @Autowired  
    private MockMvc mvc;
    
    @Test  
    @Order(1)  
    public void testCreateProduct() throws Exception { 
        this.mvc.perform(post("/product")    .contentType(MediaType.APPLICATION_JSON) .content(getCreateProductData().toString()) .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()); 
    } 
    
    @Test  
    @Order(2)  
    public void testGetProductDetails() throws Exception { 
        String testProductId = getCreateProductData().getString("id"); 
        this.mvc.perform(get("/product/" + testProductId).contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andExpect(content().json(getCreateProductData().toString())) ; 
    } 
    
    @Test  
    @Order(3)  
    public void testGetProductList() throws Exception { 
        this.mvc.perform(get("/product").contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andExpect(content().json("[" + getCreateProductData().toString() + "]")); 
    }
    @Test  
    @Order(4)  
    public void testUpdateProduct() throws Exception { 
        this.mvc.perform(put("/product") .contentType(MediaType.APPLICATION_JSON) .content(getUpdateProductData().toString())) .andExpect(status().isOk()); 
    }
    
    @Test  
    @Order(5)  
    public void testDeleteProduct() throws Exception { 
        String testProductId = getCreateProductData().getString("id"); 
        this.mvc.perform(delete("/product/" + testProductId)) .andExpect(status().isOk()); 
    }  
    
    //test data utility methods 
    public JSONObject getCreateProductData() throws JSONException { 
        JSONObject createProductData = new JSONObject(); 
        createProductData.put("id", "test-product-1"); 
        createProductData.put("title", "test product 1"); 
        return createProductData; 
    }
    
    public JSONObject getUpdateProductData() throws JSONException { 
        JSONObject createProductData = new JSONObject(); 
        createProductData.put("id", "test-product-1"); 
        createProductData.put("title", "test product 1 updated"); 
        return createProductData; 
    }
}