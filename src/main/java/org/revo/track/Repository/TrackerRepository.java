package org.revo.track.Repository;

import org.revo.track.Domain.Tracker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TrackerRepository extends ReactiveCrudRepository<Tracker, String> {
}
