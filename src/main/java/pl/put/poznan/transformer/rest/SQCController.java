package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.SQChecker;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class SQCController {

    private static final Logger logger = LoggerFactory.getLogger(SQCController.class);



    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                      @RequestParam(value="funkcja", defaultValue="zliczKroki") String funkcja) {

        // log the parameters
        logger.debug(text);
        logger.debug(funkcja);


        SQChecker Checker = new SQChecker(text);
        return Checker.check(funkcja);
    }





}


