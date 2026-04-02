package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.strefainformacji.repository.AdRepository;

@Service
@AllArgsConstructor
public class AdService {
    private final AdRepository adRepository;

    public void saveAdd() {
        
    }
}
