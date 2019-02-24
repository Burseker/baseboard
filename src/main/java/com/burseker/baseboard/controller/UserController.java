package com.burseker.baseboard.controller;

import com.burseker.baseboard.model.Customer;
import com.burseker.baseboard.sevice.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/customerslistpage", method = RequestMethod.GET)
    public String customersListPage(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sort") Optional<String> sort){

        logger.trace("page request for customers");

        int curretPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String sortProp = sort.orElse("cust_id");

        Page<Customer> pageCustomer = bankService.getCustomersPaginated(
                    PageRequest.of(curretPage-1, pageSize, new Sort(Sort.Direction.ASC, sortProp)
                ));

//        logger.trace("pageCustomer.totalPages: " + pageCustomer.getTotalPages());
//        logger.trace("pageCustomer.size: " + pageCustomer.getSize());
//        logger.trace("pageCustomer.content.size: " + pageCustomer.getContent().size());

        model.addAttribute("customers", pageCustomer);
        model.addAttribute("sortParam", sortProp);

        int totalPages = pageCustomer.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "customer_page.html";
    }

    @RequestMapping(value = "/customerslisttable", method = RequestMethod.GET)
    public String customersListString(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        logger.trace("table request for customers");
        model.addAttribute("customers", bankService.getCustomers());
        return "customer_table.html";
    }

    @RequestMapping(value = "/customerslistraw", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> customersListRaw(Model model, @RequestParam(value = "name", defaultValue = "User") String name){
        logger.trace("raw request for customers");
        return ResponseEntity.ok(bankService.getCustomers());
    }

    //@RequestMapping(value = "/createCustomer", method = RequestMethod.GET) equivalent
    @GetMapping(value = "/createCustomer")
    public String createCustomer(Model model){
        model.addAttribute("form", new Customer());
        return "createCustomer.html";
    }

    @PostMapping("/addCustomer")
    public String addCustomre(@ModelAttribute Customer customer, Model model){
        bankService.setCustomer(customer);
        logger.trace("Customer added: " + customer);
        return "redirect:/createCustomer";
//        return "redirect:/customerslisttable";
    }

    @RequestMapping(value = "/greetpage", method = RequestMethod.GET)
    public ResponseEntity<String> getPageName(){
        return ResponseEntity.ok("Hello greet page");
    }

    @RequestMapping(value = "/loggertest", method = RequestMethod.GET)
    public ResponseEntity<String> loggerTest(){
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        return ResponseEntity.ok("All logger levels had been used");
    }

    @RequestMapping(value = "/greetbyname", method = RequestMethod.GET)
    public ResponseEntity<String> getGreetingsByName(@RequestParam(value = "name", defaultValue = "User") String name){
        return ResponseEntity.ok("Hello " + name);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model, @RequestParam(value = "name", defaultValue = "User") String name){
        logger.trace("we are home");
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
        model.addAttribute("serverTime", dateFormat.format(new Date()));
        model.addAttribute("toUserMessage", "Hello " + name + "!");
        return "home.html";
    }

    @RequestMapping(value = "/addfixcustomers", method = RequestMethod.GET)
    public ResponseEntity<String> addCustomer(){

        Customer customer = new Customer();
        //customer.setCust_id(0);
        customer.setfName("Ivan");
        customer.setlName("Draga");
        customer.setPassportId("2134324234");
        bankService.setCustomer(customer);
        logger.trace("Customer added: " + customer);

        customer = new Customer();
        //customer.setCust_id(1);
        customer.setfName("Maria");
        customer.setlName("Rasputina");
        customer.setPassportId("2134234900");
        bankService.setCustomer(customer);
        logger.trace("Customer added: " + customer);

        customer = new Customer();
        //customer.setCust_id(2);
        customer.setfName("Jerar");
        customer.setlName("Pratarar");
        customer.setPassportId("1112223339");
        bankService.setCustomer(customer);
        logger.trace("Customer added: " + customer);

        customer = new Customer();
        customer.setfName("Maun");
        customer.setlName("Ktulh");
        customer.setPassportId("23339213345345");
        bankService.setCustomer(customer);
        logger.trace("Customer added: " + customer);

        customer = new Customer();
        customer.setfName("Kopper");
        customer.setlName("Fild");
        customer.setPassportId("1112223339");
        bankService.setCustomer(customer);
        logger.trace("Customer added: " + customer);

        return ResponseEntity.ok("3 customers added");
    }
}
