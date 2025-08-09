package ecommerce_app.configuration;

import ecommerce_app.dao.CountryRepository;
import ecommerce_app.dao.ProductCategoryRepository;
import ecommerce_app.dao.ProductRepository;
import ecommerce_app.dao.StateRepository;
import ecommerce_app.entity.Country;
import ecommerce_app.entity.Product;
import ecommerce_app.entity.ProductCategory;
import ecommerce_app.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Configuration
public class DataInitialization implements CommandLineRunner {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductCategoryRepository productCategoryRepo;

    @Autowired
    CountryRepository countryRepo;

    @Autowired
    StateRepository stateRepo;

    @Override
    public void run(String[] args) throws Exception {
        if (productCategoryRepo.count() > 0) return;

        System.out.println("Dropping tables before data initialization");

        productRepo.deleteAll();
        productCategoryRepo.deleteAll();
        countryRepo.deleteAll();
        stateRepo.deleteAll();


        // create ProductCategory objects
        ProductCategory p1 = new ProductCategory("Books");
        ProductCategory p2 = new ProductCategory("Coffee Mugs");
        ProductCategory p3 = new ProductCategory("Mouse Pads");
        ProductCategory p4 = new ProductCategory("Luggage Tags");

        productCategoryRepo.save(p1);
        productCategoryRepo.save(p2);
        productCategoryRepo.save(p3);
        productCategoryRepo.save(p4);

        List<Product> bookProducts = Arrays.asList(
                new Product("BOOK-TECH-1000", "Crash Course in Python", "Learn Python at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1000.png", true, 100, BigDecimal.valueOf(14.99), p1),
                new Product("BOOK-TECH-1001", "Become a Guru in JavaScript", "Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1001.png", true, 100, BigDecimal.valueOf(20.99), p1),
                new Product("BOOK-TECH-1002", "Exploring Vue.js", "Learn Vue.js at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1002.png", true, 100, BigDecimal.valueOf(14.99), p1),
                new Product("BOOK-TECH-1003", "Advanced Techniques in Big Data", "Learn Big Data at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1003.png", true, 100, BigDecimal.valueOf(13.99), p1),
                new Product("BOOK-TECH-1004", "Crash Course in Big Data", "Learn Big Data at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1004.png", true, 100, BigDecimal.valueOf(18.99), p1),
                new Product("BOOK-TECH-1005", "JavaScript Cookbook", "Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1005.png", true, 100, BigDecimal.valueOf(23.99), p1),
                new Product("BOOK-TECH-1006", "Beginners Guide to SQL", "Learn SQL at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1006.png", true, 100, BigDecimal.valueOf(14.99), p1),
                new Product("BOOK-TECH-1007", "Advanced Techniques in JavaScript", "Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1007.png", true, 100, BigDecimal.valueOf(16.99), p1),
                new Product("BOOK-TECH-1008", "Introduction to Spring Boot", "Learn Spring Boot at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1008.png", true, 100, BigDecimal.valueOf(25.99), p1),
                new Product("BOOK-TECH-1009", "Become a Guru in React.js", "Learn React.js at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1009.png", true, 100, BigDecimal.valueOf(23.99), p1),
                new Product("BOOK-TECH-1010", "Beginners Guide to Data Science", "Learn Data Science at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1010.png", true, 100, BigDecimal.valueOf(24.99), p1),
                new Product("BOOK-TECH-1011", "Advanced Techniques in Java", "Learn Java at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1011.png", true, 100, BigDecimal.valueOf(19.99), p1),
                new Product("BOOK-TECH-1012", "Exploring DevOps", "Learn DevOps at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1012.png", true, 100, BigDecimal.valueOf(24.99), p1),
                new Product("BOOK-TECH-1013", "The Expert Guide to SQL", "Learn SQL at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1013.png", true, 100, BigDecimal.valueOf(19.99), p1),
                new Product("BOOK-TECH-1014", "Introduction to SQL", "Learn SQL at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1014.png", true, 100, BigDecimal.valueOf(22.99), p1),
                new Product("BOOK-TECH-1015", "The Expert Guide to JavaScript", "Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1015.png", true, 100, BigDecimal.valueOf(22.99), p1),
                new Product("BOOK-TECH-1016", "Exploring React.js", "Learn React.js at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1016.png", true, 100, BigDecimal.valueOf(27.99), p1),
                new Product("BOOK-TECH-1017", "Advanced Techniques in React.js", "Learn React.js at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1017.png", true, 100, BigDecimal.valueOf(13.99), p1),
                new Product("BOOK-TECH-1018", "Introduction to C#", "Learn C# at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1018.png", true, 100, BigDecimal.valueOf(26.99), p1),
                new Product("BOOK-TECH-1019", "Crash Course in JavaScript", "Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1019.png", true, 100, BigDecimal.valueOf(13.99), p1),
                new Product("BOOK-TECH-1020", "Introduction to Machine Learning", "Learn Machine Learning at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1020.png", true, 100, BigDecimal.valueOf(19.99), p1),
                new Product("BOOK-TECH-1021", "Become a Guru in Java", "Learn Java at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1021.png", true, 100, BigDecimal.valueOf(18.99), p1),
                new Product("BOOK-TECH-1022", "Introduction to Python", "Learn Python at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1022.png", true, 100, BigDecimal.valueOf(26.99), p1),
                new Product("BOOK-TECH-1023", "Advanced Techniques in C#", "Learn C# at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1023.png", true, 100, BigDecimal.valueOf(22.99), p1),
                new Product("BOOK-TECH-1024", "The Expert Guide to Machine Learning", "Learn Machine Learning at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!", "assets/images/products/books/book-luv2code-1024.png", true, 100, BigDecimal.valueOf(16.99), p1)
        );

        List<Product> coffeeMugProducts = Arrays.asList(
                new Product("COFFEEMUG-1000", "Coffee Mug - Express", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1000.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1001", "Coffee Mug - Cherokee", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1001.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1002", "Coffee Mug - Sweeper", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1002.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1003", "Coffee Mug - Aspire", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1003.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1004", "Coffee Mug - Dorian", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1004.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1005", "Coffee Mug - Columbia", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1005.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1006", "Coffee Mug - Worthing", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1006.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1007", "Coffee Mug - Oak Cliff", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1007.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1008", "Coffee Mug - Tachyon", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1008.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1009", "Coffee Mug - Pan", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1009.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1010", "Coffee Mug - Phase", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1010.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1011", "Coffee Mug - Falling", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1011.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1012", "Coffee Mug - Wispy", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1012.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1013", "Coffee Mug - Arlington", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1013.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1014", "Coffee Mug - Gazing", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1014.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1015", "Coffee Mug - Azura", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1015.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1016", "Coffee Mug - Quantum Leap", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1016.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1017", "Coffee Mug - Light Years", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1017.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1018", "Coffee Mug - Taylor", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1018.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1019", "Coffee Mug - Gracia", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1019.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1020", "Coffee Mug - Relax", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1020.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1021", "Coffee Mug - Windermere", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1021.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1022", "Coffee Mug - Prancer", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1022.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1023", "Coffee Mug - Recursion", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1023.png", true, 100, BigDecimal.valueOf(18.99), p2),
                new Product("COFFEEMUG-1024", "Coffee Mug - Treasure", "Do you love mathematics? If so, then you need this elegant coffee mug with an amazing fractal design. You don't have to worry about boring coffee mugs anymore. This coffee mug will be the topic of conversation in the office, guaranteed! Buy it now!", "assets/images/products/coffeemugs/coffeemug-luv2code-1024.png", true, 100, BigDecimal.valueOf(18.99), p2)
        );

        List<Product> mousePadProducts = Arrays.asList(
                new Product("MOUSEPAD-1000", "Mouse Pad - Express", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1000.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1001", "Mouse Pad - Cherokee", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1001.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1002", "Mouse Pad - Sweeper", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1002.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1003", "Mouse Pad - Aspire", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1003.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1004", "Mouse Pad - Dorian", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1004.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1005", "Mouse Pad - Columbia", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1005.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1006", "Mouse Pad - Worthing", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1006.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1007", "Mouse Pad - Oak Cliff", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1007.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1008", "Mouse Pad - Tachyon", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1008.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1009", "Mouse Pad - Pan", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1009.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1010", "Mouse Pad - Phase", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1010.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1011", "Mouse Pad - Falling", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1011.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1012", "Mouse Pad - Wispy", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1012.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1013", "Mouse Pad - Arlington", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1013.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1014", "Mouse Pad - Gazing", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1014.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1015", "Mouse Pad - Azura", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1015.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1016", "Mouse Pad - Quantum Leap", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1016.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1017", "Mouse Pad - Light Years", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1017.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1018", "Mouse Pad - Taylor", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1018.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1019", "Mouse Pad - Gracia", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1019.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1020", "Mouse Pad - Relax", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1020.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1021", "Mouse Pad - Windermere", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1021.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1022", "Mouse Pad - Prancer", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1022.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1023", "Mouse Pad - Recursion", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1023.png", true, 100, BigDecimal.valueOf(17.99), p3),
                new Product("MOUSEPAD-1024", "Mouse Pad - Treasure", "Fractal images are amazing! You can now own a mouse pad with a unique and amazing fractal. The mouse pad is made of a durable and smooth material. Your mouse will easily glide across the mouse pad. This mouse pad will brighten your workspace. Buy it now!", "assets/images/products/mousepads/mousepad-luv2code-1024.png", true, 100, BigDecimal.valueOf(17.99), p3)
        );

        List<Product> luggageTagProducts = Arrays.asList(
                new Product("LUGGAGETAG-1000", "Luggage Tag - Cherish", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1000.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1001", "Luggage Tag - Adventure", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1001.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1002", "Luggage Tag - Skyline", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1002.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1003", "Luggage Tag - River", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1003.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1004", "Luggage Tag - Trail Steps", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1004.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1005", "Luggage Tag - Blooming", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1005.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1006", "Luggage Tag - Park", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1006.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1007", "Luggage Tag - Beauty", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1007.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1008", "Luggage Tag - Water Fall", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1008.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1009", "Luggage Tag - Trail", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1009.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1010", "Luggage Tag - Skyscraper", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1010.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1011", "Luggage Tag - Leaf", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1011.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1012", "Luggage Tag - Jungle", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1012.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1013", "Luggage Tag - Shoreline", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1013.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1014", "Luggage Tag - Blossom", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1014.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1015", "Luggage Tag - Lock", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1015.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1016", "Luggage Tag - Cafe", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1016.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1017", "Luggage Tag - Darling", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1017.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1018", "Luggage Tag - Full Stack", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1018.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1019", "Luggage Tag - Courtyard", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1019.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1020", "Luggage Tag - Coaster", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1020.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1021", "Luggage Tag - Bridge", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1021.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1022", "Luggage Tag - Sunset", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1022.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1023", "Luggage Tag - Flames", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1023.png", true, 100, BigDecimal.valueOf(16.99), p4),
                new Product("LUGGAGETAG-1024", "Luggage Tag - Countryside", "This luggage tag will help you identify your luggage. The luggage tag is very unique and it will stand out from the crowd. The luggage tag is created out of a rugged and durable plastic. Buy this luggage tag now to make it easy to identify your luggage!", "assets/images/products/luggagetags/luggagetag-luv2code-1024.png", true, 100, BigDecimal.valueOf(16.99), p4)
        );

        productRepo.saveAll(bookProducts);
        productRepo.saveAll(coffeeMugProducts);
        productRepo.saveAll(mousePadProducts);
        productRepo.saveAll(luggageTagProducts);

        Country c1 = new Country("BR","Brazil");
        Country c2 = new Country("CA","Canada");
        Country c3 = new Country("DE","Germany");
        Country c4 = new Country("IN","India");
        Country c5 = new Country("TR","Turkey");
        Country c6 = new Country("US","United States");

        List<Country> countries = Arrays.asList(
            c1, c2, c3, c4, c5, c6
        );

        countryRepo.saveAll(countries);

        List<State> states = new ArrayList<>();

        states.add(new State("Acre", c1));
        states.add(new State("Alagoas", c1));
        states.add(new State("Amapá", c1));
        states.add(new State("Amazonas", c1));
        states.add(new State("Bahia", c1));
        states.add(new State("Ceará", c1));
        states.add(new State("Distrito Federal", c1));
        states.add(new State("Espírito Santo", c1));
        states.add(new State("Goiás", c1));
        states.add(new State("Maranhão", c1));
        states.add(new State("Mato Grosso do Sul", c1));
        states.add(new State("Mato Grosso", c1));
        states.add(new State("Minas Gerais", c1));
        states.add(new State("Paraná", c1));
        states.add(new State("Paraíba", c1));
        states.add(new State("Pará", c1));
        states.add(new State("Pernambuco", c1));
        states.add(new State("Piaui", c1));
        states.add(new State("Rio de Janeiro", c1));
        states.add(new State("Rio Grande do Norte", c1));
        states.add(new State("Rio Grande do Sul", c1));
        states.add(new State("Rondônia", c1));
        states.add(new State("Roraima", c1));
        states.add(new State("Santa Catarina", c1));
        states.add(new State("Sergipe", c1));
        states.add(new State("São Paulo", c1));
        states.add(new State("Tocantins", c1));
        states.add(new State("Alberta", c2));
        states.add(new State("British Columbia", c2));
        states.add(new State("Manitoba", c2));
        states.add(new State("New Brunswick", c2));
        states.add(new State("Newfoundland and Labrador", c2));
        states.add(new State("Northwest Territories", c2));
        states.add(new State("Nova Scotia", c2));
        states.add(new State("Nunavut", c2));
        states.add(new State("Ontario", c2));
        states.add(new State("Prince Edward Island", c2));
        states.add(new State("Quebec", c2));
        states.add(new State("Saskatchewan", c2));
        states.add(new State("Yukon", c2));
        states.add(new State("Baden-Württemberg", c3));
        states.add(new State("Bavaria", c3));
        states.add(new State("Berlin", c3));
        states.add(new State("Brandenburg", c3));
        states.add(new State("Bremen", c3));
        states.add(new State("Hamburg", c3));
        states.add(new State("Hesse", c3));
        states.add(new State("Lower Saxony", c3));
        states.add(new State("Mecklenburg-Vorpommern", c3));
        states.add(new State("North Rhine-Westphalia", c3));
        states.add(new State("Rhineland-Palatinate", c3));
        states.add(new State("Saarland", c3));
        states.add(new State("Saxony", c3));
        states.add(new State("Saxony-Anhalt", c3));
        states.add(new State("Schleswig-Holstein", c3));
        states.add(new State("Thuringia", c3));
        states.add(new State("Andhra Pradesh", c4));
        states.add(new State("Arunachal Pradesh", c4));
        states.add(new State("Assam", c4));
        states.add(new State("Bihar", c4));
        states.add(new State("Chhattisgarh", c4));
        states.add(new State("Goa", c4));
        states.add(new State("Gujarat", c4));
        states.add(new State("Haryana", c4));
        states.add(new State("Himachal Pradesh", c4));
        states.add(new State("Jammu & Kashmir", c4));
        states.add(new State("Jharkhand", c4));
        states.add(new State("Karnataka", c4));
        states.add(new State("Kerala", c4));
        states.add(new State("Madhya Pradesh", c4));
        states.add(new State("Maharashtra", c4));
        states.add(new State("Manipur", c4));
        states.add(new State("Meghalaya", c4));
        states.add(new State("Mizoram", c4));
        states.add(new State("Nagaland", c4));
        states.add(new State("Odisha", c4));
        states.add(new State("Punjab", c4));
        states.add(new State("Rajasthan", c4));
        states.add(new State("Sikkim", c4));
        states.add(new State("Tamil Nadu", c4));
        states.add(new State("Telangana", c4));
        states.add(new State("Tripura", c4));
        states.add(new State("Uttar Pradesh", c4));
        states.add(new State("Uttarakhand", c4));
        states.add(new State("West Bengal", c4));
        states.add(new State("Andaman and Nicobar Islands", c4));
        states.add(new State("Chandigarh", c4));
        states.add(new State("Dadra and Nagar Haveli", c4));
        states.add(new State("Daman & Diu", c4));
        states.add(new State("Lakshadweep", c4));
        states.add(new State("Puducherry", c4));
        states.add(new State("The Government of NCT of Delhi", c4));
        states.add(new State("Alabama", c6));
        states.add(new State("Alaska", c6));
        states.add(new State("Arizona", c6));
        states.add(new State("Arkansas", c6));
        states.add(new State("California", c6));
        states.add(new State("Colorado", c6));
        states.add(new State("Connecticut", c6));
        states.add(new State("Delaware", c6));
        states.add(new State("District Of Columbia", c6));
        states.add(new State("Florida", c6));
        states.add(new State("Georgia", c6));
        states.add(new State("Hawaii", c6));
        states.add(new State("Idaho", c6));
        states.add(new State("Illinois", c6));
        states.add(new State("Indiana", c6));
        states.add(new State("Iowa", c6));
        states.add(new State("Kansas", c6));
        states.add(new State("Kentucky", c6));
        states.add(new State("Louisiana", c6));
        states.add(new State("Maine", c6));
        states.add(new State("Maryland", c6));
        states.add(new State("Massachusetts", c6));
        states.add(new State("Michigan", c6));
        states.add(new State("Minnesota", c6));
        states.add(new State("Mississippi", c6));
        states.add(new State("Missouri", c6));
        states.add(new State("Montana", c6));
        states.add(new State("Nebraska", c6));
        states.add(new State("Nevada", c6));
        states.add(new State("New Hampshire", c6));
        states.add(new State("New Jersey", c6));
        states.add(new State("New Mexico", c6));
        states.add(new State("New York", c6));
        states.add(new State("North Carolina", c6));
        states.add(new State("North Dakota", c6));
        states.add(new State("Ohio", c6));
        states.add(new State("Oklahoma", c6));
        states.add(new State("Oregon", c6));
        states.add(new State("Pennsylvania", c6));
        states.add(new State("Rhode Island", c6));
        states.add(new State("South Carolina", c6));
        states.add(new State("South Dakota", c6));
        states.add(new State("Tennessee", c6));
        states.add(new State("Texas", c6));
        states.add(new State("Utah", c6));
        states.add(new State("Vermont", c6));
        states.add(new State("Virginia", c6));
        states.add(new State("Washington", c6));
        states.add(new State("West Virginia", c6));
        states.add(new State("Wisconsin", c6));
        states.add(new State("Wyoming", c6));
        states.add(new State("Adıyaman", c5));
        states.add(new State("Afyonkarahisar", c5));
        states.add(new State("Ağrı", c5));
        states.add(new State("Aksaray", c5));
        states.add(new State("Amasya", c5));
        states.add(new State("Ankara", c5));
        states.add(new State("Antalya", c5));
        states.add(new State("Ardahan", c5));
        states.add(new State("Artvin", c5));
        states.add(new State("Aydın", c5));
        states.add(new State("Balıkesir", c5));
        states.add(new State("Bartın", c5));
        states.add(new State("Batman", c5));
        states.add(new State("Bayburt", c5));
        states.add(new State("Bilecik", c5));
        states.add(new State("Bingöl", c5));
        states.add(new State("Bitlis", c5));
        states.add(new State("Bolu", c5));
        states.add(new State("Burdur", c5));
        states.add(new State("Bursa", c5));
        states.add(new State("Çanakkale", c5));
        states.add(new State("Çankırı", c5));
        states.add(new State("Çorum", c5));
        states.add(new State("Denizli", c5));
        states.add(new State("Diyarbakır", c5));
        states.add(new State("Düzce", c5));
        states.add(new State("Edirne", c5));
        states.add(new State("Elazığ", c5));
        states.add(new State("Erzincan", c5));
        states.add(new State("Erzurum", c5));
        states.add(new State("Eskişehir", c5));
        states.add(new State("Gaziantep", c5));
        states.add(new State("Giresun", c5));
        states.add(new State("Gümüşhane", c5));
        states.add(new State("Hakkâri", c5));
        states.add(new State("Hatay", c5));
        states.add(new State("Iğdır", c5));
        states.add(new State("Isparta", c5));
        states.add(new State("İstanbul", c5));
        states.add(new State("İzmir", c5));
        states.add(new State("Kahramanmaraş", c5));
        states.add(new State("Karabük", c5));
        states.add(new State("Karaman", c5));
        states.add(new State("Kars", c5));
        states.add(new State("Kastamonu", c5));
        states.add(new State("Kayseri", c5));
        states.add(new State("Kırıkkale", c5));
        states.add(new State("Kırklareli", c5));
        states.add(new State("Kırşehir", c5));
        states.add(new State("Kilis", c5));
        states.add(new State("Kocaeli", c5));
        states.add(new State("Konya", c5));
        states.add(new State("Kütahya", c5));
        states.add(new State("Malatya", c5));
        states.add(new State("Manisa", c5));
        states.add(new State("Mardin", c5));
        states.add(new State("Mersin", c5));
        states.add(new State("Muğla", c5));
        states.add(new State("Muş", c5));
        states.add(new State("Nevşehir", c5));
        states.add(new State("Niğde", c5));
        states.add(new State("Ordu", c5));
        states.add(new State("Osmaniye", c5));
        states.add(new State("Rize", c5));
        states.add(new State("Sakarya", c5));
        states.add(new State("Samsun", c5));
        states.add(new State("Siirt", c5));
        states.add(new State("Sinop", c5));
        states.add(new State("Sivas", c5));
        states.add(new State("Şanlıurfa", c5));
        states.add(new State("Şırnak", c5));
        states.add(new State("Tekirdağ", c5));
        states.add(new State("Tokat", c5));
        states.add(new State("Trabzon", c5));
        states.add(new State("Tunceli", c5));
        states.add(new State("Uşak", c5));
        states.add(new State("Van", c5));
        states.add(new State("Yalova", c5));
        states.add(new State("Yozgat", c5));
        states.add(new State("Zonguldak", c5));

        stateRepo.saveAll(states);

        System.out.println("All data has been initialized");
    }
}
