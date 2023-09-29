package com.mydaytodo.web.backend.models.Search;

import com.mydaytodo.web.backend.models.Todo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class GlobalSearchResult implements SearchResult {
        private List<Object> webAPIresponses;
        private List<Todo> todos;
}
