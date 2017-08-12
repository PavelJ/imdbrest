package com.jirapave.imdb.service;

import com.jirapave.imdb.service.impl.ImdbServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ImdbServiceTest {

    @Mock
    RestTemplate restTemplate;

    private ImdbService imdbService;

    @Before
    public void setUp() {
        imdbService = new ImdbServiceImpl(restTemplate);
    }

    @Test
    public void searchForTitleTest() throws Exception {
        // RemoteService has been injected into the reverser bean
        Mockito.when(restTemplate.getForObject(ImdbServiceImpl.IMDB_TITLE_URL, String.class, new String[]{"lost"})).thenReturn("<test>aaa</test>");

        String result = imdbService.searchForTitle("lost");
        Assert.assertEquals("Service should translate XML into JSON with identation", "{\n    \"test\": \"aaa\"\n}", result);
    }

}
