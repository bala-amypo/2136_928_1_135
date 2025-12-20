import java.util.List;
import java.util.Optional;
import com.example.demo.entity.EventUpdate;

public interface EventUpdateService {
    EventUpdate save(EventUpdate update);
    List<EventUpdate> getAll();
    Optional<EventUpdate> getById(Integer id); // ⚠️ make sure it returns Optional
    void delete(Integer id);
}
