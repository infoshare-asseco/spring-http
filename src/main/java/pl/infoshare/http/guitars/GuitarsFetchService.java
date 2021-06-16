package pl.infoshare.http.guitars;

import org.springframework.stereotype.Component;
import pl.infoshare.http.guitars.model.AvailableGuitar;

import java.util.Collections;
import java.util.List;

@Component
public class GuitarsFetchService {


    public List<AvailableGuitar> fetchAllGuitars() {
        return Collections.emptyList();
    }
}
