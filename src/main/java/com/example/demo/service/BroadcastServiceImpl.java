@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastLogRepository broadcastLogRepository;

    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
    }

    // EXISTING METHODS (unchanged)
    @Override
    public BroadcastLog save(BroadcastLog log) {

        if (log == null) {
            throw new IllegalArgumentException("Invalid broadcast log data");
        }

        EventUpdate eventUpdate = log.getEventUpdate();
        if (eventUpdate == null) {
            throw new ResourceNotFoundException("EventUpdate must exist for broadcast");
        }

        User subscriber = log.getSubscriber();
        if (subscriber == null) {
            throw new ResourceNotFoundException("Subscriber must exist for broadcast");
        }

        return broadcastLogRepository.save(log);
    }

    @Override
    public List<BroadcastLog> getAll() {
        return broadcastLogRepository.findAll();
    }

    // 🔹 NEW METHODS (to satisfy controller)

    @Override
    public void broadcastUpdate(Long updateId) {
        // For now, just validate existence
        // Real notification logic can be added later
        if (updateId == null) {
            throw new IllegalArgumentException("Update ID cannot be null");
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public List<BroadcastLog> getAllLogs() {
        return broadcastLogRepository.findAll();
    }
}
