package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.model.Summoner;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1")
@Log4j

public class RiotApiController {
    @Autowired
    private RestTemplate restTemplate;
    private static final int teamId = 15;

    @RequestMapping(value = "/calc --data_urlenconde '{equation}'", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String querySummoner(@PathVariable("equation") String equation) throws UnsupportedEncodingException {
    	final String url = "http://52.79.162.52:8080/api/v1/answer";
    	Calculator calc = new Calculator();
		double result = calc.calculate(equation);
		long now = System.currentTimeMillis(); 
		
		
		
    	Summoner summoner = new Summoner(equation, teamId, now, result);
    	String response = restTemplate.postForObject(url, summoner, String.class);
    	
    	return response;
    }
}
