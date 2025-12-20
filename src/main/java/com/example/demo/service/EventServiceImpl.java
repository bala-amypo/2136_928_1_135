@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Optional<Event> getById(Integer id) {
        return eventRepository.findById(id);
    }
}
