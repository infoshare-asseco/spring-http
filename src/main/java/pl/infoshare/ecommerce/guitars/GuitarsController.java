package pl.infoshare.ecommerce.guitars;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/guitars")
@RequiredArgsConstructor
public class GuitarsController {

    private final GuitarRepository guitarRepository;

    @GetMapping
    public Collection<Guitar> getAllGuitars() {
        return guitarRepository.getAll();
    }
}
