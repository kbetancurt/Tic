package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.persistence.TrackRepository;

@Service
public class TrackMgr {
    @Autowired
    TrackRepository trackRepository;
}
