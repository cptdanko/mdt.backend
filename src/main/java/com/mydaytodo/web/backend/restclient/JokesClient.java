package com.mydaytodo.web.backend.restclient;

import com.mydaytodo.web.backend.config.KeyConfig;
import com.mydaytodo.web.backend.models.ChuckNorrisJoke;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@Component
public class JokesClient {
    private final Logger logger = Logger.getLogger(JokesClient.class.toString());
    @Autowired
    private KeyConfig config;
    private final Set<String> jokeCategories = new HashSet<>();

    public JokesClient() {
    }

    /**
     * retrieve random Chuck Norris Joke
     * @return
     */
    public ResponseEntity<ChuckNorrisJoke> getJokes() {
        RestTemplate template = new RestTemplate();
        String url = config.getJokesUrl() + "/random";
        return template.getForEntity(url, ChuckNorrisJoke.class);
    }

    /**
     *
     * @param category
     * @return
     * @throws InterruptedException
     */
    public ResponseEntity<ChuckNorrisJoke> getJokes(String category) throws InterruptedException {
        RestTemplate template = new RestTemplate();
        loadCategories();
        if(category == null) {
            Random r = new Random();
            int index = r.nextInt(this.jokeCategories.size());
            category = jokeCategories.stream().toList().get(index);
        } else {
            // do some validation here to check for category
            String finalCategory = category;
            long count = jokeCategories.stream()
                    .filter(cat -> cat.equalsIgnoreCase(finalCategory))
                    .count();
            if (count < 1) {
                throw new InterruptedException("Invalid category passed");
            }
        }
        String url = config.getJokesUrl() + "/random?category=" + category;
        return template.getForEntity(url, ChuckNorrisJoke.class);
    }


    private void loadCategories() throws InterruptedException {
        String url = config.getJokesUrl() + "/categories";
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(url, String.class);
        String cats = resp.getBody();
        if(cats != null) {
            jokeCategories.addAll(Arrays.asList(cats.split("\n")));
        }
    }
}
