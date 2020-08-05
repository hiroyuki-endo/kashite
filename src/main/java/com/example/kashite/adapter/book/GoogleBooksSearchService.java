package com.example.kashite.adapter.book;

import static java.util.stream.Collectors.*;

import java.util.*;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.kashite.domain.search.BookInfo;
import com.example.kashite.domain.search.BookSearchService;
import com.example.kashite.query.dto.BookInfoDto;
import com.example.kashite.utils.ConditonalStringJoiner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GoogleBooksSearchService implements BookSearchService {
    private RestTemplate restTemplate;

    public GoogleBooksSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .build();
    }

    @Override
    public BookInfo searchById(String id) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes/" + id)
                .encode()
                .build();
        GoogleBooksVolumeResult result = restTemplate.getForObject(uriComponents.toUri(), GoogleBooksVolumeResult.class);
        log.info(uriComponents.toUriString());
        return toBookDto(result);
    }

    @Override
    public List<BookInfo> search(String intitle, String author) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", searchParam(intitle, author))
                .encode()
                .build();
        GoogleBooksSearchResults results = restTemplate.getForObject(uriComponents.toUri(), GoogleBooksSearchResults.class);
        log.info(uriComponents.toUriString());
        log.info("number of results {}.", results.getTotalItems());
        if(results.getTotalItems() == 0) {
            return Collections.emptyList();
        } else {
            return toBooks(results.getItems());
        }
    }

    private String searchParam(String intitle, String author) {
        return ConditonalStringJoiner.init("+")
                .optionalAppend(intitle, Strings::isNotEmpty, v -> "intitle:" + v)
                .optionalAppend(author, Strings::isNotEmpty, v -> "author:" + v)
                .toString();
    }

    private List<BookInfo> toBooks(List<GoogleBooksVolumeResult> results) {
        return results.stream()
                .map(this::toBookDto)
                .collect(toList());
    }

    private BookInfo toBookDto(GoogleBooksVolumeResult result) {
        return new BookInfoDto(result.getId(),
                result.getIsbn(),
                result.getTitle(),
                result.getVolumeInfo().getPageCount(),
                getListPrice(result.getSaleInfo()),
                result.getAuthors(),
                result.getPublisher(),
                result.getPublishedDate(),
                result.getDescription(),
                result.getVolumeInfo().getImageLinks() != null ?
                        result.getVolumeInfo().getImageLinks().getThumbnail() : null);
    }

    private int getListPrice(GoogleSaleInfo saleInfo) {
        if("FOR_SALE".equals(saleInfo.getSaleability())) {
            return saleInfo.getListPrice().getAmount();
        } else {
            return 0;
        }
    }
}
