package com.example.account_m.async;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
	public class AsyncController
	{
	    private final Publisher publisher;

	    @Autowired
	    public AsyncController(Publisher publisher)
	    {
	        this.publisher = publisher;
	    }

	    @GetMapping ("/send")
	    public String sendMsg(@RequestParam ("msg") String msg)
	    {
	        publisher.produceMsg(msg);
	        return "Message sent...";
	    }
	}
